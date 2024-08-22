
package br.cefetmg.gestaoentregasentidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="produtos")
public class Produto implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_produto")
    private int id;
    @Column(name="nome_produto")
    private String nome;
    @Column(name="localizacao")
    private String localizacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item", nullable = false)
    private Item item;

    public Produto() {
    }

    public Produto(String nome, String localizacao, Item item) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    
}
