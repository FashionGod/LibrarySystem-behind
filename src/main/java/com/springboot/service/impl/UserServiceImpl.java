package com.springboot.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.AnotationUserDao;
import com.springboot.dao.MyBatisXMLUserDao;
import com.springboot.entity.User;
import com.springboot.service.UserService;

/**
 * @author shang.shi and zeming.yang
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AnotationUserDao userDao;
	@Autowired
	MyBatisXMLUserDao userMbDao;

	User user = new User();

	@Override
	public String checkPassword(String userName, String password) {

		user = userDao.findByUsername(userName);
		if (user != null && password.equals(user.getPassword())) {
			String authority = user.getAuthority();
			return authority;
		}
		return null;
	}

	@Override
	public User getUser(String userId) {
		user = userDao.findById(userId);
		return user;
	}

	@Override
	public boolean addUser(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String nowTime = sdf.format(date);
		boolean result = userDao.addUser(user.getUserid(), user.getUsername(), user.getPassword(),
				user.getDescription(), user.getAuthority(), user.getEmail(), user.getBirthday(), user.getSex(),
				user.getGrade(), user.getInterest(), user.getComment());
		if (result) {
			// System.out.println("add User
			// sucess------------------------------------------------------------------");
			return true;
		}
		// System.out.println("add User
		// fail------------------------------------------------------------------");
		return false;
	}

	@Override
	public int getUserId(String userName) {
		int userId = userDao.findByUsername(userName).getUserid();
		return userId;
	}

	@Override
	public boolean checkUserThere(String userName) {
		user = userDao.findByUsername(userName);
		if (user != null) {
			String authority = user.getAuthority();
			return true;
		}

		return false;
	}

	@Override
	public boolean registerLog(int userId, String time) {
		boolean result = userDao.registerLogById(userId, time);
		if (result) {
			// System.out.println("add log
			// sucess------------------------------------------------------------------");
			return true;
		} else {
			// System.out.println("add log
			// fail------------------------------------------------------------------");
		}
		return false;
	}

	@Override
	public User getUserInfo(String userName) {
		return this.userDao.getUserInfo(userName);
	}

	@Override
	public boolean updateUserInfo(User user) {
		return this.userDao.updateUserInfo(user);
	}

}
