package com.vbtn.taskunite.service;

import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.web.rest.client.vm.RegisterVM;

public interface RegisterService {
    User registerUser(RegisterVM userDTO, String password);
}
