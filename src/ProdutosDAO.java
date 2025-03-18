/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    // Conexão com o banco de dados
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultset;
    private final ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // Método para cadastrar um produto no banco de dados
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB(); 

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    // Método para listar todos os produtos cadastrados
    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            listagem.clear();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            closeResources();
        }

        return listagem;
    }

    // Método para atualizar o status de um produto para "Vendido"
    public void venderProduto(int produtoId) {
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, produtoId);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    // Método para listar todos os produtos com status "Vendido"
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                produtosVendidos.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            closeResources();
        }

        return produtosVendidos;
    }

    // Método auxiliar para fechar recursos
    private void closeResources() {
        try {
            if (conn != null) conn.close();
            if (prep != null) prep.close();
            if (resultset != null) resultset.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
