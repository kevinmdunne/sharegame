package com.sharegame.dal.sql;

import java.util.ArrayList;
import java.util.List;

import com.sharegame.model.user.User;

public class UserSQLGenerator implements SQLGenerator {

	@Override
	public String getSQL(Object object) {
		User user = (User) object;
		List<String> criteria = new ArrayList<String>();

		String hql = "FROM User u";

		if (user.getFirstname() != null && !user.getFirstname().isEmpty()) {
			criteria.add(" u.firstname=" + "'" + user.getFirstname() + "'");
		}

		if (user.getSurname() != null && !user.getSurname().isEmpty()) {
			criteria.add(" u.surname=" + "'" + user.getSurname() + "'");
		}

		if (user.getUsername() != null && !user.getSurname().isEmpty()) {
			criteria.add(" u.username=" + "'" + user.getUsername() + "'");
		}

		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			criteria.add(" u.password=" + "'" + user.getPassword() + "'");
		}
		
		if(user.getEmail() != null && user.getEmail().isEmpty()){
			criteria.add(" u.email=" + "'" + user.getEmail() + "'");
		}

		if (!criteria.isEmpty()) {
			hql = hql + " WHERE";
			hql = hql + criteria.get(0);
			for (int i = 1; i < criteria.size(); i++) {
				hql = hql + " AND" + criteria.get(i);
			}
		}

		return hql;
	}

}
