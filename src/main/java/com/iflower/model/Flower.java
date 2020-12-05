package com.iflower.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;


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

	public String toJson() {
		StringBuilder res = new StringBuilder("{\"id\":");
		res.append(id);

		if (name != null) {
			res.append(",\"name\":").append(name);
		} else {
			res.append(",\"name\":").append(" ");
		}

		if (description != null) {
			res.append(",\"description\":").append(description);
		} else {
			res.append(",\"description\":").append(" ");
		}

		return res.append("}").toString();
	}

}
