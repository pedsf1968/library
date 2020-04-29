package com.library.libraryapi.service;

import com.library.libraryapi.dto.business.GameDTO;
import com.library.libraryapi.dto.business.PersonDTO;
import com.library.libraryapi.exceptions.BadRequestException;
import com.library.libraryapi.exceptions.ConflictException;
import com.library.libraryapi.exceptions.ResourceNotFoundException;
import com.library.libraryapi.model.Game;
import com.library.libraryapi.model.MediaType;
import com.library.libraryapi.repository.GameRepository;
import com.library.libraryapi.repository.GameSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("GameService")
public class GameService implements GenericService<GameDTO,Game,Integer> {
   private static final String CANNOT_FIND_WITH_ID = "Cannot find Game with the id : ";
   private static final String CANNOT_SAVE ="Failed to save Game";

   private final GameRepository gameRepository;
   private final PersonService personService;

   private final ModelMapper modelMapper = new ModelMapper();

   public GameService(GameRepository gameRepository, PersonService personService) {
      this.gameRepository = gameRepository;
      this.personService = personService;
   }

   @Override
   public boolean existsById(Integer id) {
      return gameRepository.findById(id).isPresent();
   }

   @Override
   public GameDTO findById(Integer id) {

      if (existsById(id)) {
         Game game = gameRepository.findById(id).orElse(null);
         return entityToDTO(game);
      } else {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      }

   }

   @Override
   public List<GameDTO> findAll() {
      List<Game> games = gameRepository.findAll();
      List<GameDTO> gameDTOS = new ArrayList<>();

      for (Game game: games){
         gameDTOS.add(entityToDTO(game));
      }

      if (!gameDTOS.isEmpty()) {
         return gameDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public List<GameDTO> findAllFiltered(GameDTO filter) {
      Specification<Game> spec = new GameSpecification(dtoToEntity(filter));

      List<Game> games = gameRepository.findAll(spec);
      List<GameDTO> gameDTOS = new ArrayList<>();

      for (Game game: games){
         gameDTOS.add(entityToDTO(game));
      }

      if (!gameDTOS.isEmpty()) {
         return gameDTOS;
      } else {
         throw new ResourceNotFoundException();
      }
   }

   @Override
   public Integer getFirstId(GameDTO filter) {
      return findAllFiltered(filter).get(0).getId();
   }

   @Override
   public GameDTO save(GameDTO gameDTO) {
      if (  !StringUtils.isEmpty(gameDTO.getId()) &&
            !StringUtils.isEmpty(gameDTO.getTitle()) &&
            !StringUtils.isEmpty(gameDTO.getType()) &&
            !StringUtils.isEmpty(gameDTO.getFormat())) {

         try {
            Integer bookId = getFirstId(gameDTO);
            Game game = gameRepository.findById(bookId).orElse(null);
            return entityToDTO(game);
         } catch (ResourceNotFoundException ex) {
            gameDTO.setId(null);
            return entityToDTO(gameRepository.save(dtoToEntity(gameDTO)));
         }
      } else {
         throw new BadRequestException(CANNOT_SAVE);
      }

   }

   @Override
   public GameDTO update(GameDTO gameDTO) {
      if (  !StringUtils.isEmpty(gameDTO.getId()) &&
            !StringUtils.isEmpty(gameDTO.getTitle()) &&
            !StringUtils.isEmpty(gameDTO.getType()) &&
            !StringUtils.isEmpty(gameDTO.getFormat())) {
         if (!existsById(gameDTO.getId())) {
            throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + gameDTO.getId());
         }
         Game game = gameRepository.save(dtoToEntity(gameDTO));

         return entityToDTO(game);
      } else {
         throw new ConflictException(CANNOT_SAVE);
      }

   }

   @Override
   public void deleteById(Integer id) {
      if (!existsById(id)) {
         throw new ResourceNotFoundException(CANNOT_FIND_WITH_ID + id);
      } else {
         gameRepository.deleteById(id);
      }
   }

   @Override
   public Integer count() {
      return Math.toIntExact(gameRepository.count());
   }

   @Override
   public GameDTO entityToDTO(Game game) {
      GameDTO gameDTO = modelMapper.map(game, GameDTO.class);
      PersonDTO editor = personService.findById(game.getEditorId());

      gameDTO.setEditor(editor);

      return gameDTO;
   }

   @Override
   public Game dtoToEntity(GameDTO gameDTO) {
      Game game = modelMapper.map(gameDTO, Game.class);

      game.setMediaType(MediaType.GAME);

      if(gameDTO.getEditor()!=null) {
         game.setEditorId(gameDTO.getEditor().getId());
      }
      return game;
   }

   public List<String> findAllTitles() {
      return gameRepository.findAllTitles();
   }
}
