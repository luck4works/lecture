package com.example.hongpark.objectmapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurgerTest {
	
	@Test
	public void 자바객체_JSON으로() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		List<String> ingredients = Arrays.asList("통새우패티", "순쇠고기패티", "토마토", "양상추", "치즈");
		Burger burger = new Burger("맥날 슈비", 5500, ingredients);
		
		String actual = om.writeValueAsString(burger);
		
		String expected = "{\"name\":\"맥날 슈비\",\"price\":5500,\"ingredients\":[\"통새우패티\",\"순쇠고기패티\",\"토마토\",\"양상추\",\"치즈\"]}";
		assertEquals(expected, actual);
		log.info(actual);
	}
}
