
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IFuncionarioDAO;
import br.cefetmg.gestaoentregasentidades.Empresa;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private final IFuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Funcionario funcionario;
    
    public boolean cadastrarFuncionario(String nome, String senha, String telefone, Empresa empresa, String perfil,String cpf) throws PersistenciaException{
        funcionario = new Funcionario( nome,  senha,  telefone,  empresa,  perfil, cpf);
        return funcionarioDAO.inserir(funcionario);
    }
    
    public List<Funcionario> listarFuncionarios() throws PersistenciaException{
        return funcionarioDAO.listarTodos();
    }
    
    public ArrayList<Funcionario> atualizaDadosCliente(int ultimoFuncionario) throws PersistenciaException{
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        String nome, telefone,tipoPerfil;
        List<Funcionario> funcionarios = listarFuncionarios();
        for (int i=ultimoFuncionario; i<funcionarios.size(); i++){
            nome = funcionarios.get(i).getNome();
            telefone = funcionarios.get(i).getTelefone();
            tipoPerfil = funcionarios.get(i).getTipoPerfil().toString();
            funcionario = new Funcionario( nome,  null,  telefone,  null, tipoPerfil , null);
            listaFuncionarios.add(funcionario);
        }
        return listaFuncionarios;
    }
    
    public Funcionario buscarFuncionarioPorNome(String nome) throws PersistenciaException{
        return funcionarioDAO.procurarPorNome(nome);
    }
    
    public ArrayList<String> nomeFuncionarios(List<Funcionario> funcionarios){
        ArrayList<String> nomeFuncionarios = new ArrayList<>();
        for(int i=0; i<funcionarios.size(); i++){
            nomeFuncionarios.add(funcionarios.get(i).getNome());
        }
        return nomeFuncionarios;
    }
}

