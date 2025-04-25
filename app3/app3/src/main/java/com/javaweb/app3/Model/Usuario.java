package com.javaweb.app3.Model;

public class Usuario {

    //atributos
    private String nome;
    private String email;
    private String perfil;
    private String Login;
    private String senha;

    //getters e setters
    public String getLogin() {
        return Login;
    }
    public String getSenha() {
        return senha;
    }
    public void setLogin(String login) {
        Login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }


}
