package org.com.brkdesenvolvimento.brk.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.com.brkdesenvolvimento.brk.models.UserLogin;
import org.com.brkdesenvolvimento.brk.models.Usuario;
import org.com.brkdesenvolvimento.brk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.apache.tomcat.util.codec.binary.Base64;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenha((usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenha((usuario.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(usuario));

		}

		return Optional.empty(); 
		}


	public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> userLogin) {

		Optional<Usuario> usuario = null;
		userLogin.get().setId(usuario.get().getId());
		userLogin.get().setSenha(usuario.get().getSenha());
		userLogin.get().setUsuario(usuario.get().getUsuario());
		

		
		if (usuario.isPresent()) {

			if (compararSenhas(userLogin.get().getSenha(), usuario.get().getSenha())) {

				userLogin.get().setId(usuario.get().getId());
				userLogin.get().setSenha(usuario.get().getSenha());
				userLogin.get().setUsuario(usuario.get().getUsuario());
				userLogin.get()
						.setToken(gerarBasicToken(userLogin.get().getUsuario(), userLogin.get().getSenha()));
				userLogin.get().setSenha(usuario.get().getSenha());
				return userLogin;

			}
		

	return Optional.empty();

		}
		return userLogin;
	}
//	private String criptografarSenha(String senha) {
//
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		return encoder.encode(senha);
//
//	}
//
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}
//
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		// TODO Auto-generated method stub
		return Logar(user);
	}

//	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
//		// TODO Auto-generated method stub
//		return senha;
//	}
}
