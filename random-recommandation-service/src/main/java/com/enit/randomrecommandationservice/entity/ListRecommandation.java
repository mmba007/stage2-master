package com.enit.randomrecommandationservice.entity;

import java.util.List;

public class ListRecommandation {
    private List<Recommandation> listRecommandation;
    private String username;


    public ListRecommandation(List<Recommandation> listRecommandation,String username) {
        this.listRecommandation = listRecommandation;
        this.username=username;
    }

    public List<Recommandation> getListRecommandation() {
        return listRecommandation;
    }
    public ListRecommandation()
    {
        
    }
}
