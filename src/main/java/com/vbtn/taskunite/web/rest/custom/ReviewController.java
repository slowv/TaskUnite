package com.vbtn.taskunite.web.rest.custom;

import java.util.logging.Logger;

import com.vbtn.taskunite.web.rest.custom.vm.ReviewVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vbtn.taskunite.domain.Review;
import com.vbtn.taskunite.domain.UserInformation;
import com.vbtn.taskunite.repository.ReviewRepository;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.service.UserService;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;
	Logger log = Logger.getLogger(this.getClass().getName());
	@Autowired
	private UserService userService;

	@Autowired
	private UserInformationRepository userInformationRepository;

	@GetMapping(path = "/detail")
	@ResponseBody
	private Review getReviewById(@RequestParam("id") long id) {
		Review review = reviewRepository.findById(id).get();
		return review;
	}

	@PostMapping(path = "/create")
	@ResponseBody
	private String createReview(ReviewVM request) {
		UserInformation userInformation = userInformationRepository.getOne(userService.getUserWithAuthorities().get().getId());
		Review review = new Review();
		review.setContent(request.getContent());
		review.setPoint(request.getPoint());
		review.setTask(request.getTask());
		review.setUser(userInformation);

		reviewRepository.save(review);
		return "ok";
	}

}
