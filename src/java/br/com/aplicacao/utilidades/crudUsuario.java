/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.utilidades;

import br.com.aplicacao.daos.UsuarioDAO;

/**
 *
 * @author luis.silva
 */
public class crudUsuario {

    public static void main(String[] args) {

        UsuarioDAO usuario = new UsuarioDAO();
        //usuario.buscarTodos();
        //usuario.getUsuario("Amaral", "123456");
        usuario.inserirUsuario();
    }

}
