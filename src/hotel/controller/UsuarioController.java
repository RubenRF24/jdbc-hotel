package hotel.controller;

import hotel.dao.UsuarioDAO;
import hotel.factory.ConnectionFactory;

public class UsuarioController {

	public Boolean verificarLogin(String usuario, String clave) {
		return new UsuarioDAO().verificarUsuario(usuario, clave);
	}
	
}
