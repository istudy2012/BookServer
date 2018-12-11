package bookserver.config;

import java.util.concurrent.TimeUnit;

public class RedisConfig {

    public static int TOKEN_EXPIRE_TIME = 24;
    public static TimeUnit TOKEN_EXPIRE_TIME_UNIT = TimeUnit.HOURS;
}
