package bookserver.token.controller;

import bookserver.error.ErrorStatus;
import bookserver.token.manager.TokenManager;
import bookserver.token.model.TokenModel;
import bookserver.user.entity.User;
import bookserver.user.repository.UserRepository;
import bookserver.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/token")
    @ResponseBody
    public Object login(@RequestParam String userName, @RequestParam String userPassword) {
        User user = userRepository.findByNameAndPassword(userName, userPassword);

        if (user == null) {
            return ResponseUtils.failure(HttpStatus.NOT_FOUND.value(), ErrorStatus.USERNAME_OR_PASSWORD_ERROR);
        }

        TokenModel tokenModel = tokenManager.createToken(user.getPid());
        return ResponseUtils.success(tokenModel);
    }

    @DeleteMapping("/token")
    @ResponseBody
    public Object logout(User user) {
        tokenManager.deleteToken(user.getPid());
        return ResponseUtils.success();
    }
}
