package com.revature.models;

import java.util.Objects;

public class Users {
	public int id;
	public String username;
	public String password;
	public int permissions;

	public Users() {
		super();
	}

	public Users(String username, String password, int permissions) {
		super();
		this.username = username;
		this.password = password;
		this.permissions = permissions;
	}

	public Users(int id, String username, String password, int permissions) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", permissions=" + permissions + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, permissions, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return id == other.id && Objects.equals(password, other.password) && permissions == other.permissions
				&& Objects.equals(username, other.username);
	}

}
