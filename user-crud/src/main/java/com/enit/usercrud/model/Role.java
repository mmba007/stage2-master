package com.enit.usercrud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

//@Entity
//@Table(name = "roles")

@Component
@Document(indexName = "roles", type = "_doc")
public class Role {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private String id;

//    @Enumerated(EnumType.STRING)
//    @NaturalId
//    @Column(length = 60)
    private RoleName name;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}


}