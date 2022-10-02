package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import banco.ConnectionFactory;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		String numero = "Numero de Reserva";
		String dataIn = "Data de entrada";
		String dataOut = "Data de saída";
		String valor = "Valor";
		String pagamento = "Forma de Pagamento";
		modelo.addRow(new Object[] {
				numero,
				dataIn,
				dataOut,
				valor,
				pagamento
		});
		try {
			pegaReservas();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar a tabela de dados 'RESERVA'"+e1, "ERRO", JOptionPane.ERROR_MESSAGE, null);
		}
		
		
		tbHospedes = new JTable();
		
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), tbHospedes, null);
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		String numeroHospede = "Id";
		String nome = "Nome";
		String sobrenome = "Sobrenome";
		String dataNascimento = "DataNascimento";
		String nacionalidade = "Nacionalidade";
		String telefone = "Telefone";
		modeloHospedes.addRow(new Object[] {
				numeroHospede,
				nome,
				sobrenome,
				dataNascimento,
				nacionalidade,
				telefone,
				numero	
		});
		try {
			pegaHospedes();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar a tabela de dados 'Hospedes'"+e2, "ERRO", JOptionPane.ERROR_MESSAGE, null);
		}
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					boolean eSobrenome = false;
					String txt = txtBuscar.getText();
					if (!"".equals(txt)) {
						modelo.setNumRows(0);
						modeloHospedes.setNumRows(0);
						eSobrenome = buscaSobrenome(txt);
						if (eSobrenome != true) {
							buscaHospedes(txt);
							Integer txtBuscar = Integer.parseInt(txt);
							buscaReservas(txtBuscar);
						}
					} else {
						modelo.setNumRows(0);
						modeloHospedes.setNumRows(0);
						pegaReservas();
						pegaHospedes();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(350, 508, 200, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					deletaHospedes();
					deletaReservas();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e);				
					}

			}
		});
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 200, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
		
		
	}
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
	    private void pegaReservas() throws SQLException {
	    	ConnectionFactory factory = new ConnectionFactory();
			Connection conexao = factory.recuperarConexao();
			
			
			Statement stm = conexao.createStatement();
			stm.execute("SELECT * FROM Reservas");
			
			ResultSet rst = stm.getResultSet();
			
			while(rst.next()) {
					modelo.addRow(new Object[] {
				    rst.getString("Id"),
					rst.getString("DataEntrada"),
					rst.getString("DataSaida"),
					rst.getString("Valor"),
					rst.getString("FormaPagamento")
				});
			}
			conexao.close();
			stm.close();
	    }
	    
	    private void buscaReservas(int id) throws SQLException {
	    	ConnectionFactory factory = new ConnectionFactory();
			Connection conexao = factory.recuperarConexao();
			
			
			PreparedStatement stm = conexao.prepareStatement("SELECT * FROM Reservas where id=?");
			stm.setInt(1, id);
			ResultSet rst = stm.executeQuery();
		
			
			while(rst.next()) {
					
					modelo.addRow(new Object[] {
				    rst.getInt("Id"),
					rst.getString("DataEntrada"),
					rst.getString("DataSaida"),
					rst.getString("Valor"),
					rst.getString("FormaPagamento")
				});
			}
			conexao.close();
			stm.close();
	    }
	    
	    private void buscaHospedes(String id) throws SQLException {
	    	ConnectionFactory factory = new ConnectionFactory();
			Connection conexao = factory.recuperarConexao();
			
			
			PreparedStatement stm = conexao.prepareStatement("SELECT * FROM Hospedes where idReserva=?");
			stm.setString(1, id);
			ResultSet rst = stm.executeQuery();
		
			
			while(rst.next()) {
					
					modeloHospedes.addRow(new Object[] {
							rst.getInt("Id"),
							rst.getString("Nome"),
							rst.getString("Sobrenome"),
							rst.getString("DataNascimento"),
							rst.getString("Nacionalidade"),
							rst.getString("Telefone"),
							rst.getString("IdReserva")
				});
			}
			conexao.close();
			stm.close();
	    }
	    
	    public boolean buscaSobrenome (String sobrenome) throws SQLException {
	    	ConnectionFactory factory = new ConnectionFactory();
			Connection conexao = factory.recuperarConexao();
			String id;
			boolean eSobrenome = false;
			
			PreparedStatement stm = conexao.prepareStatement("SELECT * FROM Hospedes where sobrenome=?");
			stm.setString(1, sobrenome);
			ResultSet rst = stm.executeQuery();
					
			while(rst.next()) {
					
					modeloHospedes.addRow(new Object[] {
							rst.getInt("Id"),
							rst.getString("Nome"),
							rst.getString("Sobrenome"),
							rst.getString("DataNascimento"),
							rst.getString("Nacionalidade"),
							rst.getString("Telefone"),
							id = rst.getString("IdReserva")
							
				});
					Integer idReserva = Integer.parseInt(id);
					sobrenomeToReserva(idReserva);
					eSobrenome = true;
			}
			
			
			
			conexao.close();
			stm.close();
			
			return eSobrenome;
	    }
	    
	    private void sobrenomeToReserva(int id) throws SQLException {
	    	ConnectionFactory factory = new ConnectionFactory();
			Connection conexao = factory.recuperarConexao();
	    	PreparedStatement stm2 = conexao.prepareStatement("SELECT * FROM Reservas where id=?");
			stm2.setInt(1, id);
			ResultSet rst2 = stm2.executeQuery();
			while ( rst2.next()){
				modelo.addRow(new Object[] {
			    rst2.getInt("Id"),
				rst2.getString("DataEntrada"),
				rst2.getString("DataSaida"),
				rst2.getString("Valor"),
				rst2.getString("FormaPagamento")
			});
		}
	    }
	    private void pegaHospedes() throws SQLException {
	    	ConnectionFactory factory = new ConnectionFactory();
			Connection conexao = factory.recuperarConexao();
			
			
			Statement stm = conexao.createStatement();
			stm.execute("SELECT * FROM Hospedes");
			
			ResultSet rst = stm.getResultSet();
			
			while(rst.next()) {
					modeloHospedes.addRow(new Object[] {
							rst.getInt("Id"),
							rst.getString("Nome"),
							rst.getString("Sobrenome"),
							rst.getString("DataNascimento"),
							rst.getString("Nacionalidade"),
							rst.getString("Telefone"),
							rst.getString("IdReserva")
				});
			}
			conexao.close();
			stm.close();
	    }
	   
	    private void deletaReservas() {
	    
			try {
	    	if (tbReservas.hasFocus()) {
				ConnectionFactory factory = new ConnectionFactory();
				Connection conexao = factory.recuperarConexao();
				int reservaSelecionada = tbReservas.getSelectedRow();
		    	String idReservas = tbReservas.getValueAt(reservaSelecionada, 0).toString();
		    	modelo.removeRow(reservaSelecionada);
		    	String sql = "delete from Reservas where Id=?";
		    	String sql2 = "delete from Hospedes where IdReserva=?";
				PreparedStatement pst2 = conexao.prepareStatement(sql);
				PreparedStatement pst3 = conexao.prepareStatement(sql2);
				pst2.setString(1, idReservas);
				pst3.setString(1, idReservas);
				pst2.execute();
				pst3.execute();
				pegaHospedes();
				conexao.close();
				pst2.close();
				pst3.close();
		    	}
	    	} catch (Exception e) {
	    		System.out.println("Ocorreu um erro ao exluir a tabela do banco");
	    		e.printStackTrace();
	    		JOptionPane.showMessageDialog(null, e);
	    	}
			}	
	    
	    private void deletaHospedes() {
	    	
	    	try {
	    	
			if (tbHospedes.hasFocus()) {
				ConnectionFactory factory = new ConnectionFactory();
				Connection conexao = factory.recuperarConexao();
				int hospedeSelecionado = tbHospedes.getSelectedRow();
		    	String idSelecionado = tbHospedes.getValueAt(hospedeSelecionado, 0).toString();
		    	String idReserva = tbHospedes.getValueAt(hospedeSelecionado, 6).toString();
		    	modeloHospedes.removeRow(hospedeSelecionado);
				String sql = "delete from Hospedes where id=?";
				String sql2 = "delete from Reservas where Id=?";
				PreparedStatement pst2 = conexao.prepareStatement(sql2);
				PreparedStatement pst = conexao.prepareStatement(sql);
				pst.setString(1, idSelecionado);
				pst2.setString(1, idReserva);
				pst2.execute();
				pst.execute();
				pegaReservas();
				conexao.close();
				pst.close();
				pst2.close();
		    	}
	    	} catch (Exception e) {
	    		System.out.println("Ocorreu um erro ao exluir a tabela do banco");
	    		JOptionPane.showMessageDialog(null, e);
	    	}
			
	    }	   
		}


