package com.enit.authentication.service;

import com.enit.authentication.model.Role;
import com.enit.authentication.model.RoleName;
import com.enit.authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

 public  class RoleConverter {


    public static Set<Role> convertAllToRole(Set<String> rolesSet, RoleRepository roleRepository)
    {   Set<Role> roles=new HashSet<>();
        rolesSet.forEach(role -> {
            switch (role) {
                case "admin":
                    System.out.println(roleRepository.findByName(RoleName.ROLE_ADMIN));

                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    System.out.println("list: "+ roles);

                    break;
                case "advertiser":
                    Role advertiserRole = roleRepository.findByName(RoleName.ROLE_ADVERTISER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(advertiserRole);

                    break;
                case "consumer":
                    Role consumerRole = roleRepository.findByName(RoleName.ROLE_CONSUMER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(consumerRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
    return roles;
    }
}
