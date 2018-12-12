package bookserver.token.manager.impl;

import bookserver.config.RedisConfig;
import bookserver.token.manager.TokenManager;
import bookserver.token.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        this.redis = redisTemplate;
//        //泛型设置成Long后必须更改对应的序列化方案
//        redis.setKeySerializer(new JdkSerializationRedisSerializer());
//    }

    @Override
    public TokenModel createToken(String userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        redisTemplate.boundValueOps(userId).set(token, RedisConfig.TOKEN_EXPIRE_TIME, RedisConfig.TOKEN_EXPIRE_TIME_UNIT);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = (String) redisTemplate.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(model.getUserId()).expire(RedisConfig.TOKEN_EXPIRE_TIME, RedisConfig.TOKEN_EXPIRE_TIME_UNIT);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        String userId = param[0];
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(String userId) {
        redisTemplate.delete(userId);
    }
}
