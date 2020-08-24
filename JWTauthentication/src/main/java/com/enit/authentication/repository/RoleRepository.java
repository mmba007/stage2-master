package com.enit.authentication.repository;


import com.enit.authentication.model.Role;
import com.enit.authentication.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role,String> {
    Optional<Role> findByName(RoleName roleName);
}
