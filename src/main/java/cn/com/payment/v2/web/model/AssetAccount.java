package cn.com.payment.v2.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * AssetAccount
 *
 * Date:2019-3-2 15:09:00
 * @author dl
 */
public class AssetAccount implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	  /** 
	   *主键ID
	   */
  	   private Long accountId;
	  /** 
	   *账户类型
	   */
  	   private String accType;
	  /** 
	   *账户余额
	   */
  	   private Long balance;
	  /** 
	   *未入账余额
	   */
  	   private Long balanceUnaccounted;
	  /** 
	   *冻结余额
	   */
  	   private Long balanceFreeze;
	  /** 
	   *可用余额
	   */
  	   private Long balanceValid;
	  /** 
	   *创建时间
	   */
  	   private Date createTime;
	  /** 
	   *修改时间
	   */
  	   private Date updateTime;
	  /** 
	   *备注
	   */
  	   private String remark;
	  /** 
	   *是否删除
	   */
  	   private String isDel;
  
  
  public AssetAccount(){}
  public AssetAccount(Long accountId,String accType,Long balance,Long balanceUnaccounted,Long balanceFreeze,Long balanceValid,Date createTime,Date updateTime,String remark,String isDel){
  	   this.accountId = accountId;
  	   this.accType = accType;
  	   this.balance = balance;
  	   this.balanceUnaccounted = balanceUnaccounted;
  	   this.balanceFreeze = balanceFreeze;
  	   this.balanceValid = balanceValid;
  	   this.createTime = createTime;
  	   this.updateTime = updateTime;
  	   this.remark = remark;
  	   this.isDel = isDel;
  }
 
 	private AssetAccount(AssetAccountBuilder builder){
  	   this.accountId = builder.accountId;
  	   this.accType = builder.accType;
  	   this.balance = builder.balance;
  	   this.balanceUnaccounted = builder.balanceUnaccounted;
  	   this.balanceFreeze = builder.balanceFreeze;
  	   this.balanceValid = builder.balanceValid;
  	   this.createTime = builder.createTime;
  	   this.updateTime = builder.updateTime;
  	   this.remark = builder.remark;
  	   this.isDel = builder.isDel;
	 }
 
  	  /**
	   * 主键ID
	   * @return accountId
	   */
	  public Long getAccountId(){
	      return accountId;
	  }
	  /**
	   * 主键ID
	   * @param accountId 
	   */
	  public void setAccountId(Long accountId){
	      this.accountId = accountId;
	  }
  	  /**
	   * 账户类型
	   * @return accType
	   */
	  public String getAccType(){
	      return accType;
	  }
	  /**
	   * 账户类型
	   * @param accType 
	   */
	  public void setAccType(String accType){
	      this.accType = accType;
	  }
  	  /**
	   * 账户余额
	   * @return balance
	   */
	  public Long getBalance(){
	      return balance;
	  }
	  /**
	   * 账户余额
	   * @param balance 
	   */
	  public void setBalance(Long balance){
	      this.balance = balance;
	  }
  	  /**
	   * 未入账余额
	   * @return balanceUnaccounted
	   */
	  public Long getBalanceUnaccounted(){
	      return balanceUnaccounted;
	  }
	  /**
	   * 未入账余额
	   * @param balanceUnaccounted 
	   */
	  public void setBalanceUnaccounted(Long balanceUnaccounted){
	      this.balanceUnaccounted = balanceUnaccounted;
	  }
  	  /**
	   * 冻结余额
	   * @return balanceFreeze
	   */
	  public Long getBalanceFreeze(){
	      return balanceFreeze;
	  }
	  /**
	   * 冻结余额
	   * @param balanceFreeze 
	   */
	  public void setBalanceFreeze(Long balanceFreeze){
	      this.balanceFreeze = balanceFreeze;
	  }
  	  /**
	   * 可用余额
	   * @return balanceValid
	   */
	  public Long getBalanceValid(){
	      return balanceValid;
	  }
	  /**
	   * 可用余额
	   * @param balanceValid 
	   */
	  public void setBalanceValid(Long balanceValid){
	      this.balanceValid = balanceValid;
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
	   * 是否删除
	   * @return isDel
	   */
	  public String getIsDel(){
	      return isDel;
	  }
	  /**
	   * 是否删除
	   * @param isDel 
	   */
	  public void setIsDel(String isDel){
	      this.isDel = isDel;
	  }
 
 	 public static class AssetAccountBuilder{
		private Long accountId; // 主键ID
		private String accType; // 账户类型
		private Long balance; // 账户余额
		private Long balanceUnaccounted; // 未入账余额
		private Long balanceFreeze; // 冻结余额
		private Long balanceValid; // 可用余额
		private Date createTime; // 创建时间
		private Date updateTime; // 修改时间
		private String remark; // 备注
		private String isDel; // 是否删除
		 /**
	   * 主键ID
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addAccountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}
		 /**
	   * 账户类型
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addAccType(String accType) {
			this.accType = accType;
			return this;
		}
		 /**
	   * 账户余额
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addBalance(Long balance) {
			this.balance = balance;
			return this;
		}
		 /**
	   * 未入账余额
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addBalanceUnaccounted(Long balanceUnaccounted) {
			this.balanceUnaccounted = balanceUnaccounted;
			return this;
		}
		 /**
	   * 冻结余额
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addBalanceFreeze(Long balanceFreeze) {
			this.balanceFreeze = balanceFreeze;
			return this;
		}
		 /**
	   * 可用余额
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addBalanceValid(Long balanceValid) {
			this.balanceValid = balanceValid;
			return this;
		}
		 /**
	   * 创建时间
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addCreateTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}
		 /**
	   * 修改时间
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
			return this;
		}
		 /**
	   * 备注
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addRemark(String remark) {
			this.remark = remark;
			return this;
		}
		 /**
	   * 是否删除
	   * @return AssetAccountBuilder
	   */
		public AssetAccountBuilder addIsDel(String isDel) {
			this.isDel = isDel;
			return this;
		}
	
		public AssetAccount build(){
			return new AssetAccount(this);
		}
	 }
}