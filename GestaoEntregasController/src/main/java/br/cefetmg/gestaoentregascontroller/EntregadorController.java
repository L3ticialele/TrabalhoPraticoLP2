package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class EntregadorController {

    public void gerarRelatorio(String inicio, String fim, ArrayList<Pedido> pedidos, Funcionario entregador) throws IOException, ParseException {

        double valorComissao = 0;
        LocalDate dataAtual = LocalDate.now();
        if (entregador != null) {
            System.out.println(valorComissao);
            valorComissao = Double.parseDouble(entregador.getComissao());
        }

        try (BufferedWriter relatorio = new BufferedWriter(new FileWriter(dataAtual + ".txt"))) {
            relatorio.write("Relatório de entregas" + dataAtual);
            relatorio.newLine();
            relatorio.write("Datas: " + inicio + ", " + fim);
            relatorio.newLine();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicio = formato.parse(inicio);
            Date dataFim = formato.parse(fim);

            if (pedidos != null) {
                for (int x = 0; x < pedidos.size(); x++) {
                    if (pedidos.get(x).getDataPedido() != null) {
                        String datapedido = formato.format(pedidos.get(x).getDataPedido());
                        Date dataCerta = formato.parse(datapedido);
                        if (dataCerta.equals(dataFim) || dataCerta.before(dataFim)) {
                            if ( dataCerta.equals(dataInicio) ||  dataCerta.after(dataInicio)) {
                                relatorio.write("Data: " + datapedido);
                                relatorio.newLine();
                                relatorio.write("Valor da Venda: " + pedidos.get(x).getValorTotal() + ",Comissao: " + valorComissao * pedidos.get(x).getValorTotal());
                                relatorio.newLine();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double valorComissao(String valor) {
        String novoValor = valor.replace(",", ".");
        double comissao = Double.parseDouble(novoValor);
        return comissao;
    }
}
