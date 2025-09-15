package co.golmanager.gestorweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

    @GetMapping("/sayHelloPublic")
    public String SayHello() {
        return "Hello, API JAAX!";
    }

    @GetMapping("/sayHelloProtected")
    public String SayHelloProtected() {
        return "Ah perro tienes un Token!";
    }

}
