package jp.co.aivick.shop_spring;

import java.util.ArrayList;
import java.util.List;

public class SalesController {
    private SalesStorage salesStorage;
    private MenuController menuController;
    private StockStorage stockStorage;

    public SalesController(SalesStorage salesStorage, MenuController menuController, StockStorage stockStorage) {
        this.salesStorage = salesStorage;
        this.menuController = menuController;
        this.stockStorage = stockStorage;
    }

    public String buy(int money, List<String> nameList) {
        int totalPrice = 0;
        String result = "";
        List<Pair<String, Integer>> namePricePair = new ArrayList<>();

        for (String name : nameList
        ) {
            if (menuController.checkSameName(name)) {
                result = "We dont have the Menu!!!";
            } else {
                if (1 <= stockStorage.getStockListByName(name).getValue2()) {
                    totalPrice += menuController.getTotalMenuPrice(name);
                    namePricePair.add(new Pair<>(name, menuController.getTotalMenuPrice(name)));
                    stockStorage.getStockListByName(name).setValue2(stockStorage.getStockListByName(name).getValue2() - 1);
                } else {
                    result = "No enough stock amount!!!!";
                }
            }
        }

        if (totalPrice <= money) {
            for (Pair<String, Integer> pair : namePricePair
            ) {
                salesStorage.add(pair.getValue1(), pair.getValue2());
                result = pair.getValue1() + "買いました！";
            }
        } else {
            result = "Not enough Money!!!";
        }
        return result;
    }

    public String showSales() {
        StringBuilder result = new StringBuilder();
        for (Sales sales : salesStorage.getSalesList()
        ) {
            result.append(sales.getMenuName()).append(" : ").append("Total price : ").append(sales.getTotalPrice()).append(" Total amount : ").append(sales.getSalesAmount());
        }

        return result.toString();
    }
}