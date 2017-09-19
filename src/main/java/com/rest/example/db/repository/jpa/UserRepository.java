package com.rest.example.db.repository.jpa;

import com.rest.example.entity.UserDAO;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
public interface UserRepository extends CrudRepository<UserDAO, Integer> {

}
