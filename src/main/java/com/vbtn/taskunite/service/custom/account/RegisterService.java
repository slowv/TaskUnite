package com.vbtn.taskunite.service.custom.account;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.*;
import com.vbtn.taskunite.security.AuthoritiesConstants;
import com.vbtn.taskunite.service.EmailAlreadyUsedException;
import com.vbtn.taskunite.service.UsernameAlreadyUsedException;
import com.vbtn.taskunite.service.util.RandomUtil;
import com.vbtn.taskunite.web.rest.custom.vm.RegisterVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class RegisterService {

    private final Logger log = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private PaymentInformationRepository paymentInformationRepository;
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;

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
        newUser.setLangKey("vi");
        // new user is activated automatically
        newUser.setActivated(true);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        // attach user information
        UserInformation userInformation = new UserInformation();
        userInformation.setAddress(userDTO.getAddress());
        userInformation.setGender(userDTO.getGender());
        userInformation.setPhone(userDTO.getPhone());
        userInformation.setStatus(0);
        userInformation.setUser(newUser);
        userInformationRepository.save(userInformation);
        // payment
        PaymentInformation paymentInformation = new PaymentInformation();
        paymentInformation.setBalance(0.0);
        paymentInformation.setHold(0.0);
        paymentInformation.setUser(userInformation);
        paymentInformationRepository.save(paymentInformation);
        //
        Statistic statistic = new Statistic();
        statistic.setLevel(1);
        statistic.setExperience(0);
        statistic.setCompletedTask(0);
        statistic.setRating(-1);
        statistic.setUser(userInformation);
        statisticRepository.save(statistic);

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
