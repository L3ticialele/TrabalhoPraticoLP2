
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IFuncionarioDAO;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private final IFuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Funcionario funcionario;
    
    public boolean cadastrarFuncionario(String nome, String senha, String telefone, String perfil,String cpf) throws PersistenciaException{
        funcionario = new Funcionario( nome,  senha,  telefone,  perfil, cpf);
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
            funcionario = new Funcionario( nome,  null,  telefone, tipoPerfil , null);
            listaFuncionarios.add(funcionario);
        }
        return listaFuncionarios;
    }
    
    public Funcionario buscarFuncionarioPorNome(String nome) throws PersistenciaException{
        return funcionarioDAO.procurarPorNome(nome);
    }
    
    public ArrayList<String> nomeEntregadores(List<Funcionario> funcionarios){
        ArrayList<String> nomeEntregadores = new ArrayList<>();
        for(int i=0; i<funcionarios.size(); i++){
            if(funcionarios.get(i).getTipoPerfil() == Perfil.ENTREGADOR){
                nomeEntregadores.add(funcionarios.get(i).getNome());
            }
        }
        return nomeEntregadores;
    }
    
    
}

