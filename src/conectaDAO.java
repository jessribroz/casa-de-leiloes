
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    // Método para conectar ao banco de dados
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            // URL do banco de dados, com usuário e senha para conexão
            String url = "jdbc:mysql://localhost/uc11"; // Alterar conforme o nome do seu banco de dados
            String usuario = "root";  // Usuário do banco
            String senha = "";        // Senha do banco (deixe vazio se não houver senha)
            
            // Estabelecendo a conexão
            conn = DriverManager.getConnection(url, usuario, senha);
            
            // Informando sucesso
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            
        } catch (SQLException erro) {
            // Exibindo mensagem de erro em caso de falha na conexão
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + erro.getMessage());
        }
        
        return conn;
    }
}
