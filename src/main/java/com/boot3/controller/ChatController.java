package com.boot3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Websocket Test";
    }

    @RequestMapping(value="/chat", method= RequestMethod.GET)
    public String chat(Locale locale, Model model){
        return "chat";
    }
}
