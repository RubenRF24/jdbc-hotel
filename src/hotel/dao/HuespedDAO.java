package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel.factory.ConnectionFactory;
import hotel.modelo.Huesped;
import hotel.modelo.Reserva;

public class HuespedDAO {

	final private Connection con;

	public HuespedDAO() {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public void guardar(Huesped huesped) {

		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO TBL_HUESPEDES" + " (nombre, apellido, fecha_nac, nacionalidad, telefono, id_reserva)"
							+ " VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setInt(5, huesped.getTelefono());
				statement.setInt(6, huesped.getReservaId());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Huesped> listarHuespedes() {
		List<Huesped> resultado = new ArrayList<>();

		try (con) {

			var querySelect = "SELECT ID, NOMBRE, APELLIDO, FECHA_NAC, NACIONALIDAD, TELEFONO, ID_RESERVA"
					+ " FROM TBL_HUESPEDES";

			PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHA_NAC"),
								resultSet.getString("NACIONALIDAD"), resultSet.getInt("TELEFONO"),
								resultSet.getInt("ID_RESERVA"));

						resultado.add(huesped);
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			Double telefono, Integer numeroReserva) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		try (con) {

			var querySelect = "UPDATE TBL_HUESPEDES SET" + " NOMBRE = ?" + " ,APELLIDO = ?" + " ,FECHA_NAC = ?"
					+ " ,NACIONALIDAD = ?" + " ,TELEFONO = ?" + " ,ID_RESERVA = ?" + " WHERE ID = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setString(3, sdf.format(fechaNacimiento));
				statement.setString(4, nacionalidad);
				statement.setDouble(5, telefono);
				statement.setInt(6, numeroReserva);
				statement.setInt(7, id);

				statement.execute();

				var updateCount = statement.getUpdateCount();

				return updateCount;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
