
package br.cefetmg.gestaoentregasentidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pedidos")
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data")
    private Date data;
    @Column(name="valor_total")
    private double valorTotal;
    @Column(name="status")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @OneToMany(fetch = FetchType.EAGER, cascade =
            CascadeType.ALL, mappedBy = "pedido")
    private ArrayList<Item> items;
    @Column(name="marca")
    private String marca;
    @Column(name="quantidade")
    private int quantidade;
    @Column(name="valor_unitario")
    private double valorUnitario;
    @Column(name="pagamento")
    private String pagamento;
    @Column(name="endereco")
    private String endereco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entregador", nullable = false)
    private Funcionario entregador;
    @Column(name="observacoes")
    private String observacoes;
    
    
    public Pedido() {
    }

    public Pedido(Date data, double valorTotal, String status, Cliente cliente, String marca, int quantidade, double valorUnitario, String pagamento, String endereco, Funcionario entregador, String observacoes) {
        items = new ArrayList<>();
        for (Status s : Status.values()) {
            if(status.equals(s.toString()))
                this.status = s;
        }
        setStatus(status);
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.marca = marca;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.pagamento = pagamento;
        this.endereco = endereco;
        this.entregador = entregador;
        this.observacoes = observacoes;
    }
    
    public final void setStatus(String status) {
        switch(status.toUpperCase()){
            case "EMPREPARACAO" -> this.status = Status.EMPREPARACAO;
            case "SAIUPARAENTREGA" -> this.status = Status.SAIUPARAENTREGA;
            case "ENTREGUE" -> this.status = Status.ENTREGUE;
        }
        
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Funcionario getEntregador() {
        return entregador;
    }

    public void setEntregador(Funcionario entregador) {
        this.entregador = entregador;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
    public void setItem(Item item){
        items.add(item);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    
    
}
