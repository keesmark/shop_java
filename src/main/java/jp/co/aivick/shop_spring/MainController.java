package jp.co.aivick.shop_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/hello")
    public String init() {
        return "hello.html";
    }

    @PostMapping("/hello")
    public String post(Model model, @RequestParam(name = "value1", required = false) int num1, @RequestParam(name = "value2", required = false) int num2) {

        int result  = num1 + num2;

        model.addAttribute("result", result);

        return "hello.html";
    }
}
