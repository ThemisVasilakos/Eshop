package gr.mindthecode.eshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/hellouser")
    public String getUser(){
        return "Hello User";
    }

    @GetMapping("/helloadmin")
    public String getAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/hellomod")
    public String getMod(){
        return "Hello Moderator";
    }
}
