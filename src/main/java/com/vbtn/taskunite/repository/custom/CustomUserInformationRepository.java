package com.vbtn.taskunite.repository.custom;

import com.vbtn.taskunite.domain.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the UserInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomUserInformationRepository extends JpaRepository<UserInformation, Long> {
    Optional<UserInformation> findByUserLogin(String username);
}
