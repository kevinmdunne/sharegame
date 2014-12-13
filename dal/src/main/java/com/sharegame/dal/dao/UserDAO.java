package com.sharegame.dal.dao;

import com.sharegame.model.user.User;

public class UserDAO extends AbstractDAO<User>{

	@Override
	public Class<User> getModelClass() {
		return User.class;
	}

}
