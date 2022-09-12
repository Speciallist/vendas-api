package io.github.Speciallist.vendasapi.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_nascimento", length = 10)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 255, nullable = false)
	private String endereco;

	@Column(length = 14)
	private String telefone;

	@Column(length = 100)
	private String email;

	@Column(name = "data_cadastro", updatable = true, length = 10)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@OneToMany(mappedBy = "cliente")
	private List<Venda> vendas;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Long id, LocalDate dataNascimento, String cpf, String nome, String endereço, String telefone,
			String email, LocalDate dataCadastro) {
		super();
		this.id = id;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereço;
		this.telefone = telefone;
		this.email = email;
		this.dataCadastro = dataCadastro;
	}

	public Cliente(LocalDate dataNascimento, String cpf, String nome, String endereço, String telefone, String email) {
		super();
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereço;
		this.telefone = telefone;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}

	@PreUpdate
	public void preUpdate() {
		setDataCadastro(this.getDataCadastro());
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", nome=" + nome
				+ ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email + ", dataCadastro="
				+ dataCadastro + "]";
	}

}
