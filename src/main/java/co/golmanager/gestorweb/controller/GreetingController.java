package co.golmanager.gestorweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

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

    @GetMapping("/time")
    public String time() {
        OffsetDateTime now = OffsetDateTime.now();

        return ("Esta es la hora actual: "  + now);}
}
