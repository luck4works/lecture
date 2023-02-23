package com.example.demo.config.auth.dto;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.Account;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		if ("naver".equals(registrationId)) {
			return ofNaver("id", attributes);
		}
		return ofGoogle(userNameAttributeName, attributes);
	}

	// 1
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder().name((String) attributes.get("name")).email((String) attributes.get("email"))
				.picture((String) attributes.get("picture")).attributes(attributes)
				.nameAttributeKey(userNameAttributeName).build();
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		
		Map<?, ?> response = (Map<?, ?>) attributes.get("response");
		Map<String, Object> map = new HashMap<>();
		map.put("id", response.get("id"));
		map.put("profile_image", response.get("profile_image"));
		map.put("email", response.get("email"));
		map.put("name", response.get("name"));
		
		//map.put(key, remappingFunction);
		return OAuthAttributes.builder().name((String) response.get("name")).email((String) response.get("email"))
				.picture((String) response.get("profile_image")).attributes(map)
				.nameAttributeKey(userNameAttributeName).build();
	}

	// 2
	public Account toEntity() {
		return Account.builder().name(name).email(email).picture(picture).role(Role.GUEST).build();
	}
}