package cn.com.payment.v2.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * AccountStatement
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
public class AccountStatement implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	  /** 
	   *主键ID
	   */
  	   private Long adjustId;
	  /** 
	   *调账人ID
	   */
  	   private Long operatorId;
	  /** 
	   *账户ID
	   */
  	   private Long accountId;
	  /** 
	   *子账号类型0-可用余额1-冻结余额2-待结算余额
	   */
  	   private String subAccType;
	  /** 
	   *财务类型0-收款1-分润2-提现3-人工调账
	   */
  	   private String financialType;
	  /** 
	   *调整方式0-入账1-出账
	   */
  	   private String adjustType;
	  /** 
	   *调账前余额
	   */
  	   private Long beforBalance;
	  /** 
	   *调账后余额
	   */
  	   private Long afterBalance;
	  /** 
	   *调账金额
	   */
  	   private Long adjustAmount;
	  /** 
	   *状态 0-无效1-有效
	   */
  	   private String state;
	  /** 
	   *备注
	   */
  	   private String remark;
	  /** 
	   *创建时间
	   */
  	   private Date createTime;
	  /** 
	   *修改时间
	   */
  	   private Date updateTime;
  
  
  public AccountStatement(){}
  public AccountStatement(Long adjustId,Long operatorId,Long accountId,String subAccType,String financialType,String adjustType,Long beforBalance,Long afterBalance,Long adjustAmount,String state,String remark,Date createTime,Date updateTime){
  	   this.adjustId = adjustId;
  	   this.operatorId = operatorId;
  	   this.accountId = accountId;
  	   this.subAccType = subAccType;
  	   this.financialType = financialType;
  	   this.adjustType = adjustType;
  	   this.beforBalance = beforBalance;
  	   this.afterBalance = afterBalance;
  	   this.adjustAmount = adjustAmount;
  	   this.state = state;
  	   this.remark = remark;
  	   this.createTime = createTime;
  	   this.updateTime = updateTime;
  }
 
 	private AccountStatement(AccountStatementBuilder builder){
  	   this.adjustId = builder.adjustId;
  	   this.operatorId = builder.operatorId;
  	   this.accountId = builder.accountId;
  	   this.subAccType = builder.subAccType;
  	   this.financialType = builder.financialType;
  	   this.adjustType = builder.adjustType;
  	   this.beforBalance = builder.beforBalance;
  	   this.afterBalance = builder.afterBalance;
  	   this.adjustAmount = builder.adjustAmount;
  	   this.state = builder.state;
  	   this.remark = builder.remark;
  	   this.createTime = builder.createTime;
  	   this.updateTime = builder.updateTime;
	 }
 
  	  /**
	   * 主键ID
	   * @return adjustId
	   */
	  public Long getAdjustId(){
	      return adjustId;
	  }
	  /**
	   * 主键ID
	   * @param adjustId 
	   */
	  public void setAdjustId(Long adjustId){
	      this.adjustId = adjustId;
	  }
  	  /**
	   * 调账人ID
	   * @return operatorId
	   */
	  public Long getOperatorId(){
	      return operatorId;
	  }
	  /**
	   * 调账人ID
	   * @param operatorId 
	   */
	  public void setOperatorId(Long operatorId){
	      this.operatorId = operatorId;
	  }
  	  /**
	   * 账户ID
	   * @return accountId
	   */
	  public Long getAccountId(){
	      return accountId;
	  }
	  /**
	   * 账户ID
	   * @param accountId 
	   */
	  public void setAccountId(Long accountId){
	      this.accountId = accountId;
	  }
  	  /**
	   * 子账号类型0-可用余额1-冻结余额2-待结算余额
	   * @return subAccType
	   */
	  public String getSubAccType(){
	      return subAccType;
	  }
	  /**
	   * 子账号类型0-可用余额1-冻结余额2-待结算余额
	   * @param subAccType 
	   */
	  public void setSubAccType(String subAccType){
	      this.subAccType = subAccType;
	  }
  	  /**
	   * 财务类型0-收款1-分润2-提现3-人工调账
	   * @return financialType
	   */
	  public String getFinancialType(){
	      return financialType;
	  }
	  /**
	   * 财务类型0-收款1-分润2-提现3-人工调账
	   * @param financialType 
	   */
	  public void setFinancialType(String financialType){
	      this.financialType = financialType;
	  }
  	  /**
	   * 调整方式0-入账1-出账
	   * @return adjustType
	   */
	  public String getAdjustType(){
	      return adjustType;
	  }
	  /**
	   * 调整方式0-入账1-出账
	   * @param adjustType 
	   */
	  public void setAdjustType(String adjustType){
	      this.adjustType = adjustType;
	  }
  	  /**
	   * 调账前余额
	   * @return beforBalance
	   */
	  public Long getBeforBalance(){
	      return beforBalance;
	  }
	  /**
	   * 调账前余额
	   * @param beforBalance 
	   */
	  public void setBeforBalance(Long beforBalance){
	      this.beforBalance = beforBalance;
	  }
  	  /**
	   * 调账后余额
	   * @return afterBalance
	   */
	  public Long getAfterBalance(){
	      return afterBalance;
	  }
	  /**
	   * 调账后余额
	   * @param afterBalance 
	   */
	  public void setAfterBalance(Long afterBalance){
	      this.afterBalance = afterBalance;
	  }
  	  /**
	   * 调账金额
	   * @return adjustAmount
	   */
	  public Long getAdjustAmount(){
	      return adjustAmount;
	  }
	  /**
	   * 调账金额
	   * @param adjustAmount 
	   */
	  public void setAdjustAmount(Long adjustAmount){
	      this.adjustAmount = adjustAmount;
	  }
  	  /**
	   * 状态 0-无效1-有效
	   * @return state
	   */
	  public String getState(){
	      return state;
	  }
	  /**
	   * 状态 0-无效1-有效
	   * @param state 
	   */
	  public void setState(String state){
	      this.state = state;
	  }
  	  /**
	   * 备注
	   * @return remark
	   */
	  public String getRemark(){
	      return remark;
	  }
	  /**
	   * 备注
	   * @param remark 
	   */
	  public void setRemark(String remark){
	      this.remark = remark;
	  }
  	  /**
	   * 创建时间
	   * @return createTime
	   */
	  public Date getCreateTime(){
	      return createTime;
	  }
	  /**
	   * 创建时间
	   * @param createTime 
	   */
	  public void setCreateTime(Date createTime){
	      this.createTime = createTime;
	  }
  	  /**
	   * 修改时间
	   * @return updateTime
	   */
	  public Date getUpdateTime(){
	      return updateTime;
	  }
	  /**
	   * 修改时间
	   * @param updateTime 
	   */
	  public void setUpdateTime(Date updateTime){
	      this.updateTime = updateTime;
	  }
 
 	 public static class AccountStatementBuilder{
		private Long adjustId; // 主键ID
		private Long operatorId; // 调账人ID
		private Long accountId; // 账户ID
		private String subAccType; // 子账号类型0-可用余额1-冻结余额2-待结算余额
		private String financialType; // 财务类型0-收款1-分润2-提现3-人工调账
		private String adjustType; // 调整方式0-入账1-出账
		private Long beforBalance; // 调账前余额
		private Long afterBalance; // 调账后余额
		private Long adjustAmount; // 调账金额
		private String state; // 状态 0-无效1-有效
		private String remark; // 备注
		private Date createTime; // 创建时间
		private Date updateTime; // 修改时间
		 /**
	   * 主键ID
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addAdjustId(Long adjustId) {
			this.adjustId = adjustId;
			return this;
		}
		 /**
	   * 调账人ID
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addOperatorId(Long operatorId) {
			this.operatorId = operatorId;
			return this;
		}
		 /**
	   * 账户ID
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addAccountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}
		 /**
	   * 子账号类型0-可用余额1-冻结余额2-待结算余额
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addSubAccType(String subAccType) {
			this.subAccType = subAccType;
			return this;
		}
		 /**
	   * 财务类型0-收款1-分润2-提现3-人工调账
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addFinancialType(String financialType) {
			this.financialType = financialType;
			return this;
		}
		 /**
	   * 调整方式0-入账1-出账
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addAdjustType(String adjustType) {
			this.adjustType = adjustType;
			return this;
		}
		 /**
	   * 调账前余额
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addBeforBalance(Long beforBalance) {
			this.beforBalance = beforBalance;
			return this;
		}
		 /**
	   * 调账后余额
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addAfterBalance(Long afterBalance) {
			this.afterBalance = afterBalance;
			return this;
		}
		 /**
	   * 调账金额
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addAdjustAmount(Long adjustAmount) {
			this.adjustAmount = adjustAmount;
			return this;
		}
		 /**
	   * 状态 0-无效1-有效
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addState(String state) {
			this.state = state;
			return this;
		}
		 /**
	   * 备注
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addRemark(String remark) {
			this.remark = remark;
			return this;
		}
		 /**
	   * 创建时间
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addCreateTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}
		 /**
	   * 修改时间
	   * @return AccountStatementBuilder
	   */
		public AccountStatementBuilder addUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
			return this;
		}
	
		public AccountStatement build(){
			return new AccountStatement(this);
		}
	 }
}