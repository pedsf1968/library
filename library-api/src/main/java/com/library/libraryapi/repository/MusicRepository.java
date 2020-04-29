package com.library.libraryapi.repository;

import com.library.libraryapi.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer>, JpaSpecificationExecutor<Music> {
   Optional<Music> findById(Integer id);
   Optional<Music> findByEan(String ean);
   List<Music> findAll();
   Music save(Music music);
   void deleteById(Integer id);

   @Query("SELECT DISTINCT authorId FROM Music ORDER BY authorId")
   List<Integer> findAllAuthors();

   @Query("SELECT DISTINCT composerId FROM Music ORDER BY composerId")
   List<Integer> findAllComposers();

   @Query("SELECT DISTINCT interpreterId FROM Music ORDER BY interpreterId")
   List<Integer> findAllInterpreters();

   @Query(value = "SELECT DISTINCT title FROM media WHERE media_type = 'MUSIC' ORDER BY title", nativeQuery = true)
   List<String> findAllTitles();
}
