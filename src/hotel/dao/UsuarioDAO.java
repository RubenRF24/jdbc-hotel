package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.factory.ConnectionFactory;
import hotel.modelo.Usuario;

public class UsuarioDAO {

	final private Connection con;
	
	public UsuarioDAO() {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public Boolean verificarUsuario(String usuario, String clave) {
		Boolean usuarioVerificado = false;
		
		try(con){
			var querySelect = "SELECT ID,LOGIN,CLAVE "
					+ " FROM TBL_USUARIOS "
					+ " WHERE LOGIN = ? AND CLAVE = ?";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.
					prepareStatement(querySelect);
	
			try(statement){
				statement.setString(1, usuario);
				statement.setString(2, clave);
				statement.execute();
		
				final ResultSet resultSet = statement.getResultSet();
		
				try(resultSet){
					while (resultSet.next()) {
						usuarioVerificado = true;
					}
				}
			}
			
			return usuarioVerificado;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
