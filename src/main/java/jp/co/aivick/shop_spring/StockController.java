package jp.co.aivick.shop_spring;

import java.util.ArrayList;
import java.util.List;

public class StockController {
    private StockStorage stockStorage;
    private MenuStorage menuStorage;

    public StockController(StockStorage stockStorage, MenuStorage menuStorage) {
        this.stockStorage = stockStorage;
        this.menuStorage = menuStorage;
    }

    public String addToStock(String name, int amount) {
        if (checkMenuName(name)) {
            if (!stockStorage.nameCheck(name)) {
                stockStorage.addAmount(name, amount);
                return "Added stock";
            } else {
                stockStorage.add(new Stock(name, amount));
                return "Added new stock";
            }
        } else {
            return "We dont have the Menu name!!!";
        }
    }

    public String delToStock(String name, int amount) {
        if (checkMenuName(name)) {
            if (!stockStorage.nameCheck(name)) {
                return stockStorage.delAmount(name, amount);
            }
        } else {
            return "We dont have the Menu name!!!";
        }
        return "something wrong";
    }

    public String getAllStocks() {
        String result = "";
        for (Pair<String, Integer> pair : stockStorage.getStockPairList()
        ) {
            result += "Menu name : " + pair.getValue1() + " Stock amount : " + pair.getValue2();
        }
        return result;

//        result.append("Menu name : ").append(pair.getValue1()).append(" Stock amount : ").append(pair.getValue2());
//    }
//        return result.toString();
    }

    public String getKindStock(String kind) {
        List<String> nameList = getKindMenu(kind);
        StringBuilder result = new StringBuilder();
        for (String name : nameList
        ) {
            result.append(stockStorage.getStockListByName(name).getValue1()).append(" : ").append(stockStorage.getStockListByName(name).getValue2());
        }
        return result.toString();
    }

    public List<String> getKindMenu(String kind) {
        List<String> result = new ArrayList<>();

        for (Menu menu : menuStorage.getMenuList()
        ) {
            if (menu.getKind().equals(kind)) {
                result.add(menu.getMenuName());
            }
        }
        return result;
    }

    public String getStockByName(String name) {
        return stockStorage.getStockListByName(name).getValue1() + " : " + stockStorage.getStockListByName(name).getValue2();
    }

    public boolean checkMenuName(String name) {
        for (Menu menu : menuStorage.getMenuList()
        ) {
            if (menu.getMenuName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStockAmount(String name) {
        for (Pair<String, Integer> pair : stockStorage.getStockPairList()
        ) {
            if (pair.getValue1().equals(name) && 1 <= pair.getValue2()) {
                return true;
            }
        }

        return false;
    }
}