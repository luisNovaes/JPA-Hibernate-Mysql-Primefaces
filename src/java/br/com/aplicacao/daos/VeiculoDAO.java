/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.daos;

import br.com.aplicacao.modelos.Montadora;
import br.com.aplicacao.modelos.Pessoa;
import br.com.aplicacao.modelos.TipoVeiculo;
import br.com.aplicacao.modelos.Veiculo;
import br.com.aplicacao.utilidades.conexao;
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
public class VeiculoDAO extends conexao {

    public String salvarVeiculos() {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

            Pessoa proprietario = manager.find(Pessoa.class, 3L);

            Veiculo veiculo = new Veiculo();
            veiculo.setPropietario(proprietario);
            veiculo.setPlaca("WTR-8569");
            veiculo.setTipoveiculo(TipoVeiculo.OUTROS);
            veiculo.setMontadora(Montadora.GM);
            veiculo.setModelo("Celta");
            veiculo.setAnoFabricacao(2008);
            veiculo.setObservacao("Veiculo Chegou rebocado com problema elétrico");

            manager.persist(veiculo);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar salver no banco!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String buscarTodosVeiculos() {
        EntityManager manager = conexao.getEntityManager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.select(veiculo);

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getIdVeiculo() + " - "
                        + v.getModelo() + " - "
                        + v.getPlaca() + " - "
                        + v.getAnoFabricacao() + " - "
                        + v.getTipoveiculo() + " - "
                        + v.getMontadora() + " - "
                        + v.getAnoFabricacao() + " - "
                        + v.getPropietario().getNome() + " - "
                        + v.getPropietario().getRg() + " - "
                        + v.getPropietario().getCpf_cnpj() + " - "
                        + v.getPropietario().getTelefone() + " - "
                        //                        + v.getAgendamento().getDataAgenda() + " - "
                        //                        + v.getAgendamento().getHoraAgenda() + " - "
                        + v.getObservacao());
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

    public String buscarVeiculosPorNomeProprietario() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Veiculo where propietario.nome = :propietario");

            query.setParameter("propietario", "Alana");
            List veiculos = query.getResultList();

            if (veiculos.isEmpty()) {
                System.out.println(
                        "Pessoa não possui veículo cadastrado no sistema!");
            } else {

                for (Object obj : veiculos) {
                    Veiculo v = (Veiculo) obj;
                    System.out.println(v.getIdVeiculo() + " - "
                            + v.getModelo() + " - "
                            + v.getPlaca() + " - "
                            + v.getAnoFabricacao() + " - "
                            + v.getTipoveiculo() + " - "
                            + v.getMontadora() + " - "
                            + v.getAnoFabricacao() + " - "
                            + v.getPropietario().getNome() + " - "
                            + v.getPropietario().getRg() + " - "
                            + v.getPropietario().getCpf_cnpj() + " - "
                            + v.getPropietario().getTelefone() + " - "
                            + v.getAgendamento().getDataAgenda() + " - "
                            + v.getAgendamento().getHoraAgenda() + " - "
                            + v.getObservacao());
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

    public String buscarVeiculosPelaPlaca() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Veiculo where placa = :placa");

            query.setParameter("placa", "DEF-1234");
            List veiculos = query.getResultList();

            if (veiculos.isEmpty()) {
                System.out.println("Veículo não cadastrado com esta Placa!");
            } else {

                for (Object obj : veiculos) {
                    Veiculo v = (Veiculo) obj;

                    System.out.println(v.getIdVeiculo() + " - "
                            + v.getModelo() + " - "
                            + v.getPlaca() + " - "
                            + v.getAnoFabricacao() + " - "
                            + v.getTipoveiculo() + " - "
                            + v.getMontadora() + " - "
                            + v.getAnoFabricacao() + " - "
                            + v.getPropietario().getNome() + " - "
                            + v.getPropietario().getRg() + " - "
                            + v.getPropietario().getCpf_cnpj() + " - "
                            + v.getPropietario().getTelefone() + " - "
                            + v.getAgendamento().getDataAgenda() + " - "
                            + v.getAgendamento().getHoraAgenda() + " - "
                            + v.getObservacao());
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

    public String AtualizarObservacaoVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Observaçao atual: " + veiculo.getObservacao());
        veiculo.setObservacao(" Esta é a nova observação de veiculo2!");
        System.out.println("Nova Observaçao: " + veiculo.getObservacao());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarTipoVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Tipo atual: " + veiculo.getTipoveiculo());
        veiculo.setTipoveiculo(TipoVeiculo.OUTROS);
        System.out.println("Novo Tipo: " + veiculo.getTipoveiculo());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarMontadoraVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Montadora atual: " + veiculo.getMontadora());
        veiculo.setMontadora(Montadora.GM);
        System.out.println("Nova Montadora: " + veiculo.getMontadora());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarModeloVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Modelo atual: " + veiculo.getModelo());
        veiculo.setModelo("Celta");
        System.out.println("Novo Modelo: " + veiculo.getModelo());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarAnoFabricacaoVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Ano Fabricacao atual: " + veiculo.getAnoFabricacao());
        veiculo.setAnoFabricacao(1975);
        System.out.println("Ano Fabricacao depois: " + veiculo.getAnoFabricacao());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarPlacaVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Placa atual: " + veiculo.getPlaca());
        veiculo.setPlaca("DTS-1975");
        System.out.println("Placa depois: " + veiculo.getPlaca());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarProprietarioVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa proprietario = manager.find(Pessoa.class, 5L);

        Veiculo veiculo = manager.find(Veiculo.class, 2L);

        System.out.println("Proprietario atual: " + veiculo.getPropietario());
        veiculo.setPropietario(proprietario);
        System.out.println("Proprietario depois: " + veiculo.getPropietario());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veiculo!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String ExcluirVeiculo() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        long id = 8L;

        try {
            Veiculo veiculo = manager.find(Veiculo.class, id);
            manager.remove(veiculo);
            tx.commit();
            System.out.println(" Veiculo excluida com sucesso!");

        } catch (Exception e) {

            System.out.println("Este veiculo não pode ser excluido, "
                    + "pos está vinculada a algum cadastro ou não existe no sistema!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }
}
