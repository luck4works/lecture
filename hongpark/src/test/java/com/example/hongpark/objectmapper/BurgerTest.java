package com.example.hongpark.objectmapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BurgerTest {
	
	@Test
	public void javaobj_to_JSONstr() throws JsonProcessingException {
		
		//자바객체
		List<String> ingredients = Arrays.asList("통새우패티", "순쇠고기패티", "토마토", "양상추", "치즈");
		Burger burger = new Burger("맥날 슈비", 5500, ingredients);		

		//자바객체에서 만들어진 실제 jsonstr
		ObjectMapper om = new ObjectMapper();
		String actual = om.writeValueAsString(burger); //자바객체 -> jsonstr
		
		//예상 jsonstr
		String expected = "{\"name\":\"맥날 슈비\",\"price\":5500,\"ingredients\":[\"통새우패티\",\"순쇠고기패티\",\"토마토\",\"양상추\",\"치즈\"]}";
		
		assertEquals(expected, actual);
		
		//실제 jsonstr 이쁘게 출력
		JsonNode jsonNode = om.readTree(actual);
		log.info(burger.toString());
		log.info(jsonNode.toPrettyString());
	}
	
	@Test
	public void JSONstr_to_javaobj() throws JsonMappingException, JsonProcessingException {

		//jsonstr
		String jsonStr = "{\"name\":\"맥날 슈비\",\"price\":5500,\"ingredients\":[\"통새우패티\",\"순쇠고기패티\",\"토마토\",\"양상추\",\"치즈\"]}";

		//jsonstr에서 만들어진 실제 자바 객체
		ObjectMapper om = new ObjectMapper();
		Burger actual = om.readValue(jsonStr, Burger.class); //jsonstr -> 자바객체
		
		//예상 자바 객체
		List<String> ingredients = Arrays.asList("통새우패티", "순쇠고기패티", "토마토", "양상추", "치즈");
		Burger expected = new Burger("맥날 슈비", 5500, ingredients);
		
		assertEquals(expected.toString(), actual.toString());
		
		//jsonstr 이쁘게 출력
		//jsonstr -> jsonobj -> prettystr
		JsonNode jsonNode = om.readTree(jsonStr);
		log.info(jsonNode.toPrettyString()); //jsonstr
		log.info(actual.toString()); //실제 자바객체

	}
	
	@Test
	public void JSONobj_to_javaobj() throws JsonProcessingException {
		
		//jsonobj 생성
		ObjectMapper om = new ObjectMapper();
		ObjectNode objectNode = om.createObjectNode();
		objectNode.put("name", "맥날 슈비");
		objectNode.put("price", "5500");

		//배열을 넣으려면
		ArrayNode arrayNode = om.createArrayNode();
		arrayNode.add("통새우패티");
		arrayNode.add("순쇠고기패티");
		arrayNode.add("토마토");
		arrayNode.add("양상추");
		arrayNode.add("치즈");
		
		//objectNode.put("ingredients", arrayNode); put은 deprecated
		objectNode.set("ingredients", arrayNode); // set 사용
		
		//실제
		String jsonStr = objectNode.toString(); //jsonobj -> jsonstr
		Burger actual = om.readValue(jsonStr, Burger.class); //jsonstr -> 자바객체
		
		
		//예상 자바 객체
		List<String> ingredients = Arrays.asList("통새우패티", "순쇠고기패티", "토마토", "양상추", "치즈");
		Burger expected = new Burger("맥날 슈비", 5500, ingredients);

		assertEquals(expected.toString(), actual.toString());
		
		//jsonstr 이쁘게 출력
		JsonNode jsonNode = om.readTree(jsonStr);
		log.info(jsonNode.toPrettyString()); //jsonstr -> jsonobj -> prettystr 
		log.info(objectNode.toPrettyString());  //직접 objectNode 사용
		log.info(actual.toString()); //실제 자바객체
	}
	
	//정리
	//objectNode -> .toString() -> jsonStr
	//jsonStr -> ObjectMapper.readValue(jsonStr, .class) -> javaobj
	//javaobj -> ObjectMapper.writeValueAsString(javaobj) -> jsonStr
}
