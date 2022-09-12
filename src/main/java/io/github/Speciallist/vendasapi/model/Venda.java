package io.github.Speciallist.vendasapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 8)
	private FormaPagamento formaPagamento;

	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itens;

	@Column(precision = 16, scale = 2, nullable = false)
	private BigDecimal total;

	@Column(name = "data_venda", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataVenda;

	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PrePersist
	public void prePersist() {
		setDataVenda(LocalDateTime.now());
	}

	@PreUpdate
	public void preUpdate() {
		setDataVenda(this.getDataVenda());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", cliente=" + cliente + ", formaPagamento=" + formaPagamento + ", itens=" + itens
				+ ", total=" + total + ", dataVenda=" + dataVenda + "]";
	}

}
