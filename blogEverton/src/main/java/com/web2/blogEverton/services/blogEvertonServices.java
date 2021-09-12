package com.web2.blogEverton.services;

import java.util.List;

import com.web2.blogEverton.model.Postagens;

public interface blogEvertonServices {
	List<Postagens> findAll();
	Postagens findById(int id);
	Postagens save(Postagens postagem);
	List<Postagens> findPostagensByTipo(int tipo);
	Postagens deleteById(int id);
	List<Postagens> findPostagensByTituloLike(String titulo);

}
