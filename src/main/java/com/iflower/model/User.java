package com.iflower.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;



@Entity
@Table(name = "myuser")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public @Data class User {
	@Id
	private String username;
	private String email;
    private String password;

	public Map<String, String> toJson() {
		Map<String, String > entity = new LinkedHashMap<>();
		try {
			entity.put("username", username);
			entity.put("email", email);
			entity.put("password", password);
		} catch (Exception e) {}
		return entity;
	}

}
