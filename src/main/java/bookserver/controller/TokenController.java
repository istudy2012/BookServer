package bookserver.controller;

import bookserver.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/token")
    @ResponseBody
    public Object login(@RequestParam String userName, @RequestParam String userPassword) {
//        userRepository.findById()
        return null;
    }
}
