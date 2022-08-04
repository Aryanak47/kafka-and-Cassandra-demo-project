package example.micronaut;

import example.micronaut.storage.User;
import io.micronaut.http.annotation.Get;

import java.util.Map;

@io.micronaut.http.annotation.Controller("/user")
class Controller {

    private final Service service;

    Controller(Service service) {
        this.service = service;
    }

    @Get
    Map<User, Long> getUsers() {
        return service.getUserTransaction();
    }
}
