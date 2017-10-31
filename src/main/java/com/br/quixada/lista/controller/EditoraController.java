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
public class EditoraController {
	Operacoes op = new Operacoes();

	@GetMapping("/editoras")
	public String home(Model model) {
		List<Editora> lista = op.listEditora();
		model.addAttribute("editoras", lista);
		return "lista-editoras";
	}

	@GetMapping("/editoras/add")
	public String insereForm(Model model) {
		model.addAttribute("editora", new Editora());
		model.addAttribute("acao", "/editoras");
		return "inserir-editora";
	}

	@PostMapping("/editoras")
	public String addEditora(Editora editora, Model model, RedirectAttributes redirect, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("editora", editora);
			model.addAttribute("acao", "/editoras");
			if (editora.getIdMongo() == null) {
				return "inserir-editora";
			} else {
				return "editar-editora";
			}
		}
		if (editora.getIdMongo() == null) {
			op.insertEditora(editora);
			redirect.addFlashAttribute("mensagem", "Editora inserida com sucesso!");
		} else {
			//op.updateEditora(editora);
			redirect.addFlashAttribute("mensagem", "Editora atualizada com sucesso!");
		}
		return "redirect:/editoras";
	}

	@RequestMapping("/editoras/{id}/update")
	public String alteraForm(@PathVariable String id, Model model) {
		Editora editora = op.findOneEditora(id);
		model.addAttribute("editora", editora);
		model.addAttribute("acao", "/editoras");
		return "editar-editoras";
	}

	@GetMapping("/editoras/{id}/delete")
	public String deleteEditora(@PathVariable String id, RedirectAttributes redirect) {
		op.removeEditora(id);
		redirect.addFlashAttribute("mensagem", "Editora removida com sucesso!");
		return "redirect:/editoras";
	}

}
