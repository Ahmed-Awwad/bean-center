package com.revature.hydra.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Tag;
import com.revature.hydra.question.data.QuestionRepository;
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
	
	public void createQuestion(Integer bucketId, String questionText, String[] answers, Integer[] tagIds) {
		List<Tag> tags = new ArrayList<>();;
		for (Integer i : tagIds) {
			tags.add(tagRepository.findByTagId(i));
		}
	}
	
}
