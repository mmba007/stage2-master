package com.enit.usercrud.repository;

import java.util.Optional;

import com.enit.usercrud.model.Role;
import com.enit.usercrud.model.RoleName;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends ElasticsearchRepository<Role, String> {
    Optional<Role> findByName(RoleName roleName);
}
