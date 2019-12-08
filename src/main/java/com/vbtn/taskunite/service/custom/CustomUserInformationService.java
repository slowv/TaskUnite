package com.vbtn.taskunite.service.custom;

import com.vbtn.taskunite.domain.UserInformation;
import com.vbtn.taskunite.repository.custom.CustomUserInformationRepository;
import com.vbtn.taskunite.service.mapper.UserInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserInformation}.
 */
@Service
@Transactional
public class CustomUserInformationService {

    private final Logger log = LoggerFactory.getLogger(CustomUserInformationService.class);

    @Autowired
    private CustomUserInformationRepository customUserInformationRepository;

    private UserInformationMapper userInformationMapper;

    public CustomUserInformationService(UserInformationMapper userInformationMapper) {
        this.userInformationMapper = userInformationMapper;
    }

    /**
     * Get one userInformation by username.
     *
     * @param username the username of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserInformation> findOneByUsername(String username) {
        log.debug("Request to get UserInformation : {}", username);
        return customUserInformationRepository.findByUserLogin(username);
    }

    public UserInformation findOne(Long id) {
        return customUserInformationRepository.getOne(id);
    }

}
