package hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import hotel.controller.HuespedController;
import hotel.controller.ReservaController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservaController reservaController;
	private HuespedController huespedController;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
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
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtBuscar.getText().isBlank()) {
					if (tbReservas.isShowing()) {
						borrarFilas(modelo);
						cargarReservas();
					} else if (tbHuespedes.isShowing()) {
						borrarFilas(modeloHuesped);
						cargarHuespedes();
					}
				}
			}
		});
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		cargarReservas();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		cargarHuespedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
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
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
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
				if (tbHuespedes.isShowing()) {
					buscar(modeloHuesped, huespedController);
				} else if (tbReservas.isShowing()) {
					buscar(modelo, reservaController);
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");

		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.isShowing()) {
					modificarReserva();
				} else if (tbHuespedes.isShowing()) {
					modificarHuesped();
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.isShowing()) {
					eliminar(tbReservas, modelo, reservaController);
				} else if (tbHuespedes.isShowing()) {
					eliminar(tbHuespedes, modeloHuesped, huespedController);
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private boolean tieneFilaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	}

	private void cargarHuespedes() {
		var huespedes = new HuespedController().cargarHuespedes();

		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getReservaId() }));
	}

	private void cargarReservas() {
		var reservas = new ReservaController().cargarReservas();

		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPagoId() }));
	}

	private void modificarReserva() {
		if (tieneFilaElegida(tbReservas)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
					try {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						Date fechaEntrada = formato.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
						Date fechaSalida = formato.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
						Double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						Integer formaPago = Integer
								.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString());

						int filasModificadas;

						filasModificadas = this.reservaController.modificar(id, fechaEntrada, fechaSalida, valor,
								formaPago);

						JOptionPane.showMessageDialog(this,
								String.format("%d reserva modificada con éxito!", filasModificadas));
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	private void modificarHuesped() {
		if (tieneFilaElegida(tbHuespedes)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
					try {
						Integer id = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
						String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
						Date fechaNacimiento = formato
								.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
						String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
						BigInteger telefono = new BigInteger(
								modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
						Integer numeroReserva = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
						;

						int filasModificadas;

						filasModificadas = this.huespedController.modificar(id, nombre, apellido, fechaNacimiento,
								nacionalidad, telefono, numeroReserva);

						JOptionPane.showMessageDialog(this,
								String.format("%d huespedes modificados con éxito!", filasModificadas));
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	private void eliminar(JTable tabla, DefaultTableModel modelo, Object controller) {
		if (tieneFilaElegida(tabla)) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
				.ifPresentOrElse(fila -> {

					Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());

					int cantidadEliminada = 0;

					if (controller instanceof ReservaController) {
						cantidadEliminada = new ReservaController().eliminar(id);
					} else if (controller instanceof HuespedController) {
						cantidadEliminada = new HuespedController().eliminar(id);
					}

					modelo.removeRow(tabla.getSelectedRow());

					JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	private void buscar(DefaultTableModel modelo, Object controller) {

		borrarFilas(modelo);

		if (controller instanceof ReservaController) {
			var coincidencia = txtBuscar.getText();
			var contenido = new ReservaController().buscar(coincidencia);
			contenido.forEach(datos -> modelo.addRow(new Object[] { datos.getId(), datos.getFechaEntrada(),
					datos.getFechaSalida(), datos.getValor(), datos.getFormaPagoId() }));

		} else if (controller instanceof HuespedController) {
			var coincidencia = txtBuscar.getText();
			var contenido = new HuespedController().buscar(coincidencia);
			contenido.forEach(datos -> modelo.addRow(
					new Object[] { datos.getId(), datos.getNombre(), datos.getApellido(), datos.getFechaNacimiento(),
							datos.getNacionalidad(), datos.getTelefono(), datos.getReservaId() }));
		}

	}

	private void borrarFilas(DefaultTableModel modelo) {
		int cantidadFilas = modelo.getRowCount() - 1;

		for (int i = cantidadFilas; i >= 0; i--) {
			modelo.removeRow(i);
		}
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
