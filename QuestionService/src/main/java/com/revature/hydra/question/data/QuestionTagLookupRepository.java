package com.revature.hydra.question.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.beans.QuestionTagLookup;

public interface QuestionTagLookupRepository extends JpaRepository<QuestionTagLookup, Integer> {
	
}
