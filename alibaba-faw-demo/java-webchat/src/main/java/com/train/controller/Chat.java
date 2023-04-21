package com.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author PanXin
 * @version $ Id: Chat, v 0.1 2023/04/19 17:32 PanXin Exp $
 */
@Controller
public class Chat {

    @GetMapping("/index.html")
    public String chat() {
        return "chat";
    }
    @GetMapping("/")
    public String index() {
        return "chat";
    }
}