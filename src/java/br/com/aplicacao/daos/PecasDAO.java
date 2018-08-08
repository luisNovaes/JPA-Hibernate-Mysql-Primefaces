/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.daos;

import br.com.aplicacao.modelos.Agendamento;
import br.com.aplicacao.modelos.Pecas;
import br.com.aplicacao.utilidades.conexao;
import java.math.BigDecimal;
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
public class PecasDAO extends conexao {

    public String salvar() {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            Agendamento agendamento = manager.find(Agendamento.class, 1L);

            Pecas pecas = new Pecas();
            pecas.setNome("Farol dianteriro");
            pecas.setModelo("45AC");
            pecas.setFabricante("Moura");
            pecas.setValorPeca(new BigDecimal(100));
            pecas.setObservacao("testada e ok");
            pecas.setAgendamento(agendamento);

            manager.persist(pecas);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar inserir dados no banco");

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
            CriteriaQuery<Pecas> criteriaQuery = builder.createQuery(Pecas.class);

            Root<Pecas> pecas = criteriaQuery.from(Pecas.class);
            criteriaQuery.select(pecas);

            TypedQuery<Pecas> query = manager.createQuery(criteriaQuery);
            List<Pecas> pecass = query.getResultList();

            for (Pecas pe : pecass) {
                System.out.println(pe.getIdPeca() + " - "
                        + pe.getNome() + " - "
                        + pe.getModelo() + " - "
                        + pe.getFabricante() + " - "
                        + pe.getValorPeca() + " - "
                        + pe.getObservacao()
                );
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar pessoa no banco!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String buscarPecaPorNome() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pecas where nome = :nome");

            query.setParameter("nome", "Lâmpada");
            List pecass = query.getResultList();

            if (pecass.isEmpty()) {
                System.out.println("Peça não cadastrada!");
            } else {

                for (Object obj : pecass) {
                    Pecas p = (Pecas) obj;

                    System.out.println(p.getIdPeca() + " - - "
                            + p.getNome() + " - "
                            + p.getModelo() + " - "
                            + p.getFabricante() + " - "
                            + p.getValorPeca() + " - "
                            + p.getAgendamento().getIdOS() + " - "
                            + p.getAgendamento().getFuncionarios().getPessoa().getNome() + " - "
                            + p.getAgendamento().getVeiculo().getPlaca() + " - "
                            + p.getObservacao()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Peças!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarPecaPorFabricante() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pecas where fabricante = :fabricante");

            query.setParameter("fabricante", "Siemens");
            List pecass = query.getResultList();

            if (pecass.isEmpty()) {
                System.out.println("Fabricante não cadastrado!");
            } else {

                for (Object obj : pecass) {
                    Pecas p = (Pecas) obj;

                    System.out.println(p.getIdPeca() + " - - "
                            + p.getNome() + " - "
                            + p.getModelo() + " - "
                            + p.getFabricante() + " - "
                            + p.getValorPeca() + " - "
                            + p.getAgendamento().getIdOS() + " - "
                            + p.getAgendamento().getFuncionarios().getPessoa().getNome() + " - "
                            + p.getAgendamento().getVeiculo().getPlaca() + " - "
                            + p.getObservacao()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Fabricante!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarPecaPorModelo() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pecas where modelo = :modelo");

            query.setParameter("modelo", "AC");
            List pecass = query.getResultList();

            if (pecass.isEmpty()) {
                System.out.println("Modelo não cadastrado!");
            } else {

                for (Object obj : pecass) {
                    Pecas p = (Pecas) obj;

                    System.out.println(p.getIdPeca() + " - - "
                            + p.getNome() + " - "
                            + p.getModelo() + " - "
                            + p.getFabricante() + " - "
                            + p.getValorPeca() + " - "
                            + p.getAgendamento().getIdOS() + " - "
                            + p.getAgendamento().getFuncionarios().getPessoa().getNome() + " - "
                            + p.getAgendamento().getVeiculo().getPlaca() + " - "
                            + p.getObservacao()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Modelo!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarPecaPorValor() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pecas where valorPeca = :valorPeca");

            query.setParameter("valorPeca", new BigDecimal(400.0));
            List pecass = query.getResultList();

            if (pecass.isEmpty()) {
                System.out.println("Preço não cadastrado!");
            } else {

                for (Object obj : pecass) {
                    Pecas p = (Pecas) obj;

                    System.out.println(p.getIdPeca() + " - "
                            + p.getNome() + " - "
                            + p.getModelo() + " - "
                            + p.getFabricante() + " - "
                            + p.getValorPeca() + " - "
                            + p.getAgendamento().getIdOS() + " - "
                            + p.getAgendamento().getFuncionarios().getPessoa().getNome() + " - "
                            + p.getAgendamento().getVeiculo().getPlaca() + " - "
                            + p.getObservacao()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Preço da peça!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }
}
