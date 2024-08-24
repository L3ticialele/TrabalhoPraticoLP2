package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.UsuarioDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IUsuarioDAO;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Usuario;

public class UsuarioController {

    private final IUsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario login(String cpf, String senha) throws PersistenciaException {
        if (!usuarioDAO.listarTodos().isEmpty()) {
            Usuario usuario = usuarioDAO.procurarPorCPF(cpf);
            if (usuario!=null && usuario.getSenha().equals(senha)) {
                if(usuarioDAO.validarLogin(usuario)){
                    return usuario;
                }
            }
        }
        return null;
    }

    public String direcionarTela(Usuario usuario) {
        String tela = null;
        switch (usuario.getTipo()) {
            case "Cliente" ->
                tela = "TelaCliente";
            case "Funcionario" -> {
                Funcionario funcionario = (Funcionario) usuario;
                switch (funcionario.getTipoPerfil().toString()) {
                    case "ADMINISTRADOR" ->
                        tela = "TelaVisualizarPedidos";
                    case "ATENDENTE" ->
                        tela = "TelaVisualizarPedidos";
                    case "ENTREGADOR" ->
                        tela = "TelaVisualizarPedidos";
                }
            }
        }
        return tela;
    }
}
