package com.cmcc.andedu.hdsyn.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="EDU_SYNC_HBB_ORDERS")
@SequenceGenerator(name = "EDU_SEQ_HBB_ORDERS",sequenceName="EDU_SEQ_HBB_ORDERS")
public class HbbOrderGdEntity implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EDU_SEQ_HBB_ORDERS")
	private Long id;
	@Column(name="TIMESTAMP")
	private String timeStamp;//毫秒级，例：1416374455361
	@Column(name="PROVINCE")
	private String province;//省参考地区编码表中的编码（广东为 440000）
	@Column(name="USER_ID")
	private Long userId;//用户id
	@Column(name="GROUPBILLINGID")
	private String groupBillingId;//组合包资费ID
	@Column(name="BOSSCODE")
	private String bossCode;//点播或包月业务省boss用的业务编码
	@Column(name="DN")
	private String dn;//手机号
	@Column(name="PHONE")
	private String phone;//用户手机号 ,正向订购必选
	@Column(name="TYPE")
	private int type;//业务类型：1：包月订购；2：包月退订；（1）当类型为包月订购，则需要填写订购时间、生效时间。（2）当类型为退订，则需要填写退订时间。
	@Column(name="FLOW_NO")
	private String flowNo;//支付流水号（正向时省侧生成，规则：“EDUBU”+ timestamp+7位随机数， timestamp+随机数 共20位数字）
	@Column(name="ORDER_TIME")
	private String orderTime;//订购时间，YYYY-MM-DD hh:mi:ss
	@Column(name="QUIT_TIME")
	private String quitTime;//退订时间，YYYY-MM-DD hh:mi:ss
	@Column(name="START_TIME")
	private String startTime;//生效时间，YYYY-MM-DD hh:mi:ss
	@Column(name="STUDENT_ID")
	private Long studentId;//学生id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getGroupBillingId() {
		return groupBillingId;
	}
	public void setGroupBillingId(String groupBillingId) {
		this.groupBillingId = groupBillingId;
	}
	public String getBossCode() {
		return bossCode;
	}
	public void setBossCode(String bossCode) {
		this.bossCode = bossCode;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getQuitTime() {
		return quitTime;
	}
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	public HbbOrderGdEntity() {
		super();
	}
	
	public HbbOrderGdEntity(String timeStamp, String province, Long userId, String bossCode, String dn, int type,
                            String flowNo) {
		super();
		this.timeStamp = timeStamp;
		this.province = province;
		this.userId = userId;
		this.bossCode = bossCode;
		this.dn = dn;
		this.type = type;
		this.flowNo = flowNo;
	}
	
	public HbbOrderGdEntity(Long id, String timeStamp, String province, Long userId, String groupBillingId, String bossCode,
                            String dn, String phone, int type, String flowNo, String orderTime, String quitTime, String startTime,
                            Long studentId) {
		super();
		this.id = id;
		this.timeStamp = timeStamp;
		this.province = province;
		this.userId = userId;
		this.groupBillingId = groupBillingId;
		this.bossCode = bossCode;
		this.dn = dn;
		this.phone = phone;
		this.type = type;
		this.flowNo = flowNo;
		this.orderTime = orderTime;
		this.quitTime = quitTime;
		this.startTime = startTime;
		this.studentId = studentId;
	}
	
	@Override
	public String toString() {
		return "HbbOrderGdEntity [id=" + id + ", timeStamp=" + timeStamp + ", province=" + province + ", userId=" + userId
				+ ", groupBillingId=" + groupBillingId + ", bossCode=" + bossCode + ", dn=" + dn + ", phone=" + phone
				+ ", type=" + type + ", flowNo=" + flowNo + ", orderTime=" + orderTime + ", quitTime=" + quitTime
				+ ", startTime=" + startTime + ", studentId=" + studentId + "]";
	}
	
	

}
