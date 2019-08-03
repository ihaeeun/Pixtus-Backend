package com.mashup.pixtus.pixtus.meal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mashup.pixtus.pixtus.meal.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

	// TODO uid, dateId 인덱스 추가 필요
	List<Meal> findByUidAndDateId(String uid, String dateId);

	List<Meal> findByUidAndDateBetween(String uid, String startDate, String endDate);
}
