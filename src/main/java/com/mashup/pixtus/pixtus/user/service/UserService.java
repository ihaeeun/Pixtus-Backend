package com.mashup.pixtus.pixtus.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mashup.pixtus.pixtus.stage.StageService;
import com.mashup.pixtus.pixtus.user.UserRepository;
import com.mashup.pixtus.pixtus.user.dto.ReqUserDto;
import com.mashup.pixtus.pixtus.user.dto.ReqUserSignUpDto;
import com.mashup.pixtus.pixtus.user.entity.User;

@Service
public class UserService {

	private UserRepository userRepository;
	private StageService stageService;

	public UserService(UserRepository userRepository, StageService stageService) {
		this.userRepository = userRepository;
		this.stageService = stageService;
	}

	private static final int SIGN_UP_LEVEL = 1;

	public boolean isExisted(String uid, String email) {
		return userRepository.findById(uid).filter(u -> u.getEmail().equals(email)).isPresent();
	}

	@Transactional
	public void signUp(ReqUserSignUpDto requestBody) {
		// TODO 이미 회원일 경우 예외 추가하기
		if (isExisted(requestBody.getUid(), requestBody.getEmail()))
			throw new RuntimeException();

		User user = User.from(requestBody);

		user.setLevel(stageService.getStage(SIGN_UP_LEVEL));

		userRepository.save(user);
	}

	public User get(String uid) {
		// TODO 유저 없을 때 예외 추가
		return userRepository.findById(uid).orElseThrow(RuntimeException::new);
	}

	@Transactional
	public void increaseExp(String uid, int exp) {
		User user = userRepository.findById(uid).orElseThrow(RuntimeException::new);
		user.increaseExp(exp);

		if (user.isLevelUp()) {
			user.setLevel(stageService.getNextStage(user.getLevel()));
		}
	}

	@Transactional
	public void decreaseExp(String uid, int exp) {
		User user = userRepository.findById(uid).orElseThrow(RuntimeException::new);
		user.decreaseExp(exp);

		if (user.isLevelDown()) {
			user.setLevel(stageService.getPrevStage(user.getLevel()));
		}
	}
}
