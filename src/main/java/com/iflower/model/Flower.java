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
	private String name;

	@Column (columnDefinition = "LONGTEXT")
	private String description;

	private String image;

	public Map<String, String> toJson() {
		Map<String, String > entity = new LinkedHashMap<>();
		try {
			entity.put("name", name);
			entity.put("description", description);
			entity.put("image", image);
		} catch (Exception e) {}
		return entity;
	}

}
