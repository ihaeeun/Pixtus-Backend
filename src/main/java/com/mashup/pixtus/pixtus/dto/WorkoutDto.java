package com.mashup.pixtus.pixtus.dto;

import com.mashup.pixtus.pixtus.entity.Workout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkoutDto {

	private String exerciseName;

	private int totalKcal;
	
	public WorkoutDto(Workout workout) {
		this.exerciseName = workout.getExerciseName();
		this.totalKcal = workout.getTotalKcal();
	}

}
