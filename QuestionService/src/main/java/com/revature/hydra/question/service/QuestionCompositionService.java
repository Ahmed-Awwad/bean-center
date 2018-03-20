package com.revature.hydra.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Question;
import com.revature.beans.QuestionTagLookup;
import com.revature.beans.Tag;
import com.revature.hydra.question.data.QuestionRepository;
import com.revature.hydra.question.data.QuestionTagLookupRepository;
import com.revature.hydra.question.data.TagRepository;

@Service
public class QuestionCompositionService {
	
	@Autowired
	AmqpTemplate rabbitTemplate;

	@Autowired
	public QuestionRepository questionRepository;
	
	@Autowired
	public TagRepository tagRepository;
	
	@Autowired
	public QuestionTagLookupRepository  questionTagLookupRepository;
	
	public void createQuestion(Integer bucketId, String questionText, String[] answers, Integer[] tagIds) {
		List<Tag> tags = new ArrayList<>();;
		for (Integer i : tagIds) {
			tags.add(tagRepository.findByTagId(i));
		}
		Question q = new Question(bucketId, true, questionText, answers[0], answers[1],
				answers[2], answers[3], answers[4]);
		q = questionRepository.save(q);
		for (Tag t : tags) {
			QuestionTagLookup qtl = new QuestionTagLookup(t, q);
			questionTagLookupRepository.save(qtl);
		}
	}
	
}
