package hotel.controller;

import hotel.dao.UsuarioDAO;

public class UsuarioController {

	public Boolean verificarLogin(String usuario, String clave) {
		return new UsuarioDAO().verificarUsuario(usuario, clave);
	}
	
}
