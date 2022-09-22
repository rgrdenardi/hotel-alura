package banco;

import java.sql.Connection;
import java.sql.SQLException;

public class testaConexao {

	public static void main(String[] args) throws SQLException {
		
		Connection conexao = null;
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			conexao = connectionFactory.recuperarConexao();
		}catch (SQLException e) {
			System.out.println("Ocorreu um erro ao acessar o banco:" + e.getMessage());
		} finally {
				conexao.close();	
				System.out.println("Conexao fechada");
		}
	}
}
