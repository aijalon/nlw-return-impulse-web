/**
 * 
 */
package com.feedbackwidget.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedbackwidget.domain.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

	//@Query("from Feedback where uuid= :uuid")
	Optional<Feedback> findByUuid(String uuid);
	
}
