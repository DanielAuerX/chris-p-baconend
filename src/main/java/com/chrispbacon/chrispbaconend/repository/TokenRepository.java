package com.chrispbacon.chrispbaconend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chrispbacon.chrispbaconend.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Query(value = """
      select t from Token t inner join Student u\s
      on t.student.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(UUID id);

    Optional<Token> findByToken(String token);
}
