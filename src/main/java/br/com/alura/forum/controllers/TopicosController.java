package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.dtos.TopicoDTO;
import br.com.alura.forum.forms.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

@Autowired	
private TopicoRepository topicoRepository;
@Autowired
private CursoRepository cursoRepository;

 @GetMapping
 public List<TopicoDTO> lista(String nomeCurso){
	 List<Topico> lista; 
	if(nomeCurso==null) {
	lista=topicoRepository.findAll();
	}else {
	 lista=topicoRepository.findByCurso_nome(nomeCurso);
		
	}   
    return  TopicoDTO.convert(lista);
	 
 }
 @PostMapping
 public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form,UriComponentsBuilder uriBuilder) {
  
	
	Topico t= this.topicoRepository.save(form.formtoTopico(cursoRepository));
	URI uri=uriBuilder.path("/topicos/{id}").buildAndExpand(t.getId()).toUri();
	
  return	ResponseEntity.created(uri).body(new TopicoDTO(t));
  	 
 } 

}

