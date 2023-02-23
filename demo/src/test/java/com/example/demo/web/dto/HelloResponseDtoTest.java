package com.example.demo.web.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {

	@Test
	public void 이게맞니() {
		// given
		String name = "test";
		int amount = 1000;

		// when
		HelloResponseDto dto = new HelloResponseDto(name, amount);
		
		assertThat(dto.getName()).isEqualTo(name);
	    assertThat(dto.getAmount()).isEqualTo(amount);
	}
}
