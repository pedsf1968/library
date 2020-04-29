package com.user.userapi.repository;

import com.user.userapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private UserRepository userRepository;



   @Before
   public void before() {
      User userReference = new User();
      userReference.setId(123);
      userReference.setFirstName("Emile");
      userReference.setLastName("Zola");
      userReference.setCounter(5);
      userReference.setStatus("status");
      userReference.setEmail("emile.zola@gmail.com");
      userReference.setPhone("0123789456");
      userReference.setPassword("6568798kjghk");
      userReference.setPhotoLink("/photo.jpg");

      log.info("======================================================" + userReference.getId().toString());
      userReference = entityManager.persistAndFlush(userReference);
      log.info("======================================================" + userReference.getId().toString());
   }


   @Test
   public void GIVENuser_WHENgetOne_THENreturnSameUser() {
  /*    User userReference = new User();
      userReference.setId(123);
      userReference.setFirstName("Emile");
      userReference.setLastName("Zola");
      userReference.setAddressId(2);
      userReference.setCounter(5);
      userReference.setStatus("status");
      userReference.setEmail("emile.zola@gmail.com");
      userReference.setPhone("0123789456");
      userReference.setPassword("6568798kjghk");
      userReference.setPhotoLink("/photo.jpg");

      log.info("======================================================" + userReference.getId().toString());
      entityManager.persist(userReference);
      entityManager.flush();
      log.info("======================================================" + userReference.getId().toString());


      User found = userRepository.getOne(123);
      assertEquals( userReference, found);*/
   }
/*
   User getOne(Integer id);
   User findByEmail(String email);
   User findByStatus(String status);
   List<User> findAll();
   User save(User user);
   void deleteById(Integer id);

 */

}