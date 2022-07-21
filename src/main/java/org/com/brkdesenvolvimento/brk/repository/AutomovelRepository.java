package org.com.brkdesenvolvimento.brk.repository;

import java.util.List;
import java.util.Optional;

import org.com.brkdesenvolvimento.brk.models.Automovel;
import org.com.brkdesenvolvimento.brk.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
	public List<Automovel> findByPlacaContainingIgnoreCase(String Placa);


}
