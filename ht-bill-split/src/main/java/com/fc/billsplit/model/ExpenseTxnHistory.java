package com.fc.billsplit.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="expense_txn_history")
public class ExpenseTxnHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
    @JoinColumn
    private GroupExpense groupExpense;
	
	private String txn;
	private String desciption;
	private Double amount;
	@Column(length = 10)
	private String mobile_no;
	
	@Column(length = 10)
	private String updated_by_mob_no;
	
	private Date createdAt;
	public ExpenseTxnHistory() {
		super();
	}
	
	public ExpenseTxnHistory(int id, GroupExpense groupExpense, String txn, String desciption, Double amount,
			String mobile_no, String updated_by_mob_no, Date createdAt) {
		super();
		this.id = id;
		this.groupExpense = groupExpense;
		this.txn = txn;
		this.desciption = desciption;
		this.amount = amount;
		this.mobile_no = mobile_no;
		this.updated_by_mob_no = updated_by_mob_no;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GroupExpense getGroupExpense() {
		return groupExpense;
	}
	public void setGroupExpense(GroupExpense groupExpense) {
		this.groupExpense = groupExpense;
	}
	public String getTxn() {
		return txn;
	}
	public void setTxn(String txn) {
		this.txn = txn;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getUpdated_by_mob_no() {
		return updated_by_mob_no;
	}
	public void setUpdated_by_mob_no(String updated_by_mob_no) {
		this.updated_by_mob_no = updated_by_mob_no;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ExpenseTxnHistory [id=" + id + ", groupExpense=" + groupExpense + ", txn=" + txn + ", desciption="
				+ desciption + ", amount=" + amount + ", mobile_no=" + mobile_no + ", updated_by_mob_no="
				+ updated_by_mob_no + ", createdAt=" + createdAt + "]";
	}

}
