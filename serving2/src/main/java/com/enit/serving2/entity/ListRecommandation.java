package com.enit.serving2.entity;

import java.util.List;

public class ListRecommandation {
	private List<Recommandation> listRecommandation;
	private String username;
	private String algoId;

	public String getAlgoId() {
		return algoId;
	}

	public String getUsername() {
		return username;
	}

	public List<Recommandation> getListRecommandation() {
		return listRecommandation;
	}

	public ListRecommandation() {

	}

	public ListRecommandation(List<Recommandation> listRecommandation, String username, String algoId) {
		super();
		this.listRecommandation = listRecommandation;
		this.username = username;
		this.algoId = algoId;
	}
}