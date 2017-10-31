package com.br.quixada.lista.model;

import org.bson.types.ObjectId;

public class Editora {
	private ObjectId idMongo;
	private Double id;
	private String nome;

	public Editora(ObjectId idMongo, Double id, String nome) {
		this.idMongo = idMongo;
		this.id = id;
		this.nome = nome;
	}

	public Editora() {

	}

	public ObjectId getIdMongo() {
		return idMongo;
	}

	public void setIdMongo(ObjectId idMongo) {
		this.idMongo = idMongo;
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
