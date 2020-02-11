package jp.co.aivick.shop_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private RecipeStorage recipeStorage = new RecipeStorage();
    private RecipeController recipeController = new RecipeController(recipeStorage);
    private MenuStorage menuStorage = new MenuStorage();
    private MenuController menuController = new MenuController(menuStorage, recipeStorage);
    private StockStorage stockStorage = new StockStorage();
    private StockController stockController = new StockController(stockStorage, menuStorage);
    private SalesStorage salesStorage = new SalesStorage();
    private SalesController salesController = new SalesController(salesStorage, menuController, stockStorage);

    @GetMapping("/")
    public String init() {
        return "index.html";
    }

    @PostMapping("/")
    public String post(Model model, @RequestParam(name = "op", required = false) String op, @RequestParam(name = "value", required = false) String value) {

        List<String> commandLine = Arrays.asList(value.split(" "));

        int commandSize = commandLine.size();
        String result = "";
        try {
            switch (op) {
                case "makerecipe":
                    result = recipeController.create(commandLine.get(0), Integer.parseInt(commandLine.get(1)));
                    break;
                case "showrecipe":
                    result = recipeController.searchRecipe(commandLine.get(0));
                    break;

                case "makemenu":
                    List<String> nameList = new ArrayList<>();
                    for (int i = 3; i < commandSize; i++) {
                        nameList.add(commandLine.get(i));
                    }
                    result = menuController.create(commandLine.get(0), commandLine.get(1), Integer.parseInt(commandLine.get(2)), nameList);
                    break;

                case "showmenu":
                    result = menuController.showMenu(commandLine.get(0));
                    break;

                case "menustatus":
                    List<String> menuNameList = new ArrayList<>();
                    if (commandLine.get(0).equals("")) {
                        menuNameList = menuController.getAllRecipeName();
                    } else {
                        for (int i = 0; i < commandSize; i++) {
                            menuNameList.add(commandLine.get(i));
                        }
                    }
                    result = menuController.menuStatus(menuNameList);
                    break;

                case "showstock":
                    if (commandLine.get(0).equals("")) {
                        result = stockController.getAllStocks();
                    } else if (commandLine.get(0).equals("wa") || commandLine.get(0).equals("yo") || commandLine.get(0).equals("chu")) {
                        result =stockController.getKindStock(commandLine.get(0));
                    } else {
                        result = stockController.getStockByName(commandLine.get(0));
                    }
                    break;

                case "addstock":
                    result = stockController.addToStock(commandLine.get(0), Integer.parseInt(commandLine.get(1)));
                    break;

                case "delstock":
                    result = stockController.delToStock(commandLine.get(0), Integer.parseInt(commandLine.get(1)));
                    break;

                case "buy":
                    List<String> menuCommandList = new ArrayList<>();
                    for (int i = 1; i < commandSize; i++) {
                        menuCommandList.add(commandLine.get(i));
                    }
                    result = salesController.buy(Integer.parseInt(commandLine.get(0)), menuCommandList);
                    break;

                case "showsales":
                    result = salesController.showSales();
                    break;
            }
        } catch (ShopRuntimeException e) {
            result = e.getMessage();
        }

        model.addAttribute("result", result);
        return "index.html";
    }
}
