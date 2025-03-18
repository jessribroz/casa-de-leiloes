/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jessica
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaVendas extends JFrame {
    private final JTable tabelaVendas;
    private final JScrollPane scrollPane;

    public TelaVendas() {
        setTitle("Vendas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Criando a tabela para exibir os produtos vendidos
        tabelaVendas = new JTable();
        scrollPane = new JScrollPane(tabelaVendas);
        add(scrollPane);

        // Carregar dados de produtos vendidos
        carregarProdutosVendidos();
    }

    private void carregarProdutosVendidos() {
        ProdutosDAO produtosDAO = new ProdutosDAO();
        List<ProdutosDTO> produtosVendidos = produtosDAO.listarProdutosVendidos();
        
        // Definir os dados da tabela
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nome", "Valor", "Status"}, 0);
        for (ProdutosDTO produto : produtosVendidos) {
            model.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getValor(), produto.getStatus()});
        }
        tabelaVendas.setModel(model);
    }
}
