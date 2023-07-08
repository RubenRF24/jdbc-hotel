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

import javax.swing.JOptionPane;

import hotel.factory.ConnectionFactory;
import hotel.modelo.Reserva;

public class ReservaDAO {

	final private Connection con;

	public ReservaDAO() {
		this.con = new ConnectionFactory().recuperaConexion();
	}

	public int guardar(Reserva reserva) {

		try (con) {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO TBL_RESERVA"
					+ " (fecha_entrada, fecha_salida, valor, formaPago_id)" + " VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, reserva.getFechaEntrada());
				statement.setString(2, reserva.getFechaSalida());
				statement.setDouble(3, reserva.getValor());
				statement.setInt(4, reserva.getFormaPagoId());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
			return reserva.getId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Reserva> listarReservas() {
		List<Reserva> resultado = new ArrayList<>();

		try (con) {

			var querySelect = "SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMAPAGO_ID " + "FROM TBL_RESERVA";

			PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"),
								resultSet.getDate("FECHA_SALIDA"), resultSet.getDouble("VALOR"),
								resultSet.getInt("FORMAPAGO_ID"));

						resultado.add(reserva);
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public int modificar(Integer id, Date fechaEntrada, Date fechaSalida, Double valor, Integer formaPago) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		try (con) {

			var querySelect = "UPDATE TBL_RESERVA SET" + " FECHA_ENTRADA = ?" + " ,FECHA_SALIDA = ?" + " ,VALOR = ?"
					+ " ,FORMAPAGO_ID = ?" + " WHERE ID = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);
			try (statement) {
				statement.setString(1, String.valueOf(sdf.format(fechaEntrada)));
				statement.setString(2, String.valueOf(sdf.format(fechaSalida)));
				statement.setDouble(3, valor);
				statement.setInt(4, formaPago);
				statement.setInt(5, id);

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
			var querySelect = "DELETE FROM TBL_RESERVA WHERE ID = ?";

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

}
