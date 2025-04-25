package com.javaweb.app3.Controller;

import jakarta.servlet.http.HttpSession;
import com.javaweb.app3.Model.Usuario;
import com.javaweb.app3.Service.UsuarioService; // IMPORTANTE
import org.springframework.beans.factory.annotation.Autowired; // IMPORTANTE
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class usuariocontroller {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/novoUsr")
    public String mostrarTelaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.cadastrar(usuario);
        model.addAttribute("msg", "Usuário cadastrado com sucesso!");
        return "login";
    }

    @PostMapping("/validar")
    public String validarCredencias(@ModelAttribute Usuario usr, Model modelo, HttpSession sessao) {
        Usuario u = usuarioService.autenticar(usr.getLogin(), usr.getSenha());
        if (u != null) {
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

        modelo.addAttribute("msg", "Usuário ou senha incorretos");
        return "login";
    }

    @GetMapping("/login")
    public String mostrarTelaLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String mostraTelainicial(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            model.addAttribute("msg", "Sessão expirou ou usuario deslogado");
            return "redirect:/login";
        } else {
            model.addAttribute("Nomeusuario", usuario.getNome());
            return "home";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession sessao) {
        sessao.invalidate();
        return "redirect:/login";
    }
}
