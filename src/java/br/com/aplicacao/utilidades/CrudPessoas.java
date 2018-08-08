/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.utilidades;

import br.com.aplicacao.daos.PessoaDAO;

/**
 *
 * @author luis.silva
 */
public interface CrudPessoas {

    public static void main(String[] args) throws Exception {
        PessoaDAO dao = new PessoaDAO();
        //dao.salvar();
        dao.buscarTodos();
        //dao.buscarPorNome();
        //dao.buscarPorCpfCnpj();
        //dao.buscarPorRg();
        //dao.AtualizarPessoaTelefone();
        //dao.AtualizarPessoaEndereco();
        //dao.AtualizarPessoaEmail();
        //dao.AtualizarPessoaCidade();
        //dao.AtualizarPessoaEstado();
        //dao.AtualizarPessoaObservacao();
        //dao.ExcluirPessoa();
    }

}
