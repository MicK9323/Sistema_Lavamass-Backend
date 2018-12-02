package com.mca.apps.lavamassapirest.models.repository;

import com.mca.apps.lavamassapirest.models.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {

    Optional<UserDTO> findByDniOrCodUser(String dni, String codUser);

    Optional<UserDTO> findByNameContainsOrLastNameContains(String name, String lastName);

    Optional<UserDTO> findByDniOrEmail(String dni, String email);

    List<UserDTO> findByIdIn(List<Integer> userIds);

    Boolean existsByDni(String dni);

    Boolean existsByEmail(String dni);

}
