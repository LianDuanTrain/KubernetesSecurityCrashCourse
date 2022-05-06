package com.docker.restful.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.docker.restful.demo.entities.User;

@Service
public class UserService {
	 Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private RedisTemplate redisTemplate;
//	Map<String, User> map = new ConcurrentHashMap<String, User>();

	public List<User> findByPattern() {
		
		List<User> list = new ArrayList<User>();
		Set<String> set = redisTemplate.keys("*");
		for(String key :set) {
			User user = (User)redisTemplate.opsForValue().get(key);
			list.add(user);
		}
		logger.info("Find User. User size is   {}", list.size());
		return  list;
	}

	public User findById(final String userId) {
		User user = (User)redisTemplate.opsForValue().get(userId);
		//User user = map.get(userId);
		if (user == null) {
			throw new NotFoundException("User does not exist in the DB");
		}
		return user;
	}

	public void save(final User user) {
		
		String id = user.getId();
		logger.info("Add User. ID is {}", id);
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		operations.set(id, user);
		//map.put(id, user);

	}

	public void update(final User user) {
		
		if (redisTemplate.hasKey(user.getId())) {
			String id = UUID.randomUUID().toString();
			user.setId(id);
			ValueOperations<String, User> operations = redisTemplate.opsForValue();
			operations.set(id, user);
		} else {
			throw new NotFoundException("User does not exist in the DB");
		}
	}

	public void delete(final String userId) {

		if (redisTemplate.hasKey(userId)) {
			logger.info("Delete User. ID is {}", userId);
			redisTemplate.delete(userId);
			
		} else {
			throw new NotFoundException("User does not exist in the DB");
		}
	}

}
