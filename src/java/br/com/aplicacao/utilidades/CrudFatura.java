/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.utilidades;

import br.com.aplicacao.daos.FaturaDAO;

/**
 *
 * @author luis.silva
 */
public interface CrudFatura {

    public static void main(String[] args) {

        FaturaDAO dao = new FaturaDAO();
        dao.salvar();
        //dao.buscarTodos();
        //dao.buscarFaturaPelaOS();
        //dao.buscarFaturaPelaStatusPG();
        //dao.buscarFaturaValorDoc();
    }

}
