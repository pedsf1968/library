package com.library.libraryapi.repository;

import com.library.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
   Optional<Book> findById(Integer id);
   Optional<Book> findByEan(String ean);
   List<Book> findAll();
   Book save(Book book);
   void deleteById(Integer id);

   @Query("SELECT DISTINCT authorId FROM Book ORDER BY authorId")
   List<Integer> findAllAuthors();

   @Query("SELECT DISTINCT editorId FROM Book ORDER BY editorId")
   List<Integer> findAllEditors();

   @Query(value = "SELECT DISTINCT title FROM media WHERE media_type = 'BOOK' ORDER BY title", nativeQuery = true)
   List<String> findAllTitles();
}
