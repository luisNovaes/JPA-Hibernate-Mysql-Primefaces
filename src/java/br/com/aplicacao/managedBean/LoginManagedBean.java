/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.managedBean;

import br.com.aplicacao.daos.UsuarioDAO;
import br.com.aplicacao.modelos.Usuario;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author luis.silva
 */
@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario = new Usuario();

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();

        usuario = usuarioDAO.getUsuario(usuario.getNome(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            context.addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha invalidos!",
                            "Erro no Login!"));
        } else {
            context.getExternalContext().getSessionMap().put("user", usuario);

            try {
                context.getExternalContext().redirect("home.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            System.out.println("Erro (" + e.getMessage() + " ao fazer o Logoff!");

        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//********************************************
    public Usuario getSenha() {
        return null;
    }

    public void setSenha(Usuario senha) {

    }

}
