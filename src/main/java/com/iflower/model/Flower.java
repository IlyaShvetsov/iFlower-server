package com.iflower.model;

import lombok.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.LinkedHashMap;


@Entity
@Table(name = "myflower")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public @Data class Flower {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String description;

//	public String toJson() {
//		StringBuilder res = new StringBuilder("{\"id\":");
//		res.append(id);
//
//		if (name != null) {
//			res.append(",\"name\":\"").append(name).append("\"");
//		} else {
//			res.append(",\"name\":\"").append(" \"");
//		}
//
//		if (description != null) {
//			res.append(",\"description\":\"").append(description).append("\"");
//		} else {
//			res.append(",\"description\":").append(" \"");
//		}
//
//		return res.append("}").toString();
//	}

	public LinkedHashMap<String, String> toJson() {
		LinkedHashMap<String, String > entity = new LinkedHashMap<>();
		try {
			entity.put("id", Integer.valueOf(id).toString());
			entity.put("name", name);
			entity.put("description", description);
		} catch (Exception e) {}
		return entity;
	}

}
