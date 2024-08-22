
package br.cefetmg.gestaoentregasentidades;

public enum Perfil {
    ADMINISTRADOR("Administrador"),
    ATENDENTE("Atendente"),
    ENTREGADOR("Entregador");
    
    private final String descricao;
    
    Perfil(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return descricao;
    }
}
