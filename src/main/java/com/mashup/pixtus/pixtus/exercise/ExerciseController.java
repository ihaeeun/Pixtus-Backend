package com.mashup.pixtus.pixtus.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

	@Autowired
	private ExerciseService exerciseService;

	@GetMapping("")
	public ResponseEntity list() {
		return ResponseEntity.status(HttpStatus.OK).body(exerciseService.getAllExcludeWalk());
	}

}
