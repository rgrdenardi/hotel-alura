package banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagemHospedes {

	public static void main(String[] args) throws SQLException {
		
			ConnectionFactory connectionFactory = new ConnectionFactory();
			Connection conexao = connectionFactory.recuperarConexao();
			Statement stm = conexao.createStatement();
			stm.execute("SELECT * FROM Hospedes");
			
			ResultSet rst = stm.getResultSet();
			
			while(rst.next()) {
				int id = rst.getInt("Id");
				String nome = rst.getString("Nome");
				String sobrenome = rst.getString("Sobrenome");
				String dataNascimento = rst.getString("DataNascimento");
				String nacionalidade = rst.getString("Nacionalidade");
				String telefone = rst.getString("Telefone");
				String idReserva = rst.getString("IdReserva");
				System.out.println(id + "|" + nome + "|" + sobrenome + "|" + dataNascimento + "|" + nacionalidade + "|" + telefone + "|" + idReserva);
			}
		} 
	}
	 

