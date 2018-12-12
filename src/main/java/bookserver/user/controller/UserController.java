package bookserver.user.controller;

import bookserver.error.ErrorStatus;
import bookserver.user.entity.User;
import bookserver.user.repository.UserRepository;
import bookserver.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/v1") // This means URL's start with /demo (after Application path)
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @ResponseBody
    public Object users() {
        logger.debug("userRepository: " + userRepository);

        Iterable<User> users = userRepository.findAll();

        List<User> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(user);
        }

        return ResponseUtils.success(ResponseUtils.createData(userList));
    }

    @GetMapping(value = "/users/{id}")
    @ResponseBody
    public Object getUserInfo(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return ResponseUtils.success(userOptional.get());
        } else {
            return ResponseUtils.failure(404, ErrorStatus.USER_NOT_EXIST);
        }
    }

    @PutMapping(value = "/users")
    @ResponseBody
    public Object putUserInfo(@RequestBody User user) {
        userRepository.save(user);
        return ResponseUtils.put();
    }

    @PostMapping(value = "/users/{id}")
    @ResponseBody
    public Object postUserInfo(@PathVariable Long id, @RequestBody User user) {
        if (!userRepository.existsById(id)) {
            return ResponseUtils.failure(404, ErrorStatus.USER_NOT_EXIST);
        }

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User newUser = userOptional.get();
            if (user.getUserName() != null) {
                newUser.setUserName(user.getUserName());
            }
            if (user.getNickName() != null) {
                newUser.setNickName(user.getNickName());
            }
            if (user.getUserAvatar() != null) {
                newUser.setUserAvatar(user.getUserAvatar());
            }

            userRepository.save(newUser);
        }

        return ResponseUtils.post();
    }

    @DeleteMapping(value = "/users/{id}")
    @ResponseBody
    public Object deleteUserInfo(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseUtils.failure(404, ErrorStatus.USER_NOT_EXIST);
        }
        userRepository.deleteById(id);
        return ResponseUtils.delete();
    }
}
