package com.javaweb.app3.Controller;

import jakarta.servlet.http.HttpSession;
import com.javaweb.app3.Model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class usuariocontroller {

    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    @GetMapping("/novoUsr")
    public String mostrarTelaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        listaUsuarios.add(usuario);
        model.addAttribute("msg", "Usuário cadastrado com sucesso!");
        return "login";
    }

    @PostMapping("/validar")
    public String validarCredencias(@ModelAttribute Usuario usr, Model modelo, HttpSession sessao) {
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equalsIgnoreCase(usr.getLogin()) && u.getSenha().equals(usr.getSenha())) {
                sessao.setAttribute("usuarioLogado", u);

                switch (u.getPerfil()) {
                    case "ADMIN":
                        return "admin";
                    case "PROFESSOR":
                        return "professor";
                    case "ALUNO":
                        return "aluno";
                    default:
                        return "home";
                }
            }
        }
        modelo.addAttribute("msg", "Usuário ou senha incorretos");
        return "login";
    }


        @GetMapping("/login")
        public String mostrarTelaLogin(){
            return "login";
        }

        @GetMapping("/home")
        public String mostraTelainicial(HttpSession session, Model model) {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null) {
                model.addAttribute("msg", "Sessão expirou ou usuario deslogado");
                return "redirect:/login";
            }
            else {
                model.addAttribute("Nomeusuario", usuario.getNome());
                return "home";
            }
        }

        @GetMapping("/logout")
        public String logout(HttpSession sessao){
            sessao.invalidate();
            return "redirect:/login";
        }
}
