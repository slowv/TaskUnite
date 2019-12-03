package com.vbtn.taskunite.service.api.account;

import com.vbtn.taskunite.domain.UserInformation;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.repository.custom.CustomUserInformationRepository;
import com.vbtn.taskunite.service.UserInformationService;
import com.vbtn.taskunite.service.dto.UserInformationDTO;
import com.vbtn.taskunite.service.mapper.UserInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

}
