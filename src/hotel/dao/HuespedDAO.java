package hotel.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import hotel.factory.ConnectionFactory;
import hotel.modelo.Huesped;

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
				statement.setLong(5, huesped.getTelefono().longValue());
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
								resultSet.getString("NACIONALIDAD"), BigInteger.valueOf(resultSet.getLong("TELEFONO")),
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
			BigInteger telefono, Integer numeroReserva) {

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
				statement.setLong(5, telefono.longValue());
				statement.setInt(6, numeroReserva);
				statement.setInt(7, id);

				statement.execute();

				var updateCount = statement.getUpdateCount();

				return updateCount;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
			throw new RuntimeException(e);
		}

	}

	public int eliminar(Integer id) {
		try (con) {
			var querySelect = "DELETE FROM TBL_HUESPEDES WHERE ID = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {
				statement.setInt(1, id);

				statement.execute();

				return statement.getUpdateCount();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listarSegunTexto(String coincidencia) {
		List<Huesped> resultado = new ArrayList<>();
		var id = 0;
		try{
			id = Integer.valueOf(coincidencia);
		}catch(NumberFormatException e) {
		}
		
		try (con) {

			var querySelect = "SELECT ID, NOMBRE, APELLIDO, FECHA_NAC, NACIONALIDAD, TELEFONO, ID_RESERVA"
					+ " FROM TBL_HUESPEDES"
					+ " WHERE APELLIDO LIKE ? OR ID_RESERVA = ?";

			PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {
				
				statement.setString(1, coincidencia+"%");
				statement.setInt(2, id);
				
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHA_NAC"),
								resultSet.getString("NACIONALIDAD"), BigInteger.valueOf(resultSet.getLong("TELEFONO")),
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

}
