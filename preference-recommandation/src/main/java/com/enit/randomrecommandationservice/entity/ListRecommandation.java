package com.enit.randomrecommandationservice.entity;

import java.util.List;

public class ListRecommandation {
    private List<Recommandation> listRecommandation;
    private String username;


    public ListRecommandation(List<Recommandation> listRecommandation,String username) {
        this.listRecommandation = listRecommandation;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public List<Recommandation> getListRecommandation() {
        return listRecommandation;
    }

    @Override
    public String toString() {
        return "ListRecommandation{" +
                "listRecommandation=" + listRecommandation +
                ", username='" + username + '\'' +
                '}';
    }

    public ListRecommandation()
    {
        
    }
}
