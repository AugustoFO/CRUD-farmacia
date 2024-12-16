package com.crudfarmacia.crud_farmacia.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min =3, message = "O atributo título deve conter no mínimo 03 caracteres")
	@Column(name = "nome_produto")
	private String nomeProduto;
	
	@Size(min =10, message = "O atributo descricao do produto deve conter no mínimo 10 caracteres")
	@Column(name = "descricao_produto")
	private String descricaoProduto;
	
	@NotNull(message = "O preço é obrigatório.")
	@Column(precision = 10, scale = 2, name = "preco_produto")
	@Positive(message = "O preço não pode ser negativo.")
    private BigDecimal precoProduto;
	
	@NotNull(message = "O atributo quantidade em estoque é obrigatório.")
	@Column(name = "qtdestoque_produto")
	private int qtdEstoqueProduto;
	
	@ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public BigDecimal getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(BigDecimal precoProduto) {
		this.precoProduto = precoProduto;
	}

	public int getQtdEstoqueProduto() {
		return qtdEstoqueProduto;
	}

	public void setQtdEstoqueProduto(int qtdEstoqueProduto) {
		this.qtdEstoqueProduto = qtdEstoqueProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
