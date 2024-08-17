package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IClienteDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ClienteDAO implements IClienteDAO {

    @Override
    public boolean inserir(Cliente cliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
            System.out.println("Cliente cadastrado!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Cliente> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Cliente> criteria
                = entityManager.getCriteriaBuilder().createQuery(Cliente.class);
        criteria.select(criteria.from(Cliente.class));
        List<Cliente> clientes = entityManager.createQuery(criteria).getResultList();

        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.print(
                        "Id: " + cliente.getId()
                        + " Nome: " + cliente.getNome()
                        + " Telefone: " + cliente.getTelefone()
                );
            }
        }

        entityManager.close();
        return clientes;
    }

    @Override
    public boolean delete(int idCliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Cliente cliente = entityManager.find(Cliente.class, idCliente);

            if (cliente != null) {
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
    public boolean atualizar(Cliente cliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Cliente clientePersistido = entityManager.find(Cliente.class, cliente.getId());

            if (clientePersistido != null) {
                clientePersistido.setId(cliente.getId());
                clientePersistido.setNome(cliente.getNome());
                clientePersistido.setLogradouro(cliente.getLogradouro());
                clientePersistido.setBairro(cliente.getBairro());
                clientePersistido.setCnpj(cliente.getCnpj());
                clientePersistido.setCpf(cliente.getCpf());
                clientePersistido.setEmpresa(cliente.getEmpresa());
                clientePersistido.setPedidos(cliente.getPedidos());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o cliente com o id: " + cliente.getId());
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
    public Cliente procurarPorId(int idCliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Cliente AS c WHERE c.id =:id ");
            query.setParameter("id", idCliente);
            List<Cliente> clientePersistido = query.getResultList();
            if (!clientePersistido.isEmpty()) {
                return clientePersistido.get(0);
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
    public Cliente procurarPorNome(String nomeCliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Cliente AS c WHERE c.nome =:nomeCliente");
            query.setParameter("nomeCliente", nomeCliente);
            List<Cliente> clientePersistido = query.getResultList();
            if (!clientePersistido.isEmpty()) {
                return clientePersistido.get(0);
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
