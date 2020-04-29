package com.library.libraryapi.service;

import com.library.libraryapi.dto.business.BookDTO;
import com.library.libraryapi.dto.business.MediaDTO;
import com.library.libraryapi.exceptions.BadRequestException;
import com.library.libraryapi.exceptions.ConflictException;
import com.library.libraryapi.exceptions.ResourceNotFoundException;
import com.library.libraryapi.model.Media;
import com.library.libraryapi.model.MediaType;
import com.library.libraryapi.repository.MediaRepository;
import com.library.libraryapi.repository.MediaSpecification;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("MediaService")
public class MediaService implements GenericService<MediaDTO,Media,Integer> {
   private static final String CANNOT_FIND_WITH_ID = "Cannot find Media with the id : ";
   private static final String CANNOT_SAVE ="Failed to save Media";

   private final MediaRepository mediaRepository;
   private final BookService bookService;
   private final ModelMapper modelMapper = new ModelMapper();

   public MediaService(MediaRepository mediaRepository, BookService bookService) {
      this.mediaRepository = mediaRepository;
      this.bookService = bookService;
   }


   @Override
   public boolean existsById(Integer id) {
      Optional<Media> media = mediaRepository.findById(id);

      return media.isPresent();
   }

   @Override
   public MediaDTO findById(Integer id) {

      Optional<Media> media = mediaRepository.findById(id);

      if (media.isPresent()) {
         return entityToDTO(media.get());
      } else {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      }
   }

   @Override
   public List<MediaDTO> findAll() {
      List<Media> medias = mediaRepository.findAll();
      List<MediaDTO> mediaDTOS = new ArrayList<>();

      for (Media media: medias){
         mediaDTOS.add(entityToDTO(media));
      }

      if (!mediaDTOS.isEmpty()) {
         return mediaDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public List<MediaDTO> findAllFiltered(MediaDTO filter) {
      Specification<Media> spec = new MediaSpecification(dtoToEntity(filter));
      List<Media> medias = mediaRepository.findAll(spec);
      List<MediaDTO> mediaDTOS = new ArrayList<>();

      for (Media media: medias){
         mediaDTOS.add(entityToDTO(media));
      }

      if (!mediaDTOS.isEmpty()) {
         return mediaDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public Integer getFirstId(MediaDTO filter) {
      return findAllFiltered(filter).get(0).getId();
   }

   @Override
   public MediaDTO save(MediaDTO mediaDTO) {
      if (  !StringUtils.isEmpty(mediaDTO.getId()) &&
            !StringUtils.isEmpty(mediaDTO.getTitle()) &&
            !StringUtils.isEmpty(mediaDTO.getMediaType())) {

         try {
            Integer mediaId = getFirstId(mediaDTO);
            return findById(mediaId);

         } catch (ResourceNotFoundException ex) {
            mediaDTO.setId(null);
            return entityToDTO(mediaRepository.save(dtoToEntity(mediaDTO)));
         }

      } else {
         throw new BadRequestException(CANNOT_SAVE);
      }
   }

   @Override
   public MediaDTO update(MediaDTO mediaDTO) {
      if (  !StringUtils.isEmpty(mediaDTO.getId()) &&
            !StringUtils.isEmpty(mediaDTO.getTitle()) &&
            !StringUtils.isEmpty(mediaDTO.getMediaType())) {

         if (existsById(mediaDTO.getId())) {

            BookDTO bookDTO = bookService.findById(mediaDTO.getId());
            bookDTO.setRemaining(mediaDTO.getRemaining());
            bookDTO = bookService.update(bookDTO);

            Media media = mediaRepository.findById(mediaDTO.getId()).orElse(null);
            return entityToDTO(media);
         } else {
            throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + mediaDTO.getId());
         }
      } else {
         throw new ConflictException(CANNOT_SAVE);
      }
   }

   @Override
   public void deleteById(Integer id) {
      if (!existsById(id)) {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      } else {
         mediaRepository.deleteById(id);
      }
   }

   @Override
   public Integer count() {
      return Math.toIntExact(mediaRepository.count());
   }

   @Override
   public MediaDTO entityToDTO(Media media) {
      return modelMapper.map(media, MediaDTO.class);
   }

   @Override
   public Media dtoToEntity(MediaDTO mediaDTO) {
      Media media = modelMapper.map(mediaDTO, Media.class);
      String type = mediaDTO.getMediaType();
      media.setMediaType(MediaType.valueOf(type));

      return media;
   }


   Integer quantityRemaining(Integer id){
      return mediaRepository.remaining(id);
   }

   void updateQuantityRemaining(Integer mediaId, Integer quantity){
      mediaRepository.updateRemaining(quantity, mediaId);
   }

   void updateReturnDate(Integer mediaId, Date date) {
      java.sql.Date sDate = new java.sql.Date(date.getTime());
      mediaRepository.updateReturnDate(sDate, mediaId);
   }
}
