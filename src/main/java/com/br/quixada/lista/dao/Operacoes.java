package com.br.quixada.lista.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort.Direction;

import com.br.quixada.lista.model.Editora;
import com.br.quixada.lista.model.Livro;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Operacoes {
	MongoClient mongoClient = new MongoClient();
	MongoDatabase db = mongoClient.getDatabase("lista8_2");
	MongoCollection<Document> collectionEditoras = db.getCollection("editoras");
	MongoCollection<Document> collectionLivros = db.getCollection("livros");

	public Operacoes() {

	}

	public Livro findOne(String id) {
		Document filter = Document.parse("{_id:ObjectId('" + id + "')}");
		Livro livro = new Livro();

		for (Document doc : collectionLivros.find(filter)) {
			livro.setIdMongo(doc.getObjectId("_id"));
			livro.setIsbn(doc.getDouble("isbn"));
			livro.setTitulo(doc.getString("Titulo"));
			livro.setAno_publicacao(doc.getDouble("ano_publicacao"));
			livro.setId_editora(doc.getDouble("id_editora"));
			livro.setQtd_estoque(doc.getDouble("qtd_estoque"));
			livro.setValor(doc.getDouble("valor"));
		}
		return livro;
	}

	public Editora findOneEditora(String id) {
		Document filter = Document.parse("{_id:ObjectId('" + id + "')}");
		Editora editora = new Editora();
		for (Document doc : collectionLivros.find(filter)) {
			editora.setId(doc.getDouble("id"));
			editora.setNome(doc.getString("nome"));
		}
		return editora;
	}

	public List<Livro> listLivros() {
		List<Livro> livros = new ArrayList<Livro>();
		for (Document doc : collectionLivros.find()) {
			Livro livro = new Livro(doc.getObjectId("_id"), doc.getDouble("isbn"), doc.getString("Titulo"),
					doc.getDouble("ano_publicacao"), doc.getDouble("qtd_estoque"), doc.getDouble("valor"),
					doc.getDouble("id_editora"));
			livros.add(livro);
		}
		return livros;
	}

	public List<Editora> listEditora() {
		List<Editora> editoras = new ArrayList<Editora>();
		for (Document doc : collectionEditoras.find()) {
			Editora editora = new Editora(doc.getObjectId("_id"), doc.getDouble("id"), doc.getString("nome"));
			editoras.add(editora);
		}
		return editoras;
	}

	public void insertLivro(Livro livro) {
		collectionLivros.insertOne(new Document("isbn", livro.getIsbn()).append("Titulo", livro.getTitulo())
				.append("ano_publicacao", livro.getAno_publicacao()).append("qtd_estoque", livro.getQtd_estoque())
				.append("valor", livro.getValor()).append("id_editora", livro.getId_editora()));
	}

	public void insertEditora(Editora editora) {
		collectionEditoras.insertOne(new Document("id", editora.getId()).append("nome", editora.getNome()));
	}

	public void updateLivro(Livro livro) {
		// Document filter = Document.parse("{_id:ObjectId('" +
		// livro.getIdMongo() + "')}");
		// String json = "{'isbn':'" + livro.getIsbn() + "','Titulo':'" +
		// livro.getTitulo() + "','ano_publicacao':'"
		// + livro.getAno_publicacao() + "','qtd_estoque':'" +
		// livro.getQtd_estoque() + "','valor':'"
		// + livro.getValor() + "','id_editora':'" + livro.getId_editora() +
		// "','_id':'" + livro.getIdMongo()
		// + "'";
		// Document update = Document.parse(json);
		// collectionLivros.findOneAndUpdate(filter, update);
	}

	public void remove(String id) {
		collectionLivros.deleteOne(new Document("_id", new ObjectId(id)));
	}

	public void removeEditora(String id) {
		collectionEditoras.deleteOne(new Document("_id", new ObjectId(id)));
	}

	//Está mostrando no console
	public Iterable<Document> questao2A() {
		return collectionLivros
				.aggregate(Arrays.asList(Aggregates.lookup("editoras", "id_editora", "id", "editora_livro"),
						Aggregates.sort(new Document("Titulo", 1)),
						Aggregates.project(Projections.fields(Projections.excludeId(), Projections.include("Titulo"),
								Projections.include("editora_livro.nome")))));
	}

	//Está mostrando no console
	public Iterable<Document> questao2B() {
		return collectionLivros
				.aggregate(Arrays.asList(Aggregates.lookup("editoras", "id_editora", "id", "editora_livro"),
						Aggregates.match(Filters.gte("ano_publicacao", 2010)),
						Aggregates.group("$editora.nome", Accumulators.sum("total_livros", "$qtd_estoque"),
								Accumulators.sum("valor_total",
										new Document("$multiply", Arrays.asList("$qtd_estoque", "$valor"))))));
	}

}
