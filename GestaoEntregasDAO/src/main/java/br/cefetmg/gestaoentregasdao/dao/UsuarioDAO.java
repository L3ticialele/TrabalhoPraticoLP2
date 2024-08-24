package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.interfaces.IUsuarioDAO;
import br.cefetmg.gestaoentregasentidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public boolean validarLogin(Usuario usuario) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u.telefone, u.senha FROM Usuario AS u WHERE u.telefone = :telefone AND u.senha = :senha");
            query.setParameter("telefone", usuario.getTelefone());
            query.setParameter("senha", usuario.getSenha());
            List<Usuario> usuarioPersistido = query.getResultList();
            return !usuarioPersistido.isEmpty();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Usuario procurarPorTelefone(String telefone) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Usuario AS u WHERE u.telefone =:telefone ");
            query.setParameter("telefone", telefone);
            List<Usuario> usuarioPersistido = query.getResultList();
            if (!usuarioPersistido.isEmpty()) {
                return usuarioPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
}
