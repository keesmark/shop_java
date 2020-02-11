package jp.co.aivick.shop_spring;

import java.util.ArrayList;
import java.util.List;

public class StockStorage {
    private List<Pair<String, Integer>> stockPairList;

    public StockStorage() {
        this.stockPairList = new ArrayList<>();
    }

    public List<Pair<String, Integer>> getStockPairList() {
        return this.stockPairList;
    }

    public Pair<String, Integer> getStockListByName(String name) {
        Pair<String, Integer> result = null;

        for (Pair<String, Integer> pair : stockPairList
        ) {
            if (pair.getValue1().equals(name)) {
                result = pair;
            }
        }

        return result;
    }

    public void add(Stock stock) {
        stockPairList.add(new Pair<>(stock.getMenuName(), stock.getMenuStock()));
    }

    public void addAmount(String name, int amount) {
        for (Pair<String, Integer> pair : stockPairList
        ) {
            if (pair.getValue1().equals(name)) {
                pair.setValue2(pair.getValue2() + amount);
            }
        }
    }

    public String delAmount(String name, int amount) {
        String result = "";
        for (Pair<String, Integer> pair : stockPairList
        ) {
            if (pair.getValue1().equals(name)) {
                if (!(pair.getValue2() > amount)) {
                    result = "error!! delete amount is bigger than stock amount!!!";
                } else {
                    pair.setValue2(pair.getValue2() - amount);
                    result = "deleted amount";
                }
            }
        }
        return result;
    }

    public boolean nameCheck(String name) {
        for (Pair<String, Integer> pair : stockPairList
        ) {
            if (pair.getValue1().contains(name)) {
                return false;
            }
        }
        return true;
    }
}