package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.factory.ConnectionFactory;
import hotel.modelo.Nacionalidad;

public class NacionalidadDAO {

	final private Connection con;
	
	public NacionalidadDAO() {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public List<Nacionalidad> listar() {
		List<Nacionalidad> resultado = new ArrayList<>();
		
		var querySelect = "SELECT PAIS_NAC, GENTILICIO_NAC, ISO_NAC FROM TBL_NACIONALIDAD";
		
		try(con){
			PreparedStatement statement = con.prepareStatement(querySelect);
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						var nacionalidad = new Nacionalidad(resultSet.getString("PAIS_NAC"),
								resultSet.getString("GENTILICIO_NAC"),resultSet.getString("ISO_NAC"));
						
						resultado.add(nacionalidad);
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	
}
