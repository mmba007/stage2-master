package com.enit.randomRec.entity;

import java.util.List;

public class ListRecommandation {
	private List<Recommandation> listRecommandation;
	private String username;
	private String algoId;

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "ListRecommandation [listRecommandation=" + listRecommandation + ", username=" + username + ", algoId="
				+ algoId + "]";
	}

	public String getAlgoId() {
		return algoId;
	}

	public ListRecommandation(List<Recommandation> listRecommandation, String username) {
		this.algoId = "Random-Recommandation";
		this.listRecommandation = listRecommandation;
		this.username = username;
	}

	public List<Recommandation> getListRecommandation() {
		return listRecommandation;
	}

	public ListRecommandation() {

	}
}
