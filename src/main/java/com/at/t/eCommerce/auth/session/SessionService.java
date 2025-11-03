package com.at.t.eCommerce.auth.session;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

	private final RedisTemplate<String, String> redis;
	private final Duration refreshTtl;

	public SessionService(RedisTemplate<String, String> redis,
			@Value("${jwt.refresh-token-expiration}") long refreshMillies) {

		this.redis = redis;
		this.refreshTtl = Duration.ofMillis(refreshMillies);
	}

	private String sessionKey(String jti) {
		return "session:" + jti;
	}

	private String userSessionKey(String username) {
		return "user-sessions:" + username;
	}

	private String refreshKey(String jti) {
		return "refresh:" + jti;
	}

	public void createSession(String jti, String username, String refreshHash, Map<String, String> meta) {
		
		if(meta == null) {
			meta = new HashMap<String, String>();
		}

		String sKey = sessionKey(jti);
		redis.opsForHash().putAll(sKey, meta);
		redis.opsForHash().put(sKey, "username", username);
		redis.expire(sKey, refreshTtl);

		redis.opsForValue().set(refreshKey(jti), refreshHash, refreshTtl);
		redis.opsForSet().add(userSessionKey(username), jti);
	}

	public boolean sessionExists(String jti) {
		return Boolean.TRUE.equals(redis.hasKey(sessionKey(jti)));
	}

	public void replaceRefresh(String jti, String newHash) {
		redis.opsForValue().set(refreshKey(jti), newHash, refreshTtl);
		redis.expire(sessionKey(jti), refreshTtl);
	}

	public void revokeSession(String jti, String username) {
		redis.delete(sessionKey(jti));
		redis.delete(refreshKey(jti));
		redis.delete(userSessionKey(username));

	}

	public void revokeAllForUser(String username) {
		Set<String> jtis = redis.opsForSet().members(userSessionKey(username));
		if (jtis != null) {
			jtis.forEach(j -> {
				redis.delete(sessionKey(j));
				redis.delete(refreshKey(j));
			});
		}
		redis.delete(userSessionKey(username));
	}

	public String getStoredRefreshHash(String jti) {
		return redis.opsForValue().get(refreshKey(jti));
	}

	public String sessionKeyFor(String jti) {
		return sessionKey(jti);
	};

	public String refreshKeyFor(String jti) {
		return refreshKey(jti);
	}

}
