package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasentidades.Pedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class EntregadorController {

    public void gerarRelatorio(String inicio, String fim, ArrayList<Pedido>pedidos) throws IOException {
       
        LocalDate dataAtual = LocalDate.now();
        try (BufferedWriter relatorio = new BufferedWriter(new FileWriter(dataAtual + ".txt"))) {
            relatorio.write("Relatório de entregas");
            relatorio.newLine();
            relatorio.write("Datas: " + inicio + ", " + fim);
   
            for(int x=0; x<pedidos.size(); x++){
                   relatorio.write(pedidos.get(x).getNomeCliente());
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
