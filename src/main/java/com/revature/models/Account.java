package com.revature.models;

import java.util.Objects;

public class Account {
	public int id;
	public int userId;
	public int accountType;
	public double balance;
	public boolean approved;

	public Account() {
		super();
	}

	public Account(int userId, int accountType, double balance, boolean approved) {
		super();
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.approved = approved;
	}

	public Account(int id, int userId, int accountType, double balance, boolean approved) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountType = accountType;
		this.balance = balance;
		this.approved = approved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean getApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", accountType=" + accountType + ", balance=" + balance
				+ ", approved=" + approved + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, approved, balance, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountType == other.accountType && approved == other.approved && balance == other.balance
				&& id == other.id && userId == other.userId;
	}

}
