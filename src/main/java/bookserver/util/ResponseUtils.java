package bookserver.util;

import bookserver.message.ErrorMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static ResponseEntity success() {
        return success(null);
    }

    public static ResponseEntity success(Object body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity failure(int status) {
        return failure(status, null);
    }

    public static ResponseEntity failure(int status, Object body) {
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity failure(int status, String key, String message) {
        return failure(status, createErrorMessage(key, message));
    }

    public static ResponseEntity create() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public static ResponseEntity put() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public static ResponseEntity post() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public static ResponseEntity delete() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public static ResponseEntity response(int status, Object body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    public static Map<String, Object> createData(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        return map;
    }

    public static ErrorMessage createErrorMessage(String errorKey, String message) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setKey(errorKey);
        errorMessage.setMessage(message);
        return errorMessage;
    }
}
