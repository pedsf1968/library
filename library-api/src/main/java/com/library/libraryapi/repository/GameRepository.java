package com.library.libraryapi.repository;

import com.library.libraryapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {
   Optional<Game> findById(Integer id);
   Optional<Game> findByEan(String ean);
   List<Game> findAll();
   Game save(Game game);
   void deleteById(Integer id);

   @Query(value = "SELECT DISTINCT title FROM media WHERE media_type = 'GAME' ORDER BY title", nativeQuery = true)
   List<String> findAllTitles();
}
