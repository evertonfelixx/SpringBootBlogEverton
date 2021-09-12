package com.web2.blogEverton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.web2.blogEverton.model.Postagens;
import com.web2.blogEverton.repository.blogEvertonRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class blogEvertonController {
	@Autowired
	blogEvertonRepository blogEvertonRepository;

	@RequestMapping(value="/postagens", method=RequestMethod.GET)
	public ModelAndView getPostagens() {
		ModelAndView mv = new ModelAndView("postagens");
		List<Postagens> postagens = blogEvertonRepository.findAll();
		mv.addObject("postagens", postagens);
		return mv;
	}

	@RequestMapping(value="/postagens/{id}", method=RequestMethod.GET)
	public ModelAndView getPostagem(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("postagem");
		Optional<Postagens> postagens = blogEvertonRepository.findById(id);
		mv.addObject("autor", postagens.get().getAutor());
		mv.addObject("titulo", postagens.get().getTitulo());
		mv.addObject("data", postagens.get().getData());
		return mv;
	}

	@RequestMapping(value="/newpost", method=RequestMethod.GET)
	public String newPost(){
		return "formulario";
	}

	@RequestMapping(value="/newpost", method=RequestMethod.POST)
	public String savePost(@Valid Postagens postagem, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos obrigatórios");
			return "redirect:/newpost";
		}
		postagem.setData(LocalDate.now());
		blogEvertonRepository.save(postagem);
		return "redirect:/postagens";
	}

	@RequestMapping(value="/postagens/tipo/{id}", method=RequestMethod.GET)
	public ModelAndView getPostagemByTipo(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("postagens");
		List<Postagens> postagens = blogEvertonRepository.findPostagensByTipo(id);

		mv.addObject("postagens", postagens);
		return mv;
	}

	@RequestMapping(value="**/pesquisar", method=RequestMethod.POST)
	public ModelAndView getPostagemByTitulo(@RequestParam("pesquisar") String pesquisar) {
		ModelAndView mv = new ModelAndView("postagens");
		List<Postagens> postagens = blogEvertonRepository.findPostagensByTituloLike("%"+pesquisar+"%");
		mv.addObject("postagens", postagens);
		return mv;
	}

	@RequestMapping(value="/postagens/delete/{id}", method=RequestMethod.GET)
	public String deletePostagens(@PathVariable("id") int id, RedirectAttributes attributes){
		blogEvertonRepository.deleteById(id);
		attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		return "redirect:/postagens";
	}

	@RequestMapping(value="/postagens/update/{id}", method=RequestMethod.GET)
	public ModelAndView updatePostagens(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("update");
		Optional<Postagens> postagens = blogEvertonRepository.findById(id);
		mv.addObject("autor", postagens.get().getAutor());
		mv.addObject("titulo", postagens.get().getTitulo());
		mv.addObject("data", postagens.get().getData());
		mv.addObject("tipo", postagens.get().getTipo());
		mv.addObject("texto", postagens.get().getTexto());
		return mv;
	}
	@RequestMapping(value="/postagens/update/{id}", method=RequestMethod.POST)
	public String updatePostagem(Postagens postagens) {
		Postagens postExistente = blogEvertonRepository.findById(postagens.getId()).orElse(null);
		postExistente.setTipo(postagens.getTipo());
		postExistente.setAutor(postagens.getAutor());
		postExistente.setTexto(postagens.getTexto());
		postExistente.setTitulo(postagens.getTitulo());
		blogEvertonRepository.save(postExistente);
		return "redirect:/postagens";
	}
}
