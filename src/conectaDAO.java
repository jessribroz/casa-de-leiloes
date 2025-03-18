import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    // Método para conectar ao banco de dados
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            // URL do banco de dados, com usuário e senha para conexão
            String url = "jdbc:mysql://localhost/uc11"; 
            String usuario = "root";  // Usuário do banco
            String senha = "Hpnxjr23!";        // Senha do banco
            
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
