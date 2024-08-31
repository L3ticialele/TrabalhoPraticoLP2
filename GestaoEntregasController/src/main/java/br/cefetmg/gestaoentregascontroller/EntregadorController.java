package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EntregadorController {

    public void gerarRelatorio(String inicio, String fim, ArrayList<Pedido>pedidos, Funcionario entregador) throws IOException {
       
        LocalDate dataAtual = LocalDate.now();
        double valorComissao = valorComissao(entregador.getComissao());
        
        try (BufferedWriter relatorio = new BufferedWriter(new FileWriter(dataAtual + ".txt"))) {
            relatorio.write("Relatório de entregas" + dataAtual);
            relatorio.newLine();
            relatorio.write("Datas: " + inicio + ", " + fim);
            relatorio.newLine();
            for(int x=0; x<pedidos.size(); x++){
                //if(inicio <pedido.get(x).getData < fim)
                   relatorio.write("Data: " + pedidos.get(x).getData());
                   relatorio.newLine();
                   relatorio.write("Valor da Venda: " + pedidos.get(x).getValorTotal() + ",Comissao: " +  valorComissao);
                   relatorio.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public double valorComissao(String valor){
       String novoValor = valor.replace(",", ".");
        double comissao = Double.parseDouble(novoValor);
        return comissao;
    }
}
    
