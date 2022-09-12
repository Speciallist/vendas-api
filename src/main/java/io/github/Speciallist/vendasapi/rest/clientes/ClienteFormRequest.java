package io.github.Speciallist.vendasapi.rest.clientes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.Speciallist.vendasapi.model.Cliente;

public class ClienteFormRequest {

	private Long id;

	private String nome;

	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private String endereco;

	private String email;

	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	public ClienteFormRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteFormRequest(Long id, String nome, String cpf, LocalDate dataNascimento, String endereco, String email,
			String telefone, LocalDate dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.dataCadastro = dataCadastro;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente toModel() {
		return new Cliente(id, dataNascimento, cpf, nome, endereco, telefone, email, dataCadastro);
	}

	public static ClienteFormRequest fromModel(Cliente cliente) {
		return new ClienteFormRequest(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(),
				cliente.getEndereco(), cliente.getEmail(), cliente.getTelefone(), cliente.getDataCadastro());
	}

}
