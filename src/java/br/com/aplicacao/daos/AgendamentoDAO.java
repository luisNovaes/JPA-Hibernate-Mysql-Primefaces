/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.daos;

import br.com.aplicacao.modelos.Agendamento;
import br.com.aplicacao.modelos.Funcionario;
import br.com.aplicacao.modelos.HoraAgenda;
import br.com.aplicacao.modelos.Pecas;
import br.com.aplicacao.modelos.TipoServico;
import br.com.aplicacao.modelos.Veiculo;
import br.com.aplicacao.utilidades.conexao;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luis.silva
 */
public class AgendamentoDAO extends conexao {

    public String salvar() {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

            Funcionario funcionario = manager.find(Funcionario.class, 1L);
            Pecas pecas = manager.find(Pecas.class, 2L);
            Veiculo veiculo = manager.find(Veiculo.class, 1L);

            Agendamento agenda = new Agendamento();

            agenda.setVeiculo(veiculo);
            agenda.setDataAgenda(new Date());
            agenda.setHoraAgenda(HoraAgenda.VINTE_HORAS);
            agenda.setFuncionarios(funcionario);
            agenda.setPecas(pecas);
            agenda.setTipoServico(TipoServico.LIMPEZA);
            agenda.setValorTotalServico(new BigDecimal(200));
            agenda.setObservacao("Fazer orçamento de troca de bateria");

            manager.persist(agenda);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar gravar dadosno banco!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String buscarTodos() {
        EntityManager manager = conexao.getEntityManager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Agendamento> criteriaQuery = builder.createQuery(Agendamento.class);

            Root<Agendamento> agendamento = criteriaQuery.from(Agendamento.class);
            criteriaQuery.select(agendamento);

            TypedQuery<Agendamento> query = manager.createQuery(criteriaQuery);
            List<Agendamento> agendamentos = query.getResultList();

            for (Agendamento a : agendamentos) {
                System.out.println(a.getDataAgenda() + " - "
                        + a.getHoraAgenda() + " - "
                        + a.getVeiculo().getPlaca() + " - "
                        + a.getVeiculo().getMontadora() + " - "
                        + a.getVeiculo().getModelo() + " - "
                        + a.getVeiculo().getTipoveiculo() + " - "
                        + a.getVeiculo().getObservacao() + " - "
                        + a.getVeiculo().getPropietario().getNome() + " - "
                        + a.getVeiculo().getPropietario().getCpf_cnpj() + " - "
                        + a.getVeiculo().getPropietario().getTelefone() + " - "
                        + a.getVeiculo().getPropietario().getEmail() + " - "
                        + a.getTipoServico() + " - "
                        + a.getFuncionarios().getPessoa().getNome() + " - "
                        + a.getPecas().getNome() + " - "
                        + a.getValorTotalServico() + " - "
                        + a.getObservacao().toUpperCase()
                );
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar veículo no banco!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String buscarAgendamentoPorData() throws ParseException {
        EntityManager manager = conexao.getEntityManager();

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        String userdata = "2018-07-30";
        Date novadata = data.parse(userdata);

        try {
            Query query = manager.createQuery(
                    " from Agendamento where dataAgenda = :dataAgenda");

            query.setParameter("dataAgenda", novadata);
            List Agendamento = query.getResultList();

            if (Agendamento.isEmpty()) {
                System.out.println("Não há Agendamento cadastrado para esta data!");
            } else {

                for (Object obj : Agendamento) {
                    Agendamento a = (Agendamento) obj;
                    System.out.println(a.getDataAgenda() + " - "
                            + a.getHoraAgenda() + " - "
                            + a.getVeiculo().getPlaca() + " - "
                            + a.getVeiculo().getMontadora() + " - "
                            + a.getVeiculo().getModelo() + " - "
                            + a.getVeiculo().getTipoveiculo() + " - "
                            + a.getVeiculo().getObservacao() + " - "
                            + a.getVeiculo().getPropietario().getNome() + " - "
                            + a.getVeiculo().getPropietario().getCpf_cnpj() + " - "
                            + a.getVeiculo().getPropietario().getTelefone() + " - "
                            + a.getVeiculo().getPropietario().getEmail() + " - "
                            + a.getTipoServico() + " - "
                            + a.getFuncionarios().getPessoa().getNome() + " - "
                            + a.getPecas().getNome() + " - "
                            + a.getValorTotalServico() + " - "
                            + a.getObservacao().toUpperCase());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Veículo!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarAgendamentoPorHora() throws ParseException {
        EntityManager manager = conexao.getEntityManager();

        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        String userdata = "2018-07-21";
        Date novadata = data.parse(userdata);

        try {
            Query query = manager.createQuery(
                    " from Agendamento where horaAgenda = :horaAgenda");

            query.setParameter("horaAgenda", HoraAgenda.OITO_HORAS);
            List Agendamento = query.getResultList();

            if (Agendamento.isEmpty()) {
                System.out.println("Não há Agendamento cadastrado para este horário!");
            } else {

                for (Object obj : Agendamento) {
                    Agendamento a = (Agendamento) obj;
                    System.out.println(a.getDataAgenda() + " - "
                            + a.getHoraAgenda() + " - "
                            + a.getVeiculo().getPlaca() + " - "
                            + a.getVeiculo().getMontadora() + " - "
                            + a.getVeiculo().getModelo() + " - "
                            + a.getVeiculo().getTipoveiculo() + " - "
                            + a.getVeiculo().getObservacao() + " - "
                            + a.getVeiculo().getPropietario().getNome() + " - "
                            + a.getVeiculo().getPropietario().getCpf_cnpj() + " - "
                            + a.getVeiculo().getPropietario().getTelefone() + " - "
                            + a.getVeiculo().getPropietario().getEmail() + " - "
                            + a.getTipoServico() + " - "
                            + a.getFuncionarios().getPessoa().getNome() + " - "
                            + a.getPecas().getNome() + " - "
                            + a.getValorTotalServico() + " - "
                            + a.getObservacao().toUpperCase());

                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Veículo!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarAgendamentoPelaPlaca() throws ParseException {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Agendamento where veiculo.placa = :placa");

            query.setParameter("placa", "WTR-8569");
            List Agendamento = query.getResultList();

            if (Agendamento.isEmpty()) {
                System.out.println("Não há Agendamento cadastrado com esta placa de veículo!");
            } else {

                for (Object obj : Agendamento) {
                    Agendamento a = (Agendamento) obj;
                    System.out.println(a.getDataAgenda() + " - "
                            + a.getHoraAgenda() + " - "
                            + a.getVeiculo().getPlaca() + " - "
                            + a.getVeiculo().getMontadora() + " - "
                            + a.getVeiculo().getModelo() + " - "
                            + a.getVeiculo().getTipoveiculo() + " - "
                            + a.getVeiculo().getObservacao() + " - "
                            + a.getVeiculo().getPropietario().getNome() + " - "
                            + a.getVeiculo().getPropietario().getCpf_cnpj() + " - "
                            + a.getVeiculo().getPropietario().getTelefone() + " - "
                            + a.getVeiculo().getPropietario().getEmail() + " - "
                            + a.getVeiculo().getPropietario().getObservacao() + " - "
                            + a.getTipoServico() + " - "
                            + a.getFuncionarios().getPessoa().getNome() + " - "
                            + a.getPecas().getNome() + " - "
                            + a.getValorTotalServico() + " - "
                            + a.getObservacao().toUpperCase());

                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Veículo!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

}
