package banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexao = factory.recuperarConexao();
		
		Statement stm = conexao.createStatement();
		stm.execute("DELETE FROM Hospedes WHERE ID > 2");
		
		int linhasModificadas = stm.getUpdateCount();
		
		System.out.println("Linhas modificadas: " + linhasModificadas);
	}
}
