package br.edu.ifsp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifsp.conexao.Conexao;
import br.edu.ifsp.modelo.Pessoa;
import javax.swing.JOptionPane;

public class PessoaDAO {
	
	public Conexao con = null;
        private PreparedStatement stat = null;
        

	public void salvarPessoa(Pessoa pessoa) {
		
		try {
		
			this.con = Conexao.getInstance();
		
			String sql = "insert into pessoa (nomecompleto, idade) values (?, ?)";
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			pstm.setInt(2, pessoa.getIdade());
			pstm.setString(1, pessoa.getNome());
			pstm.executeUpdate();
			
			System.out.println("Foi inserido com sucesso");
			
		} catch(SQLException e) {
			
			System.out.println("Problema ao inserir uma pessoa");
		}	
	}
	
	public ArrayList<Pessoa> consultarTodos() {
		
		ArrayList<Pessoa> listaPessoas = new ArrayList<>();
		
		try {
			
			this.con = Conexao.getInstance();
			
			String sql = "SELECT * FROM pessoa";
			
			PreparedStatement pstm = con.getConexao().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getInt("idade"));
				
				listaPessoas.add(p);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		return listaPessoas;
	}
	
	public Pessoa consultarPessoaPorId(int id) throws SQLException {
            this.con = Conexao.getInstance();
                Pessoa pessoa = new Pessoa();
				
		pessoa.setId( id );
			
                String comand = "SELET nomecompleto FROM PESSOA WHERE ID = "+id+"";
		stat = con.getConexao().prepareStatement(comand);
                ResultSet res = stat.executeQuery();
			
		pessoa.setNome( res.getString("nomecompleto") );
                                
                                
                String comand2 = "SELET idade FROM PESSOA WHERE ID = "+id+"";
                stat = con.getConexao().prepareStatement(comand2);
                ResultSet res2 = stat.executeQuery();
                pessoa.setIdade( res2.getInt("idade"));
                
                
                return pessoa;
        }
	
	public void editarPessoaPorId(int id) throws SQLException {
            this.con = Conexao.getInstance();
			
			String comand = "UPDATE PESSOA SET nomecompleto = ?, idade = ? where id = ? ";
			
			stat = con.getConexao().prepareStatement(comand);
			stat.setString( 1, JOptionPane.showInputDialog(null, "Digite o novo nome"));
			stat.setInt( 2, Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a nova idade")));
			stat.setInt( 3, id);
			
			stat.executeUpdate();
	}

	public void removerPessoaPorId(int id) throws SQLException {
        
        this.con = Conexao.getInstance();
        String comand = "DELETE FROM PESSOA WHERE ID = "+id+"";
        stat = con.getConexao().prepareStatement(comand);
        stat.setInt(1, id);
	stat.executeUpdate();
        
	}
}
