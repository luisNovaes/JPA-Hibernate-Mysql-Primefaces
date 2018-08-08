/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.utilidades;

import br.com.aplicacao.daos.VeiculoDAO;

/**
 *
 * @author luis.silva
 */
public interface CrudVeiculo {

    public static void main(String[] args) {

        VeiculoDAO dao = new VeiculoDAO();
        //dao.salvarVeiculos();
        dao.buscarTodosVeiculos();
        //dao.buscarVeiculosPorNomeProprietario();
        //dao.buscarVeiculosPelaPlaca();
        //dao.AtualizarObservacaoVeiculo();
        //dao.AtualizarTipoVeiculo();
        //dao.AtualizarMontadoraVeiculo();
        //dao.AtualizarModeloVeiculo();
        //dao.AtualizarAnoFabricacaoVeiculo();
        //dao.AtualizarPlacaVeiculo();
        //dao.AtualizarProprietarioVeiculo();
        //dao.ExcluirVeiculo();
    }

}
