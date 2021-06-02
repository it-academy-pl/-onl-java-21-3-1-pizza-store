package itacademy.pizzastore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringWeb {

    @GetMapping("/hello")
    public HelloResponse sayHello() {
        return new HelloResponse("Hello Spring WEB!");
    }

    @GetMapping("/hello/{name}")
    public HelloResponse sayHelloToName(@PathVariable String name) {
        return new HelloResponse("Hello " + name + "!");
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class HelloResponse {
        private String message;
    }
}
