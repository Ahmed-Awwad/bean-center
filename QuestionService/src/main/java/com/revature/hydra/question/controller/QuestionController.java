package com.revature.hydra.question.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Question;
import com.revature.hydra.question.data.QuestionRepository;
import com.revature.hydra.question.service.QuestionCompositionService;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
	
	private static final Logger log = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionCompositionService questionCompositionService;
	
	@RequestMapping(value = "/activateQuestion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> activateQuestion(@PathVariable(value="id") Integer questionId) {
		questionRepository.toggleQuestionStatusById(true, questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/deactivateQuestion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> deactivateQuestion(@PathVariable(value="id") Integer questionId) {
		questionRepository.toggleQuestionStatusById(false, questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/bucketQuestions/{bucketId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Question>> getBucketQuestions(@PathVariable(value="bucketId") Integer bucketId) {
		return new ResponseEntity<>(questionRepository.findByBucketId(bucketId), HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/createQuestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createQuestion(@RequestParam(value="bucketId") Integer bucketId, @RequestParam(value="text") String questionText, @RequestParam(value="answers") String[] answers, @RequestParam(value="tagIds") Integer[] tagIds) {
		questionCompositionService.createQuestion(bucketId, questionText, answers, tagIds);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
