package org.pruebatecnica.repository;

import org.pruebatecnica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByMail(String mail);
    boolean existsByMailAndPassword(String mail, String password);

    User findByMailAndPassword(String mail, String password);
}
