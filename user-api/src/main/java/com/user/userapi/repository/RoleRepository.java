package com.user.userapi.repository;


import com.user.userapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
   Role getOne(Integer roleId);
   Role findByName(String name);

}
