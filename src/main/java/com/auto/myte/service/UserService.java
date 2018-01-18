package com.auto.myte.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.auto.myte.entity.Role;
import com.auto.myte.entity.User;
import com.auto.myte.mapper.UserMapper;

@Component
public class UserService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserMapper userMapper;

	public UserDetails loadUserByUsername(String eName) {

		User user = userMapper.findByUserName(eName);
		if (user == null) {
			throw new UsernameNotFoundException("ユーザ存在しない");
		}
		//
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		// 用于添加用户的权限。只要把用户权限添加到authorities
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRname()));
		}
		logger.info("user : " + user.toString());

		return new org.springframework.security.core.userdetails.User(user.getEid(), user.getPassword(), authorities);
	}

}
