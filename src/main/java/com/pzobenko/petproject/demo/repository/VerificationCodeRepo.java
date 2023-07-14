package com.pzobenko.petproject.demo.repository;

import com.pzobenko.petproject.demo.domain.VerificationCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepo extends JpaRepository<VerificationCode, Long> {
  Optional<VerificationCode> getVerificationCodeByEmailCode(String code);

}
