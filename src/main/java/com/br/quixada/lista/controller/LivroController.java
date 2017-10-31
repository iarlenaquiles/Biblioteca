package com.br.quixada.lista.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.quixada.lista.dao.Operacoes;
import com.br.quixada.lista.model.Editora;
import com.br.quixada.lista.model.Livro;

@Controller
public class LivroController {
	
	Operacoes op = new Operacoes();

	@GetMapping("/livros")
	public String home(Model model) {
		List<Livro> lista = op.listLivros();
		model.addAttribute("livros", lista);
		return "lista-livros";
	}

	@GetMapping("/livros/add")
	public String insereForm(Model model) {
		List<Editora> lista = op.listEditora();
		model.addAttribute("livro", new Livro());
		model.addAttribute("acao", "/livros");
		model.addAttribute("editoras", lista);
		return "inserir-livro";
	}

	@PostMapping("/livros")
	public String addLivro(Livro livro, Model model, RedirectAttributes redirect, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("livro", livro);
			model.addAttribute("acao", "/livros");
			if (livro.getIdMongo().toString() == null) {
				return "inserir-livro";
			} else {
				return "editar-livros";
			}
		}
		if (livro.getIdMongo() == null) {
			op.insertLivro(livro);
			redirect.addFlashAttribute("mensagem", "Livro inserido com sucesso!");
		} else {
			op.updateLivro(livro);
			redirect.addFlashAttribute("mensagem", "Livro atualizado com sucesso!");
		}
		return "redirect:/livros";
	}

	@RequestMapping("/livros/{id}/update")
	public String alteraForm(@PathVariable String id, Model model) {
		Livro livro = op.findOne(id);
		List<Editora> lista = op.listEditora();
		model.addAttribute("livro", livro);
		model.addAttribute("acao", "/livros");
		model.addAttribute("editoras", lista);
		return "editar-livros";
	}

	@GetMapping("/livros/{id}/delete")
	public String deleteLivro(@PathVariable String id, RedirectAttributes redirect) {
		op.remove(id);
		redirect.addFlashAttribute("mensagem", "Livro removido com sucesso!");
		return "redirect:/livros";
	}

}
