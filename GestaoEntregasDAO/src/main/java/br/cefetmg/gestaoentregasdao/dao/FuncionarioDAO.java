
package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IFuncionarioDAO;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class FuncionarioDAO implements IFuncionarioDAO{
    @Override
    public boolean inserir(Funcionario funcionario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(funcionario);
            entityManager.getTransaction().commit();
            System.out.println("Funcionario cadastrado!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Funcionario> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Funcionario> criteria
                = entityManager.getCriteriaBuilder().createQuery(Funcionario.class);
        criteria.select(criteria.from(Funcionario.class));
        List<Funcionario> funcionarios = entityManager.createQuery(criteria).getResultList();

        if (!funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                System.out.print(
                        "Id: " + funcionario.getId()
                        + " Nome: " + funcionario.getNome()
                        + " Telefone: " + funcionario.getTelefone()
                );
            }
        }

        entityManager.close();
        return funcionarios;
    }

    @Override
    public boolean delete(int idCliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Funcionario funcionario = entityManager.find(Funcionario.class, idCliente);

            if (funcionario != null) {
                entityManager.remove(idCliente);
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o cliente com o id: " + idCliente);
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean atualizar(Funcionario funcionario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Funcionario funcionarioPersistido = entityManager.find(Funcionario.class, funcionario.getId());

            if (funcionarioPersistido != null) {
                funcionarioPersistido.setId(funcionario.getId());
                funcionarioPersistido.setNome(funcionario.getNome());
                funcionarioPersistido.setEmpresa(funcionario.getEmpresa());
                funcionarioPersistido.setSenha(funcionario.getSenha());
                funcionarioPersistido.setTelefone(funcionario.getTelefone());
                funcionarioPersistido.setTipoPerfil(funcionario.getTipoPerfil());
                funcionarioPersistido.setCpf(funcionario.getCpf());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o funcionario com o id: " + funcionario.getId());
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Funcionario procurarPorId(int idFuncionario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Funcionario AS f WHERE f.id =:id ");
            query.setParameter("id", idFuncionario);
            List<Funcionario> funcionarioPersistido = query.getResultList();
            if (!funcionarioPersistido.isEmpty()) {
                return funcionarioPersistido.get(0);
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

    @Override
    public Funcionario procurarPorNome(String nomeFuncionario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Funcionario AS f WHERE f.nome =:nomeFuncionario");
            query.setParameter("nomeFuncionario", nomeFuncionario);
            List<Funcionario> funcionarioPersistido = query.getResultList();
            if (!funcionarioPersistido.isEmpty()) {
                return funcionarioPersistido.get(0);
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
    
    @Override
    public Funcionario procurarPorCpf(String cpf) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Funcionario AS f WHERE f.cpf =:cpf ");
            query.setParameter("cpf", cpf);
            List<Funcionario> funcionarioPersistido = query.getResultList();
            if (!funcionarioPersistido.isEmpty()) {
                return funcionarioPersistido.get(0);
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
