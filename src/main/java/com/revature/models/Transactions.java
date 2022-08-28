package com.revature.models;

import java.util.Objects;

public class Transactions {
	public int id;
	public int fromAccount;
	public double amount;
	public int toAccount;

	public Transactions() {
		super();
	}

	public Transactions(int fromAccount, double amount, int toAccount) {
		super();
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.toAccount = toAccount;
	}

	public Transactions(int id, int fromAccount, double amount, int toAccount) {
		super();
		this.id = id;
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.toAccount = toAccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", fromAccount=" + fromAccount + ", amount=" + amount + ", toaccount=" + toAccount
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, fromAccount, id, toAccount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& fromAccount == other.fromAccount && id == other.id && toAccount == other.toAccount;
	}

	

}
