package com.enit.randomrecommandationservice.entity;

import java.util.List;

public class ListRecommandation {
    private List<Recommandation> listRecommandation;

    public ListRecommandation(List<Recommandation> listRecommandation) {
        this.listRecommandation = listRecommandation;
    }

    public List<Recommandation> getListRecommandation() {
        return listRecommandation;
    }
    public ListRecommandation()
    {
        
    }
}
