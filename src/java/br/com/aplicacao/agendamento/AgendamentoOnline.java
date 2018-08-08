/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.agendamento;

import br.com.aplicacao.modelos.Usuario;
import br.com.aplicacao.utilidades.conexao;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
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
@WebService(serviceName = "AgendamentoOnline")
@Stateless()
public class AgendamentoOnline {

    /**
     * Operação de SALVAR
     */
    @WebMethod(operationName = "salvar")
    public String salvar(@WebParam(name = "nome") String nome,
            @WebParam(name = "senha") String senha) {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

            Usuario user = new Usuario();

            user.setNome(nome);
            user.setSenha(senha);

            manager.persist(user);
            tx.commit();

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar cadastrar usuário no sistema ");

        }

        return "Usuário cadastrado com sucesso no sistema.";
    }

    /**
     * Operação de LISTAR TODOS
     */
    @WebMethod(operationName = "listarTodosusuarios")
    public String listarTodosusuarios() {

        EntityManager manager = conexao.getEntityManager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);

            Root<Usuario> user = criteriaQuery.from(Usuario.class);
            criteriaQuery.select(user);

            TypedQuery<Usuario> query = manager.createQuery(criteriaQuery);
            List<Usuario> usuarios = query.getResultList();

            for (Usuario users : usuarios) {
                System.out.println(users.getUsuarioId() + " - "
                        + users.getNome() + " - "
                        + users.getSenha() + " - "
                );
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao listar pessoas no banco!");

        }
        return "Usuários listados com sucesso.";
    }

    /**
     * Operação BUSCAR PELO NOME
     */
    @WebMethod(operationName = "buscarPorNome")
    public String buscarPorNome(@WebParam(name = "nome") String nome) {

        EntityManager manager = conexao.getEntityManager();

        try {
            Query query = manager.createQuery(
                    " from Usuario where nome = :nome");

            query.setParameter("nome", nome);
            List usuarios = query.getResultList();

            if (usuarios.isEmpty()) {
                System.out.println("Usuário não cadastrado!");
            } else {

                for (Object obj : usuarios) {
                    Usuario user = (Usuario) obj;

                    System.out.println(user.getUsuarioId() + " - - "
                            + user.getNome() + " - "
                            + user.getSenha()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar buscar usuário!");

        }

        return "Opreração realizada com sucesso!";
    }

    /**
     * Operação de EXCLUIR PELO ID
     */
    @WebMethod(operationName = "excluirUsuario")
    public String excluirUsuario(@WebParam(name = "id") Long id) {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Long user = id;

        try {
            Usuario usuario = manager.find(Usuario.class, user);
            manager.remove(usuario);
            tx.commit();
            System.out.println("Usuario excluido com sucesso!");

        } catch (Exception e) {

            System.out.println("Este usuario não pode ser excluido, "
                    + "pos está vinculada a algum cadastro ou não existe no sistema!");

        }
        return "Operação realizada com sucesso!";
    }

    @WebMethod(operationName = "AtualizarUsuarioNome")
    public String AtualizarUsuarioNome(@WebParam(name = "id") Long id,
            @WebParam(name = "nome") String nome,
            @WebParam(name = "senha") String senha) {

        EntityManager manager = conexao.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Usuario usuario = manager.find(Usuario.class, id);

        System.out.println("Nome atual: " + usuario.getNome());
        usuario.setNome(nome);
        System.out.println("Nome atualizado: " + usuario.getNome());

        System.out.println("Senha atual: " + usuario.getNome());
        usuario.setSenha(senha);
        System.out.println("Senha atualizada: " + usuario.getNome());

        tx.commit();

        try {

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa!");

        }
        return "Opearação realizada com sucesso!";
    }
}
