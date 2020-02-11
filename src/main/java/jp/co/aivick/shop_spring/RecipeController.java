package jp.co.aivick.shop_spring;

public class RecipeController {
    private RecipeStorage recipeStorage;

    public RecipeController(RecipeStorage recipeStorage) {
        this.recipeStorage = recipeStorage;
    }

    public String create(String recipeName, int cal) {
        return recipeStorage.add(new Recipe(recipeName, cal));
    }

    public String searchRecipe(String name) {
        if (recipeStorage.getRecipeList().containsKey(name)) {
            return "Recipe name : " + name + "Calories : " + recipeStorage.getRecipeList().get(name);
        } else {
            return "we dont have the recipe name!!!";
        }
    }
}