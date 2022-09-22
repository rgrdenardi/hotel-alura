package banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection conexao = factory.recuperarConexao();
		
		Statement stm = conexao.createStatement();
		stm.execute("INSERT INTO Hospedes (Nome, Sobrenome, DataNascimento, Nacionalidade, Telefone, IdReserva) VALUES ('placa de video', 'intel')"
		, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rst = stm.getGeneratedKeys();
		while (rst.next()) {
			int id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
	}
}
