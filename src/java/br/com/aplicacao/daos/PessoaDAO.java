/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.daos;

import br.com.aplicacao.modelos.Estado;
import br.com.aplicacao.modelos.Pessoa;
import br.com.aplicacao.modelos.TipoPessoa;
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
public class PessoaDAO extends conexao {

    public String salvar() {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome("Alana");
            pessoa.setDatabascimento(new Date());
            pessoa.setRg("045865258-55");
            pessoa.setTipoPessoa(TipoPessoa.FISICA);
            pessoa.setCpf_cnpj("123456789-12");
            pessoa.setEmail("fernando@gmail.com");
            pessoa.setTelefone("(65)999275216");
            pessoa.setEndereco("Rua das Arvores, numero 1000");
            pessoa.setCidade("São Paulo");
            pessoa.setEstado(Estado.SP);
            pessoa.setObservacao("cliente em trânsito");

            manager.persist(pessoa);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar gravar Pessoa no banco!");
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
            CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);

            Root<Pessoa> pessoa = criteriaQuery.from(Pessoa.class);
            criteriaQuery.select(pessoa);

            TypedQuery<Pessoa> query = manager.createQuery(criteriaQuery);
            List<Pessoa> pessoas = query.getResultList();

            for (Pessoa p : pessoas) {
                System.out.println(p.getIdPessoa() + " - "
                        + p.getNome() + " - "
                        + p.getDatabascimento() + " - "
                        + p.getRg() + " - "
                        + p.getTipoPessoa() + " - "
                        + p.getCpf_cnpj() + " - "
                        + p.getEmail() + " - "
                        + p.getTelefone() + " - "
                        + p.getEndereco() + " - "
                        + p.getCidade() + " - "
                        + p.getEstado() + " - "
                        + p.getObservacao()
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

    public String buscarPorNome() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pessoa where nome = :nome");

            query.setParameter("nome", "Kawan");
            List pessoas = query.getResultList();

            if (pessoas.isEmpty()) {
                System.out.println("Pessoa não cadastrada!");
            } else {

                for (Object obj : pessoas) {
                    Pessoa p = (Pessoa) obj;

                    System.out.println(p.getIdPessoa() + " -"
                            + p.getNome() + " - "
                            + p.getRg() + " - "
                            + p.getCpf_cnpj() + " - "
                            + p.getTelefone() + " - "
                            + p.getEmail() + " - "
                            + p.getDatabascimento() + " - "
                            + p.getEndereco() + " - "
                            + p.getCidade() + " - "
                            + p.getEstado() + " - "
                            + p.getObservacao());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarPorCpfCnpj() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pessoa where cpf_cnpj = :cpf_cnpj");

            query.setParameter("cpf_cnpj", "123456789-12");
            List pessoas = query.getResultList();

            if (pessoas.isEmpty()) {
                System.out.println("CPF ou CNPJ não cadastrado!");
            } else {

                for (Object obj : pessoas) {
                    Pessoa p = (Pessoa) obj;

                    System.out.println(p.getIdPessoa() + " -"
                            + p.getNome() + " - "
                            + p.getRg() + " - "
                            + p.getCpf_cnpj() + " - "
                            + p.getTelefone() + " - "
                            + p.getEmail() + " - "
                            + p.getDatabascimento() + " - "
                            + p.getEndereco() + " - "
                            + p.getCidade() + " - "
                            + p.getEstado() + " - "
                            + p.getObservacao());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String buscarPorRg() {
        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Pessoa where rg= :rg");

            query.setParameter("rg", "1234565-58");
            List pessoas = query.getResultList();

            if (pessoas.isEmpty()) {
                System.out.println("RG não cadastrado!");
            } else {

                for (Object obj : pessoas) {
                    Pessoa p = (Pessoa) obj;

                    System.out.println(p.getIdPessoa() + " -"
                            + p.getNome() + " - "
                            + p.getRg() + " - "
                            + p.getCpf_cnpj() + " - "
                            + p.getTelefone() + " - "
                            + p.getEmail() + " - "
                            + p.getDatabascimento() + " - "
                            + p.getEndereco() + " - "
                            + p.getCidade() + " - "
                            + p.getEstado() + " - "
                            + p.getObservacao());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar Pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String AtualizarPessoaTelefone() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa pessoa = manager.find(Pessoa.class, 2L);

        System.out.println("Telefone atual: " + pessoa.getTelefone());
        pessoa.setTelefone("(88)888888888");
        System.out.println("Novo telefone: " + pessoa.getTelefone());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarPessoaEndereco() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa pessoa = manager.find(Pessoa.class, 2L);

        System.out.println("Endereço atual: " + pessoa.getEndereco());
        pessoa.setEndereco("Rua do eclipse lunar de 2018, numero 0001");
        System.out.println("Novo endereço: " + pessoa.getEndereco());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarPessoaEmail() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa pessoa = manager.find(Pessoa.class, 2L);

        System.out.println("Email atual: " + pessoa.getEmail());
        pessoa.setEmail("emailatualizado@gmail.com");
        System.out.println("Novo email: " + pessoa.getEmail());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarPessoaCidade() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa pessoa = manager.find(Pessoa.class, 2L);

        System.out.println("Email atual: " + pessoa.getCidade());
        pessoa.setCidade("Maceió");
        System.out.println("Novo email: " + pessoa.getCidade());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarPessoaEstado() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa pessoa = manager.find(Pessoa.class, 2L);

        System.out.println("Email atual: " + pessoa.getEstado());
        pessoa.setEstado(Estado.AL);
        System.out.println("Novo email: " + pessoa.getEstado());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String AtualizarPessoaObservacao() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Pessoa pessoa = manager.find(Pessoa.class, 2L);

        System.out.println("Email atual: " + pessoa.getObservacao());
        pessoa.setObservacao("Esta pessoa mudou para Maceió");
        System.out.println("Novo email: " + pessoa.getObservacao());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        } finally {
            manager.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

    public String ExcluirPessoa() {
        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        long id = 6L;

        try {
            Pessoa pessoa = manager.find(Pessoa.class, id);
            manager.remove(pessoa);
            tx.commit();
            System.out.println(" Pessoa excluida com sucesso!");

        } catch (Exception e) {

            System.out.println("Esta pessoa não pode ser excluida, "
                    + "pos está vinculada a algum cadastro ou não existe no sistema!");

        } finally {
            manager.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }
}
