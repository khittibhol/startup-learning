package org.workspace.model;

import java.math.BigDecimal;

/**
 * @author khittibhol
 *
 */
public class ExpensiveMobileModel {

	private String mobile;
	private BigDecimal timing;
	private BigDecimal expense;


	public ExpensiveMobileModel(String mobile, BigDecimal timing, BigDecimal expense) {
		super();
		this.mobile = mobile;
		this.timing = timing;
		this.expense = expense;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getTiming() {
		return timing;
	}

	public void setTiming(BigDecimal timing) {
		this.timing = timing;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

}
