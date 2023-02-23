package com.example.hongpark.objectmapper;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Burger {
	private String name;
	private int price;
	private List<String> ingredients;
}
