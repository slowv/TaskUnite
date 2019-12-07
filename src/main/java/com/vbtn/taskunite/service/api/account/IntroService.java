package com.vbtn.taskunite.service.api.account;

import com.vbtn.taskunite.domain.Authority;
import com.vbtn.taskunite.domain.Master;
import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.domain.UserInformation;
import com.vbtn.taskunite.repository.AuthorityRepository;
import com.vbtn.taskunite.repository.MasterRepository;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.repository.UserRepository;
import com.vbtn.taskunite.security.AuthoritiesConstants;
import com.vbtn.taskunite.service.UserInformationService;
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
    MasterRepository masterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInformationRepository userInformationRepository;

    public void promoteMaster(User user) {
        UserInformation userInformation = userInformationRepository.getOne(user.getId());

        Master m = new Master();
        m.setStatus(1);
        m.setUser(userInformation);
        masterRepository.save(m);

        Set<Authority> authorities = user.getAuthorities();
        authorities.add(authorityRepository.getOne(AuthoritiesConstants.MASTER));
        user.setAuthorities(authorities);
        userRepository.save(user);
    }
}
