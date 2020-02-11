package jp.co.aivick.shop_spring;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private MenuStorage menuStorage;
    private RecipeStorage recipeStorage;

    public MenuController(MenuStorage menuStorage, RecipeStorage recipeStorage) {
        this.menuStorage = menuStorage;
        this.recipeStorage = recipeStorage;
    }

    public String create(String menuName, String kind, int price, List<String> recipeList) {
        if (checkSameName(menuName)) {
            if (!(kind.equals("wa") || kind.equals("yo") || kind.equals("chu"))) {
                return "Choose menu kind from wa or yo or chu";
            } else {
                menuStorage.add(new Menu(menuName, kind, price, recipeList));
            }
            return "created Menu!";
        } else {
            return "We have same Menu Name!!!!";
        }
    }

    public boolean checkSameName(String menuName) {
        for (Menu menu : menuStorage.getMenuList()
        ) {
            if (menu.getMenuName().equals(menuName)) {
                return false;
            }
        }
        return true;
    }

    public String showMenu(String name) {
        if (checkSameName(name)) {
            return "we dont have the menu!!";
        }
        Menu menu = menuStorage.getMenu(name);
        MenuView menuView = new MenuView();
        return menuView.viewMenu(menu);
    }

    public String menuStatus(List<String> nameList) {
        List<Integer> totalPrice = new ArrayList<>();
        List<Integer> totalCal = new ArrayList<>();
        int totalMenuSize = nameList.size();

        for (String name : nameList
        ) {
            totalPrice.add(menuStorage.getMenu(name).getPrice());
            totalCal.add(recipeStorage.getCalByName(menuStorage.getMenu(name).getRecipeList()));
        }


        return "Average price : " + average(totalPrice) + "\n" +
                "Max price : " + max(totalPrice) + "\n" +
                "Min price : " + min(totalPrice) + "\n" +
                "Average calories : " + total(totalCal) / totalMenuSize + "\n" +
                "Max calories : " + max(totalCal) + "\n" +
                "Min calories : " + min(totalCal) + "\n";
    }

    public List<String> getAllRecipeName() {
        List<String> result = new ArrayList<>();
        for (Menu menu : this.menuStorage.getMenuList()
        ) {
            result.add(menu.getMenuName());
        }
        return result;
    }

    public int getTotalMenuPrice(String menuName) {
        int result = 0;

        for (Menu menu : menuStorage.getMenuList()
        ) {
            if (menu.getMenuName().equals(menuName)) {
                result += menu.getPrice();
            }
        }
        return result;
    }

    public int total(List<Integer> list) {
        int result = 0;
        for (int num : list
        ) {
            result += num;
        }
        return result;
    }

    public int average(List<Integer> list) {
        int result = 0;
        int size = list.size();

        for (int num : list
        ) {
            result += num;
        }
        return result / size;
    }

    public int max(List<Integer> list) {
        int result = list.get(0);
        for (int num : list
        ) {
            if (result < num) {
                result = num;
            }
        }
        return result;
    }

    public int min(List<Integer> list) {
        int result = list.get(0);
        for (int num : list
        ) {
            if (num < result) {
                result = num;
            }
        }
        return result;
    }
}