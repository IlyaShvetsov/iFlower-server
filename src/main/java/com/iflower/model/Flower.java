package com.iflower.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;



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

	public Map<String, String> toJson() {
		Map<String, String > entity = new LinkedHashMap<>();
		try {
			entity.put("id", Integer.valueOf(id).toString());
			entity.put("name", name);
			entity.put("description", description);
		} catch (Exception e) {}
		return entity;
	}

}
