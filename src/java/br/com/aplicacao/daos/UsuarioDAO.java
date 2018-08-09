/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.daos;

import br.com.aplicacao.modelos.Usuario;
import br.com.aplicacao.utilidades.conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luis.silva
 */
public class UsuarioDAO {

    EntityManager manager = conexao.getEntityManager();
    EntityTransaction tx = manager.getTransaction();

    Usuario usuario = new Usuario();

    public String buscarTodos() {

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);

            Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
            criteriaQuery.select(usuario);

            TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
            List<Usuario> Usuarios = query.getResultList();

            for (Usuario user : Usuarios) {
                System.out.println(user.getUsuarioId() + " - "
                        + user.getNome() + " - "
                        + user.getSenha() + " - "
                        + user.getUltimoAcesso());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar pessoa no banco!");

        } finally {
            //manager.close();
            //conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public Usuario getUsuario(String nomeUsuario, String senha) {

        try {
            this.usuario = (Usuario) manager
                    .createQuery(
                            "SELECT u from Usuario u where u.nome = :name and u.senha = :senha")
                    .setParameter("name", nomeUsuario)
                    .setParameter("senha", senha).getSingleResult();
            return usuario;

        } catch (NoResultException e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar usuario no sistema!");
            return null;

        } finally {
//            manager.close();
//            conexao.close();
        }
    }

    public String inserirUsuario() {

        try {
            tx.begin();

            this.usuario.setNome("magno");
            this.usuario.setSenha("1234");
            manager.persist(this.usuario);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar inserir usuario no sistema!");

        }
        return "Operação realizada com sucesso!";
    }

    public boolean deletarUsuario(Usuario usuario) {

        try {
            manager.remove(usuario);
            return true;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar deletar usuario no sistema!");
            return false;

        } finally {
            //     manager.close();
            //    conexao.close();
        }
    }
}
