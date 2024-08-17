
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Empresa;
import java.util.List;

public interface IEmpresaDAO {
    boolean inserir(Empresa empresa) throws PersistenciaException;

    boolean atualizar(Empresa empresa) throws PersistenciaException;

    boolean delete(int idEmpresa) throws PersistenciaException;

    List<Empresa> listarTodos() throws PersistenciaException;
    
    Empresa procurarPorId(int idEmpresa) throws PersistenciaException;
    
    Empresa procurarPorNome(String nomeEmpresa) throws PersistenciaException;
}
