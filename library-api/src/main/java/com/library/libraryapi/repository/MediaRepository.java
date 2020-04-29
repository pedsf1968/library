package com.library.libraryapi.repository;

import com.library.libraryapi.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer>, JpaSpecificationExecutor<Media> {
   Optional<Media> findById(Integer id);
   Optional<Media> findByEan(String ean);
   List<Media> findAll();
   Media save(Media media);
   void deleteById(Integer id);

   @Query("SELECT m.remaining FROM Media m WHERE m.id = ?1")
   Integer remaining(Integer mediaId);

   @Modifying
   @Transactional
   @Query("UPDATE Media m SET m.remaining = ?1 WHERE m.id = ?2")
   void updateRemaining(Integer quantity, Integer mediaId);

   @Modifying
   @Transactional
   @Query("UPDATE Media m SET m.returnDate = ?1 WHERE m.id = ?2")
   void updateReturnDate(Date date, Integer mediaId);
}
