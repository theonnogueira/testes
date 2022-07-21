package org.com.brkdesenvolvimento.brk.repository;

import java.util.Optional;

import org.com.brkdesenvolvimento.brk.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
	public Optional<Usuario> findByUsuario(String usuario);


}
