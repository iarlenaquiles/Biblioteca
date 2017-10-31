package com.br.quixada.lista.model;

import org.bson.types.ObjectId;

public class Livro {

	private ObjectId idMongo;
	private Double isbn;
	private String titulo;
	private Double ano_publicacao;
	private Double qtd_estoque;
	private Double valor;
	private Double id_editora;

	public Livro(ObjectId idMongo, Double isbn, String titulo, Double ano_publicacao, Double qtd_estoque,
			Double valor, Double id_editora) {
		this.idMongo = idMongo;
		this.isbn = isbn;
		this.titulo = titulo;
		this.ano_publicacao = ano_publicacao;
		this.qtd_estoque = qtd_estoque;
		this.valor = valor;
		this.id_editora = id_editora;
	}

	public Livro(){
		
	}
	public ObjectId getIdMongo() {
		return idMongo;
	}

	public void setIdMongo(ObjectId idMongo) {
		this.idMongo = idMongo;
	}

	public Double getIsbn() {
		return isbn;
	}

	public void setIsbn(Double isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getAno_publicacao() {
		return ano_publicacao;
	}

	public void setAno_publicacao(Double ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}

	public Double getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Double qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getId_editora() {
		return id_editora;
	}

	public void setId_editora(Double id_editora) {
		this.id_editora = id_editora;
	}

}
