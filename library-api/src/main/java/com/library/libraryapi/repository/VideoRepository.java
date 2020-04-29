package com.library.libraryapi.repository;

import com.library.libraryapi.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>, JpaSpecificationExecutor<Video> {
   Optional<Video> findById(Integer id);
   Optional<Video> findByEan(String ean);
   List<Video> findAll();
   Video save(Video video);
   void deleteById(Integer id);

   @Query("SELECT DISTINCT directorId FROM Video ORDER BY directorId")
   List<Integer> findAllDirectors();

   @Query(value = "SELECT DISTINCT v.actor_id FROM video_actors v ORDER BY v.actor_id", nativeQuery = true)
   List<Integer> findAllActors();

   @Query(value = "SELECT DISTINCT title FROM media WHERE media_type = 'VIDEO' ORDER BY title", nativeQuery = true)
   List<String> findAllTitles();
}
