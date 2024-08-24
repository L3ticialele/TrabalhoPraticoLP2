
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IFuncionarioDAO;
import br.cefetmg.gestaoentregasentidades.Empresa;
import br.cefetmg.gestaoentregasentidades.Funcionario;

public class FuncionarioController {
    public boolean cadastrarFuncionario(String nome, String senha, String telefone, Empresa empresa, String perfil,String cpf) throws PersistenciaException{
        IFuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario( nome,  senha,  telefone,  empresa,  perfil, cpf);
        return funcionarioDAO.inserir(funcionario);
    }
}
