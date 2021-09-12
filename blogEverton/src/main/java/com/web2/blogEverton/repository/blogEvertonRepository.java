package com.web2.blogEverton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web2.blogEverton.model.Postagens;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface blogEvertonRepository extends JpaRepository<Postagens, Integer>{
    List<Postagens> findPostagensByTipo(int tipo);

   // List<Postagens> findPostagensByTituloContaining(String titulo);
    List<Postagens> findPostagensByTituloLike(String titulo);
}
