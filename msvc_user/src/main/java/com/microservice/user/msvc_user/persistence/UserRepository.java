package com.microservice.user.msvc_user.persistence;

import com.microservice.user.msvc_user.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
