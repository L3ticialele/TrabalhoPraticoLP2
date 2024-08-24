
package br.cefetmg.gestaoentregasentidades;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
@PrimaryKeyJoinColumn(name="id_usuario")
public class Cliente extends Usuario{
    @Column(name="logradouro")
    private String logradouro;
    @Column(name="bairro")
    private String bairro;
    @Column(name="cnpj_cliente")
    private String cnpj;
    @Column(name="cpf_cliente")
    private String cpf; 
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL,
                mappedBy="cliente")
    private ArrayList<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome, String logradouro, String bairro, String cnpj, String cpf, Empresa empresa, String senha, String telefone) {
        super(senha, telefone, nome, empresa, "Cliente");
        pedidos = new ArrayList<>();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cnpj = cnpj;
        this.cpf = cpf;
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public void setPedido(Pedido pedido){
        pedidos.add(pedido);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
