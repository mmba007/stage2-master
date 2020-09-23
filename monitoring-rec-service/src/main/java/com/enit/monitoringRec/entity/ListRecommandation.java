package com.enit.monitoringRec.entity;

import java.io.Serializable;
import java.util.List;

public class ListRecommandation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Recommandation> listRecommandation;
	private String username;
	private String algoId;

	public String getAlgoId() {
		return algoId;
	}

	@Override
	public String toString() {
		return "ListRecommandation [listRecommandation=" + listRecommandation + ", username=" + username + ", algoId="
				+ algoId + "]";
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
