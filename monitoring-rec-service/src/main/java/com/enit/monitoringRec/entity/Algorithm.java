package com.enit.monitoringRec.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "algorithm")
public class Algorithm {
	
	@Id
	private String algoId;
	private int score = 0;

	@Override
	public String toString() {
		return "Algorithm [algoId=" + algoId + ", score=" + score + "]";
	}

	public void incrementScore() {
		this.score++;
	}

	public String getAlgoId() {
		return algoId;
	}

	public void setAlgoId(String algoId) {
		this.algoId = algoId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Algorithm(String algoId, int score) {
		super();
		this.algoId = algoId;
		this.score = score;
	}

	public Algorithm(String algoId) {
		super();
		this.algoId = algoId;
		this.score = 0;
	}

	public Algorithm() {
		super();
		// TODO Auto-generated constructor stub
	}

}
