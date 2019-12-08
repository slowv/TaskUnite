package com.vbtn.taskunite.web.rest.custom.api;

import java.util.logging.Logger;

import com.vbtn.taskunite.web.rest.custom.vm.ReviewVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vbtn.taskunite.domain.Review;
import com.vbtn.taskunite.domain.UserInformation;
import com.vbtn.taskunite.repository.ReviewRepository;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.service.UserService;

@RestController
@RequestMapping(path = "/room")
public class ReviewAPI {

	@Autowired
	private ReviewRepository reviewRepository;
	Logger log = Logger.getLogger(this.getClass().getName());
	@Autowired
	private UserService userService;

	@Autowired
	private UserInformationRepository userInformationRepository;



}
