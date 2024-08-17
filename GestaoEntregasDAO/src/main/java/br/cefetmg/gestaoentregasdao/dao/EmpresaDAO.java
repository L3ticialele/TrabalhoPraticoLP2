
package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IEmpresaDAO;
import br.cefetmg.gestaoentregasentidades.Empresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class EmpresaDAO implements IEmpresaDAO{
    @Override
    public boolean inserir(Empresa empresa) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(empresa);
            entityManager.getTransaction().commit();
            System.out.println("Empresa cadastrada!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Empresa> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Empresa> criteria
                = entityManager.getCriteriaBuilder().createQuery(Empresa.class);
        criteria.select(criteria.from(Empresa.class));
        List<Empresa> empresas = entityManager.createQuery(criteria).getResultList();

        if (!empresas.isEmpty()) {
            for (Empresa empresa : empresas) {
                System.out.print(
                        "Id: " + empresa.getId()
                        + " Nome: " + empresa.getNome()
                        + " CNPJ: " + empresa.getCnpj()
                );
            }
        }

        entityManager.close();
        return empresas;
    }

    @Override
    public boolean delete(int idEmpresa) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Empresa empresa = entityManager.find(Empresa.class, idEmpresa);

            if (empresa != null) {
                entityManager.remove(idEmpresa);
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar a empresa com o id: " + idEmpresa);
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
    public boolean atualizar(Empresa empresa) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Empresa empresaPersistida = entityManager.find(Empresa.class, empresa.getId());

            if (empresaPersistida != null) {
                empresaPersistida.setId(empresa.getId());
                empresaPersistida.setNome(empresa.getNome());
                empresaPersistida.setClientes(empresa.getClientes());
                empresaPersistida.setCnpj(empresa.getCnpj());
                empresaPersistida.setCpf(empresa.getCpf());
                empresaPersistida.setFuncionarios(empresa.getFuncionarios());
                empresaPersistida.setPorcentagemComissaoEntregador(empresa.getPorcentagemComissaoEntregador());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar a empresa com o id: " + empresa.getId());
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
    public Empresa procurarPorId(int idEmpresa) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Empresa AS e WHERE e.id =:id ");
            query.setParameter("id", idEmpresa);
            List<Empresa> empresaPersistida = query.getResultList();
            if (!empresaPersistida.isEmpty()) {
                return empresaPersistida.get(0);
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
    public Empresa procurarPorNome(String nomeEmpresa) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Empresa AS e WHERE e.nome =:nomeEmpresa");
            query.setParameter("nomeEmpresa", nomeEmpresa);
            List<Empresa> empresaPersistida = query.getResultList();
            if (!empresaPersistida.isEmpty()) {
                return empresaPersistida.get(0);
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
