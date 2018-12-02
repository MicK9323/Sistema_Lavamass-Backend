package com.mca.apps.lavamassapirest.models.repository;

import com.mca.apps.lavamassapirest.models.model.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDTO, Integer> {

    Optional<RoleDTO> findByRoleName(String roleName);

    Optional<RoleDTO> findById(int id);

}
