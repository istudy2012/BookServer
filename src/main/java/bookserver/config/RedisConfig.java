package bookserver.config;

import java.util.concurrent.TimeUnit;

public class RedisConfig {

    public static final int TOKEN_EXPIRE_TIME = 24;
    public static final TimeUnit TOKEN_EXPIRE_TIME_UNIT = TimeUnit.HOURS;

}
