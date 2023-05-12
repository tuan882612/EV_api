package api.v1.base;

import api.v1.utility.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @GetMapping("")
    public ApiResponse<Void> BaseHandler() {
        return new ApiResponse<>(200, "Welcome to the API", null);
    }
}
