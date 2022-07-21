package org.com.brkdesenvolvimento.brk.models;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	
	private String sobrenome;

	@Schema(example = "email@email.com.br")
	@NotEmpty(message = "Campo não preenchido!")
	@Email
	@NotNull
	@Column(unique = true)
	private String email;


	private String dataNasc;

	@NotNull
	private String usuario;

	private String senha;

	
	private String telefone;
	
	private String tipo;
	
;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Automovel> carros;

	public Usuario() {

	}

	

	public Usuario(Long id, @NotNull String nome, String sobrenome,
			@NotEmpty(message = "Campo não preenchido!") @Email @NotNull String email, String dataNasc,
			@NotNull String usuario, @NotBlank String senha, String telefone, String tipo, List<Automovel> carros) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.dataNasc = dataNasc;
		this.usuario = usuario;
		this.senha = senha;
		this.telefone = telefone;
		this.tipo = tipo;
		this.carros = carros;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getSobrenome() {
		return sobrenome;
	}



	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDataNasc() {
		return dataNasc;
	}



	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public List<Automovel> getCarros() {
		return carros;
	}



	public void setCarros(List<Automovel> carros) {
		this.carros = carros;
	}

}