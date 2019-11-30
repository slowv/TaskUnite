package com.vbtn.taskunite.service.api.account;

import com.vbtn.taskunite.domain.Authority;
import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.domain.UserInformation;
import com.vbtn.taskunite.repository.AuthorityRepository;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.repository.UserRepository;
import com.vbtn.taskunite.security.AuthoritiesConstants;
import com.vbtn.taskunite.service.EmailAlreadyUsedException;
import com.vbtn.taskunite.service.RegisterService;
import com.vbtn.taskunite.service.UsernameAlreadyUsedException;
import com.vbtn.taskunite.service.util.RandomUtil;
import com.vbtn.taskunite.web.rest.client.vm.RegisterVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    private final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

    private final UserRepository userRepository;

    private final UserInformationRepository userInformationRepository;

    private final CacheManager cacheManager;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    public RegisterServiceImpl(UserRepository userRepository, UserInformationRepository userInformationRepository, CacheManager cacheManager, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
        this.cacheManager = cacheManager;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public User registerUser(RegisterVM userDTO, String password) {
        userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail().toLowerCase());
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setLangKey(userDTO.getLangKey());
        // new user is activated automatically
        newUser.setActivated(true);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        // attach user information
        UserInformation userExtend = new UserInformation();
        userExtend.setGender(userDTO.getGender());
        userExtend.setAddress(userDTO.getAddress());
        userExtend.setPhone(userDTO.getPhone());
        userExtend.setStatus(0);
        userExtend.setUser(newUser);
        userInformationRepository.save(userExtend);

        this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(User existingUser){
        if (existingUser.getActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        this.clearUserCaches(existingUser);
        return true;
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }
}
