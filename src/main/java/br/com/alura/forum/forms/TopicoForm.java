package br.com.alura.forum.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repositories.CursoRepository;

public class TopicoForm {
@NotBlank(message = "Titulo é um campo obrigatorio")	
@Min(message = "O campo deve ter pelo menos 10 caracteres0",value = 10)
private String titulo;
@NotBlank(message = "Mensagem é um campo obrigatorio")
private String mensagem;
@NotBlank(message = "nome do curso é um campo obrigatorio")
private String nomeCurso;


public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getMensagem() {
	return mensagem;
}
public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
}
public String getNomeCurso() {
	return nomeCurso;
}
public void setNomeCurso(String nomeCurso) {
	this.nomeCurso = nomeCurso;
}

public Topico formtoTopico(CursoRepository cursoRepo) {
Curso curso=cursoRepo.findByNome(this.nomeCurso);

return new Topico(this.titulo,this.mensagem,curso);
	
	
}


}
