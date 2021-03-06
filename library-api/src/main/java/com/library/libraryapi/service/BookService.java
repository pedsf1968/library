package com.library.libraryapi.service;

import com.library.libraryapi.dto.business.BookDTO;
import com.library.libraryapi.dto.business.PersonDTO;
import com.library.libraryapi.exceptions.BadRequestException;
import com.library.libraryapi.exceptions.ConflictException;
import com.library.libraryapi.exceptions.ResourceNotFoundException;
import com.library.libraryapi.model.Book;
import com.library.libraryapi.model.MediaType;
import com.library.libraryapi.repository.BookRepository;
import com.library.libraryapi.repository.BookSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("BookService")
public class BookService implements GenericService<BookDTO,Book,Integer> {
   private static final String CANNOT_FIND_WITH_ID = "Cannot find Book with the id : ";
   private static final String CANNOT_SAVE ="Failed to save Book";

   private final BookRepository bookRepository;
   private final PersonService personService;
   private final ModelMapper modelMapper = new ModelMapper();

   public BookService(BookRepository bookRepository, PersonService personService) {
      this.bookRepository = bookRepository;
      this.personService = personService;
   }


   @Override
   public boolean existsById(Integer id) {
      return bookRepository.findById(id).isPresent();
   }

   @Override
   public BookDTO findById(Integer id) {

      if (existsById(id)) {
         Book book = bookRepository.findById(id).orElse(null);
         return entityToDTO(book);
      } else {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      }
   }

   @Override
   public List<BookDTO> findAll() {
      List<Book> books = bookRepository.findAll();
      List<BookDTO> bookDTOS = new ArrayList<>();

      for (Book book: books){
         bookDTOS.add(entityToDTO(book));
      }

      if (!bookDTOS.isEmpty()) {
         return bookDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public List<BookDTO> findAllFiltered(BookDTO filter) {
      Book book = dtoToEntity(filter);

      Specification<Book> spec = new BookSpecification(book);
      List<Book> books = bookRepository.findAll(spec);
      List<BookDTO> bookDTOS = new ArrayList<>();

      for ( Book b: books){
         bookDTOS.add(entityToDTO(b));
      }

      if (!bookDTOS.isEmpty()) {
         return bookDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public Integer getFirstId(BookDTO filter){
      return findAllFiltered(filter).get(0).getId();
   }

   @Override
   public BookDTO save(BookDTO bookDTO) {
      if (  !StringUtils.isEmpty(bookDTO.getTitle()) &&
            !StringUtils.isEmpty(bookDTO.getAuthor()) &&
            !StringUtils.isEmpty(bookDTO.getEditor()) &&
            !StringUtils.isEmpty(bookDTO.getType()) &&
            !StringUtils.isEmpty(bookDTO.getFormat())) {

         bookDTO.setId(null);
         return entityToDTO(bookRepository.save(dtoToEntity(bookDTO)));

      } else {
         throw new BadRequestException(CANNOT_SAVE);
      }
   }

   @Override
   public BookDTO update(BookDTO bookDTO) {
      if (  !StringUtils.isEmpty(bookDTO.getId()) &&
            !StringUtils.isEmpty(bookDTO.getTitle()) &&
            !StringUtils.isEmpty(bookDTO.getAuthor()) &&
            !StringUtils.isEmpty(bookDTO.getEditor()) &&
            !StringUtils.isEmpty(bookDTO.getType()) &&
            !StringUtils.isEmpty(bookDTO.getFormat())) {
         if (!existsById(bookDTO.getId())) {
            throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + bookDTO.getId());
         }
         Book book = bookRepository.save(dtoToEntity(bookDTO));

         return entityToDTO(book);
      } else {
         throw new ConflictException(CANNOT_SAVE);
      }
   }


   @Override
   public void deleteById(Integer id) {
      if (!existsById(id)) {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      } else {
         bookRepository.deleteById(id);
      }
   }

   @Override
   public Integer count() {
      return Math.toIntExact(bookRepository.count());
   }

   @Override
   public BookDTO entityToDTO(Book book) {
      BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
      PersonDTO author = personService.findById(book.getAuthorId());
      PersonDTO editor = personService.findById(book.getEditorId());

      bookDTO.setAuthor(author);
      bookDTO.setEditor(editor);

      return bookDTO;
   }

   @Override
   public Book dtoToEntity(BookDTO bookDTO) {
      Book book = modelMapper.map(bookDTO, Book.class);

      book.setMediaType(MediaType.BOOK);

      if(bookDTO.getAuthor()!=null) {
         book.setAuthorId(bookDTO.getAuthor().getId());
      }
      if(bookDTO.getEditor()!=null) {
         book.setEditorId(bookDTO.getEditor().getId());
      }
      return book;
   }

      public List<PersonDTO> findAllAuthors() {
      List<PersonDTO> personDTOS = new ArrayList<>();

      for (int auhorId:bookRepository.findAllAuthors()){
         personDTOS.add(personService.findById(auhorId));
      }

      return personDTOS;
   }

   public List<PersonDTO> findAllEditors() {
      List<PersonDTO> personDTOS = new ArrayList<>();

      for (int editorId:bookRepository.findAllEditors()){
         personDTOS.add(personService.findById(editorId));
      }

      return personDTOS;
   }

   public List<String> findAllTitles() {
      return bookRepository.findAllTitles();
   }

}
