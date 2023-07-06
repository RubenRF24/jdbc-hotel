package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.factory.ConnectionFactory;
import hotel.modelo.MetodoPago;

public class MetodoPagoDAO {

	final private Connection con;
	
	public MetodoPagoDAO() {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public List<MetodoPago> listar() {
		List<MetodoPago> resultado = new ArrayList<>();
		
		var querySelect = "SELECT ID, NOMBRE FROM TBL_METODOPAGO";
		
		try(con){
			PreparedStatement statement = con.prepareStatement(querySelect);
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						var metodoPago = new MetodoPago(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"));
						
						resultado.add(metodoPago);
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	
}
