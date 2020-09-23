package com.enit.preferencesRec.entity;

import java.util.List;

public class ListRecommandation {
	private List<Recommandation> listRecommandation;
	private String username;
	private String algoId;

	public ListRecommandation(List<Recommandation> listRecommandation, String username) {
		this.algoId = "Preferences-Recommandation";
		this.listRecommandation = listRecommandation;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public List<Recommandation> getListRecommandation() {
		return listRecommandation;
	}

	public String getAlgoId() {
		return algoId;
	}

	@Override
	public String toString() {
		return "ListRecommandation [listRecommandation=" + listRecommandation + ", username=" + username + ", algoId="
				+ algoId + "]";
	}

	public ListRecommandation() {

	}
}
