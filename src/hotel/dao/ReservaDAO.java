package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import hotel.factory.ConnectionFactory;
import hotel.modelo.Reserva;

public class ReservaDAO {

	final private Connection con;
	
	public ReservaDAO() {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public int guardar(Reserva reserva) {

		try(con){
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO TBL_RESERVA"
						+ " (fecha_entrada, fecha_salida, valor, formaPago_id)"
						+ " VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				statement.setString(1, reserva.getFechaEntrada());
				statement.setString(2, reserva.getFechaSalida());
				statement.setDouble(3, reserva.getValor());
				statement.setInt(4, reserva.getFormaPagoId());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet){
					while(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
			return reserva.getId();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
}
