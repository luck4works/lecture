package com.example.hongpark.objectmapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurgerTest {
	
	@Test
	public void 자바객체_JSON으로() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		List<String> ingredients = Arrays.asList("통새우패티", "순쇠고기패티", "토마토", "양상추", "치즈");
		Burger burger = new Burger("맥날 슈비", 5500, ingredients);
		
		String actual = om.writeValueAsString(burger); //자바객체 -> json
		
		String expected = "{\"name\":\"맥날 슈비\",\"price\":5500,\"ingredients\":[\"통새우패티\",\"순쇠고기패티\",\"토마토\",\"양상추\",\"치즈\"]}";
		assertEquals(expected, actual);
		
		//json 이쁘게 출력
		JsonNode jsonNode = om.readTree(actual);
		log.info(jsonNode.toPrettyString());
	}
	
	@Test
	public void JSON_자바객체로() throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String jsonStr = "{\"name\":\"맥날 슈비\",\"price\":5500,\"ingredients\":[\"통새우패티\",\"순쇠고기패티\",\"토마토\",\"양상추\",\"치즈\"]}";
		
		Burger actual = om.readValue(jsonStr, Burger.class); //json -> 자바객체
		List<String> ingredients = Arrays.asList("통새우패티", "순쇠고기패티", "토마토", "양상추", "치즈");
		Burger expected = new Burger("맥날 슈비", 5500, ingredients);
		
		assertEquals(expected.toString(), actual.toString());
		
		//json 이쁘게 출력
		JsonNode jsonNode = om.readTree(jsonStr);
		log.info(jsonNode.toPrettyString());
		log.info(actual.toString());

	}
}
