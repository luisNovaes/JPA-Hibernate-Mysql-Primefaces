/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.utilidades;

import br.com.aplicacao.daos.AgendamentoDAO;
import java.text.ParseException;

/**
 *
 * @author luis.silva
 */
public interface CrudAgendamento {

    public static void main(String[] args) throws ParseException {

        AgendamentoDAO dao = new AgendamentoDAO();
        //dao.salvar();
        //dao.buscarTodos();
        //dao.buscarAgendamentoPorData();
        //dao.buscarAgendamentoPorHora();
        dao.buscarAgendamentoPelaPlaca();

    }

}
