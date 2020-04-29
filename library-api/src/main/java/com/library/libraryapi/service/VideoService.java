package com.library.libraryapi.service;

import com.library.libraryapi.dto.business.PersonDTO;
import com.library.libraryapi.dto.business.VideoDTO;
import com.library.libraryapi.exceptions.BadRequestException;
import com.library.libraryapi.exceptions.ResourceNotFoundException;
import com.library.libraryapi.model.MediaType;
import com.library.libraryapi.model.Person;
import com.library.libraryapi.model.Video;
import com.library.libraryapi.repository.VideoRepository;
import com.library.libraryapi.repository.VideoSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("VideoService")
public class VideoService implements GenericService<VideoDTO,Video,Integer> {
   private static final String CANNOT_FIND_WITH_ID = "Cannot find Video with the id : ";
   private static final String CANNOT_SAVE ="Failed to save Video";

   private final VideoRepository videoRepository;
   private final PersonService personService;

   private final ModelMapper modelMapper = new ModelMapper();

   public VideoService(VideoRepository videoRepository, PersonService personService) {
      this.videoRepository = videoRepository;
      this.personService = personService;
   }

   @Override
   public boolean existsById(Integer id) {
      return videoRepository.findById(id).isPresent();
   }

   @Override
   public VideoDTO findById(Integer id) {

      if(existsById(id)){
         Video video = videoRepository.findById(id).orElse(null);
         return entityToDTO(video);
      } else {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      }
   }

   @Override
   public List<VideoDTO> findAll() {
      List<Video> videos = videoRepository.findAll();
      List<VideoDTO> videoDTOS = new ArrayList<>();

      for (Video video: videos){
         videoDTOS.add(entityToDTO(video));
      }

      if (!videoDTOS.isEmpty()) {
         return videoDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public List<VideoDTO> findAllFiltered(VideoDTO filter) {
      Video video = dtoToEntity(filter);
      Specification<Video> spec = new VideoSpecification(video);
      List<Video> videos = videoRepository.findAll(spec);
      List<VideoDTO> videoDTOS = new ArrayList<>();

      for (Video v: videos){
         videoDTOS.add(entityToDTO(v));
      }

      if (!videoDTOS.isEmpty()) {
         return videoDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public Integer getFirstId(VideoDTO filter) {
      return findAllFiltered(filter).get(0).getId();
   }

   @Override
   public VideoDTO save(VideoDTO videoDTO) {
      return null;
   }

   @Override
   public VideoDTO update(VideoDTO videoDTO) {
      if (  !StringUtils.isEmpty(videoDTO.getId()) &&
            !StringUtils.isEmpty(videoDTO.getTitle()) &&
            !StringUtils.isEmpty(videoDTO.getDirector()) &&
            !StringUtils.isEmpty(videoDTO.getType()) &&
            !StringUtils.isEmpty(videoDTO.getFormat())) {

         try {
            Integer videoId = getFirstId(videoDTO);
            return entityToDTO(videoRepository.findById(videoId).orElse(null));

         } catch (ResourceNotFoundException ex) {
            videoDTO.setId(null);
            return entityToDTO(videoRepository.save(dtoToEntity(videoDTO)));
         }

      } else {
         throw new BadRequestException(CANNOT_SAVE);
      }
   }

   @Override
   public void deleteById(Integer id) {
      if (!existsById(id)) {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      } else {
         videoRepository.deleteById(id);
      }
   }

   @Override
   public Integer count() {
      return Math.toIntExact(videoRepository.count());
   }

   @Override
   public VideoDTO entityToDTO(Video video) {
      VideoDTO videoDTO = modelMapper.map(video, VideoDTO.class);
      PersonDTO director = personService.findById(video.getDirectorId());
      List<PersonDTO> actorDTOs = new ArrayList<>();

      if(video.getActors()!=null) {
         for (Person person : video.getActors()) {
            actorDTOs.add(personService.entityToDTO(person));
         }
      }
      videoDTO.setDirector(director);
      videoDTO.setActors(actorDTOs);

      return videoDTO;
   }

   @Override
   public Video dtoToEntity(VideoDTO videoDTO) {
      Video video = modelMapper.map(videoDTO, Video.class);
      Set<Person> personSet = new HashSet<>();

      if(videoDTO.getActors()!=null) {
         for (PersonDTO personDTO : videoDTO.getActors()) {
            personSet.add(personService.dtoToEntity(personDTO));
         }
      }
      video.setMediaType(MediaType.VIDEO);
      video.setActors(personSet);

      if(videoDTO.getDirector()!=null) {
         video.setDirectorId(videoDTO.getDirector().getId());
      }

      return video;
   }

   public List<PersonDTO> findAllDirectors() {
      List<PersonDTO> directors = new ArrayList<>();

      for( int directorId : videoRepository.findAllDirectors()){
         directors.add(personService.findById(directorId));
      }

      return  directors;
   }

   public List<PersonDTO> findAllActors() {
      List<PersonDTO> actors = new ArrayList<>();

      for( int actorsId : videoRepository.findAllActors()){
         actors.add(personService.findById(actorsId));
      }

      return  actors;
   }

   public List<String> findAllTitles() {
      return videoRepository.findAllTitles();
   }

}
