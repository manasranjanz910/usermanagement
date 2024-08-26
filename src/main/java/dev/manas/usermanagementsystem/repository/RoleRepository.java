package dev.manas.usermanagementsystem.repository;

import dev.manas.usermanagementsystem.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
}
