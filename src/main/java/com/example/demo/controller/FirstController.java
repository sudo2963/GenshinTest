package com.example.demo.controller;

import com.example.demo.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FirstController extends BaseController {
    @GetMapping("/first/view")
    public ModelAndView firstPage() {
        logger.info("进入测试页面");
        return viewModel("demo/firstPage","txtFirst","进入测试页面");
    }
}
