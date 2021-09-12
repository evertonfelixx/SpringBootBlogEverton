package com.web2.blogEverton.services.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.blogEverton.model.Postagens;
import com.web2.blogEverton.repository.blogEvertonRepository;
import com.web2.blogEverton.services.blogEvertonServices;

@Service
public class blogEvertonServiceImpl implements blogEvertonServices{
	
	@Autowired
	blogEvertonRepository blogEvertonRepository;
	
	@Override
	public List<Postagens> findAll() {
		// TODO Auto-generated method stub
		return blogEvertonRepository.findAll();
	}

	@Override
	public Postagens findById(int id) {
		// TODO Auto-generated method stub
		return blogEvertonRepository.findById(id).get();
	}

	@Override
	public Postagens save(Postagens postagem) {
		// TODO Auto-generated method stub
		return blogEvertonRepository.save(postagem);
	}

	@Override
	public List<Postagens> findPostagensByTipo(int tipo) {
		return blogEvertonRepository.findPostagensByTipo(tipo);
	}


	@Override
	public Postagens deleteById(int id) {
		return deleteById(id);
	}

	@Override
	public List<Postagens> findPostagensByTituloLike(String titulo) {
		return blogEvertonRepository.findPostagensByTituloLike(titulo);
	}


}
