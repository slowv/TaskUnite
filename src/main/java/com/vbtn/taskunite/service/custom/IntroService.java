package com.vbtn.taskunite.service.custom;

import com.vbtn.taskunite.domain.Authority;
import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.repository.AuthorityRepository;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.repository.UserRepository;
import com.vbtn.taskunite.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class IntroService {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInformationRepository userInformationRepository;

    public void promoteMaster(User user) {
        Set<Authority> authorities = user.getAuthorities();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.MASTER));
        user.setAuthorities(authorities);
        userRepository.save(user);
    }
}
