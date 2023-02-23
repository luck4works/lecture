package com.example.hongpark.ioc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Chef {
   IngredientFactory ingredientFactory;

    public String cook(String menu) {
        Ingredient ingredient = ingredientFactory.get(menu);
        return ingredient.getName() + menu;
    }
}
