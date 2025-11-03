package com.at.t.eCommerce.auth.session;

import java.time.Duration;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

@Component
public class RefreshLua {

    private final RedisTemplate<String, String> redis;

    private static final String SCRIPT = """
        local refreshKey = KEYS[1]
        local sessionKey = KEYS[2]
        local providedHash = ARGV[1]
        local newHash = ARGV[2]
        local ttlSeconds = tonumber(ARGV[3])

        local current = redis.call('GET', refreshKey)
        if not current then
            return 0
        end
        if current ~= providedHash then
            return -1
        end
        redis.call('SET', refreshKey, newHash, 'EX', ttlSeconds)
        redis.call('EXPIRE', sessionKey, ttlSeconds)
        return 1
        """;

    private final DefaultRedisScript<Long> script = new DefaultRedisScript<>(SCRIPT, Long.class);

    public RefreshLua(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    public long rotate(String refreshKey, String sessionKey, String providedHash, String newHash, Duration ttl) {
        Long result = redis.execute(script, List.of(refreshKey, sessionKey),
                providedHash, newHash, String.valueOf(ttl.toSeconds()));
        return result == null ? 0 : result;
    }
}
