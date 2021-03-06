package com.library.libraryapi.service;

import java.util.List;

public interface GenericService<T,S,N> {
   boolean existsById(N id);
   T findById(N id);
   List<T> findAll();
   List <T> findAllFiltered(T filter);
   N getFirstId(T filter);
   T save(T dto);
   T update(T dto);
   void deleteById(N id);
   Integer count();
   T entityToDTO(S entity);
   S dtoToEntity(T dto);
}
