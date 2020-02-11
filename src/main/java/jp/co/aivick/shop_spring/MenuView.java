package jp.co.aivick.shop_spring;

public class MenuView {
    public String viewMenu(Menu menu) {
        return "Menu name : " + menu.getMenuName() + "\n" +
                "kind : " + menu.getKind() + "\n" +
                "price : " + menu.getPrice() + "\n" +
                "Recipe : " + menu.getRecipeList();
//        for (String recipeName : menu.getRecipeList()
//        ) {
//            recipeName;
//        }
    }
}