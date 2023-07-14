package com.pzobenko.petproject.demo.repository;

import com.pzobenko.petproject.demo.domain.RefreshToken;
import com.pzobenko.petproject.demo.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findRefreshTokenByTokenForRefresh(String tokenForRefresh);

  Optional<RefreshToken> findRefreshTokenByOwnerOfToken(User ownerOfToken);
}
