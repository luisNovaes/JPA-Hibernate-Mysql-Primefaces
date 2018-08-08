/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.daos;

import br.com.aplicacao.modelos.Escolaridade;
import br.com.aplicacao.modelos.Especialidade;
import br.com.aplicacao.modelos.Filial;
import br.com.aplicacao.modelos.Funcao;
import br.com.aplicacao.modelos.Funcionario;
import br.com.aplicacao.modelos.Pessoa;
import br.com.aplicacao.modelos.Setor;
import br.com.aplicacao.modelos.Turno;
import br.com.aplicacao.utilidades.conexao;
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
public class FuncionarioDAO extends conexao {

    public String salvar() {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

            Pessoa pessoa = manager.find(Pessoa.class, 1L);

            Funcionario funcionario = new Funcionario();

            funcionario.setFuncao(Funcao.MECÂNICO);
            funcionario.setEspecialidade(Especialidade.MECÂNICA);
            funcionario.setEscolaridade(Escolaridade.NÍVEL_MÉDIO);
            funcionario.setDataAdminissao(new Date());
            funcionario.setDataDemissao(new Date());
            funcionario.setFilial(Filial.PRIMAVERA);
            funcionario.setSetor(Setor.OFICINA);
            funcionario.setTurno(Turno.MANHÃ);
            funcionario.setPessoa(pessoa);

            manager.persist(funcionario);
            tx.commit();

        } catch (Exception e) {
            System.out.println("Erro ("
                    + ") ao tentar gravar no banco!");

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
            CriteriaQuery<Funcionario> criteriaQuery = builder.createQuery(Funcionario.class);

            Root<Funcionario> funcionario = criteriaQuery.from(Funcionario.class);
            criteriaQuery.select(funcionario);

            TypedQuery<Funcionario> query = manager.createQuery(criteriaQuery);
            List<Funcionario> funcionarios = query.getResultList();

            for (Funcionario f : funcionarios) {
                System.out.println(f.getIdfucionario() + " - "
                        + f.getPessoa().getNome() + " - "
                        + f.getPessoa().getRg() + " - "
                        + f.getPessoa().getCpf_cnpj() + " "
                        + f.getPessoa().getDatabascimento() + " - "
                        + f.getPessoa().getTelefone() + " - "
                        + f.getPessoa().getEmail() + " - "
                        + f.getPessoa().getEndereco() + " - "
                        + f.getPessoa().getCidade() + " - "
                        + f.getPessoa().getEstado() + " - "
                        + f.getPessoa().getTipoPessoa() + " - "
                        + f.getPessoa().getObservacao() + " - "
                        + f.getDataAdminissao() + " - "
                        + f.getDataDemissao() + " - "
                        + f.getEscolaridade() + " - "
                        + f.getEspecialidade() + " - "
                        + f.getFuncao() + " - "
                        + f.getFilial() + " - "
                        + f.getSetor() + " - "
                        + f.getTurno()
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

    public String buscarFuncionarioPeloNome() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Funcionario where pessoa.nome = :nome");

            query.setParameter("nome", "Maria");
            List funcionarios = query.getResultList();

            if (funcionarios.isEmpty()) {
                System.out.println("Não há Funcionario não cadastrado com este Nome!");
            } else {

                for (Object obj : funcionarios) {
                    Funcionario f = (Funcionario) obj;
                    System.out.println(f.getIdfucionario() + " - "
                            + f.getPessoa().getNome() + " - "
                            + f.getPessoa().getRg() + " - "
                            + f.getPessoa().getCpf_cnpj() + " "
                            + f.getPessoa().getDatabascimento() + " - "
                            + f.getPessoa().getTelefone() + " - "
                            + f.getPessoa().getEmail() + " - "
                            + f.getPessoa().getEndereco() + " - "
                            + f.getPessoa().getCidade() + " - "
                            + f.getPessoa().getEstado() + " - "
                            + f.getPessoa().getTipoPessoa() + " - "
                            + f.getPessoa().getObservacao() + " - "
                            + f.getDataAdminissao() + " - "
                            + f.getDataDemissao() + " - "
                            + f.getEscolaridade() + " - "
                            + f.getEspecialidade() + " - "
                            + f.getFuncao() + " - "
                            + f.getFilial() + " - "
                            + f.getSetor() + " - "
                            + f.getTurno()
                    );
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

    public String buscarFuncionarioPelaFuncao() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Funcionario where funcao = :funcao");

            query.setParameter("funcao", Funcao.GERENTE);
            List funcionarios = query.getResultList();

            if (funcionarios.isEmpty()) {
                System.out.println("Não há Funcionario cadastrado com esta funçao!");
            } else {

                for (Object obj : funcionarios) {
                    Funcionario f = (Funcionario) obj;
                    System.out.println(f.getIdfucionario() + " - "
                            + f.getPessoa().getNome() + " - "
                            + f.getPessoa().getRg() + " - "
                            + f.getPessoa().getCpf_cnpj() + " "
                            + f.getPessoa().getDatabascimento() + " - "
                            + f.getPessoa().getTelefone() + " - "
                            + f.getPessoa().getEmail() + " - "
                            + f.getPessoa().getEndereco() + " - "
                            + f.getPessoa().getCidade() + " - "
                            + f.getPessoa().getEstado() + " - "
                            + f.getPessoa().getTipoPessoa() + " - "
                            + f.getPessoa().getObservacao() + " - "
                            + f.getDataAdminissao() + " - "
                            + f.getDataDemissao() + " - "
                            + f.getEscolaridade() + " - "
                            + f.getEspecialidade() + " - "
                            + f.getFuncao() + " - "
                            + f.getFilial() + " - "
                            + f.getSetor() + " - "
                            + f.getTurno()
                    );
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

    public String buscarFuncionarioPelaFilial() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Funcionario where filial = :filial");

            query.setParameter("filial", Filial.TANGARÁ);
            List funcionarios = query.getResultList();

            if (funcionarios.isEmpty()) {
                System.out.println("Não há Funcionario cadastrado nesta filial!");
            } else {

                for (Object obj : funcionarios) {
                    Funcionario f = (Funcionario) obj;
                    System.out.println(f.getIdfucionario() + " - "
                            + f.getPessoa().getNome() + " - "
                            + f.getPessoa().getRg() + " - "
                            + f.getPessoa().getCpf_cnpj() + " "
                            + f.getPessoa().getDatabascimento() + " - "
                            + f.getPessoa().getTelefone() + " - "
                            + f.getPessoa().getEmail() + " - "
                            + f.getPessoa().getEndereco() + " - "
                            + f.getPessoa().getCidade() + " - "
                            + f.getPessoa().getEstado() + " - "
                            + f.getPessoa().getTipoPessoa() + " - "
                            + f.getPessoa().getObservacao() + " - "
                            + f.getDataAdminissao() + " - "
                            + f.getDataDemissao() + " - "
                            + f.getEscolaridade() + " - "
                            + f.getEspecialidade() + " - "
                            + f.getFuncao() + " - "
                            + f.getFilial() + " - "
                            + f.getSetor() + " - "
                            + f.getTurno()
                    );
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

    public String buscarFuncionarioPeloRG() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Funcionario where pessoa.rg = :pessoa");

            query.setParameter("pessoa", "2234565-58");
            List funcionarios = query.getResultList();

            if (funcionarios.isEmpty()) {
                System.out.println("Não há Funcionario cadastrado com este RG!");
            } else {

                for (Object obj : funcionarios) {
                    Funcionario f = (Funcionario) obj;
                    System.out.println(f.getIdfucionario() + " - "
                            + f.getPessoa().getNome() + " - "
                            + f.getPessoa().getRg() + " - "
                            + f.getPessoa().getCpf_cnpj() + " "
                            + f.getPessoa().getDatabascimento() + " - "
                            + f.getPessoa().getTelefone() + " - "
                            + f.getPessoa().getEmail() + " - "
                            + f.getPessoa().getEndereco() + " - "
                            + f.getPessoa().getCidade() + " - "
                            + f.getPessoa().getEstado() + " - "
                            + f.getPessoa().getTipoPessoa() + " - "
                            + f.getPessoa().getObservacao() + " - "
                            + f.getDataAdminissao() + " - "
                            + f.getDataDemissao() + " - "
                            + f.getEscolaridade() + " - "
                            + f.getEspecialidade() + " - "
                            + f.getFuncao() + " - "
                            + f.getFilial() + " - "
                            + f.getSetor() + " - "
                            + f.getTurno()
                    );
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

    public String buscarFuncionarioPeloCpfCnpj() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Funcionario where pessoa.cpf_cnpj = :cpf_cnpj");

            query.setParameter("cpf_cnpj", "123456789-12");
            List funcionarios = query.getResultList();

            if (funcionarios.isEmpty()) {
                System.out.println("Não há Colaborador cadastrado com este CPF ou CNPJ!");
            } else {

                for (Object obj : funcionarios) {
                    Funcionario f = (Funcionario) obj;
                    System.out.println(f.getIdfucionario() + " - "
                            + f.getPessoa().getNome() + " - "
                            + f.getPessoa().getRg() + " - "
                            + f.getPessoa().getCpf_cnpj() + " "
                            + f.getPessoa().getDatabascimento() + " - "
                            + f.getPessoa().getTelefone() + " - "
                            + f.getPessoa().getEmail() + " - "
                            + f.getPessoa().getEndereco() + " - "
                            + f.getPessoa().getCidade() + " - "
                            + f.getPessoa().getEstado() + " - "
                            + f.getPessoa().getTipoPessoa() + " - "
                            + f.getPessoa().getObservacao() + " - "
                            + f.getDataAdminissao() + " - "
                            + f.getDataDemissao() + " - "
                            + f.getEscolaridade() + " - "
                            + f.getEspecialidade() + " - "
                            + f.getFuncao() + " - "
                            + f.getFilial() + " - "
                            + f.getSetor() + " - "
                            + f.getTurno()
                    );
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
