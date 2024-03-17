package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/check")
    public String index(@RequestParam("name") String name) {

        return "Hello, Seshu! " + name + "!";
    }
}
