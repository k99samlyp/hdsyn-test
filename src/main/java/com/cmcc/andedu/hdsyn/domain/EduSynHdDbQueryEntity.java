package com.cmcc.andedu.hdsyn.domain;

public class EduSynHdDbQueryEntity {

	private int id;//主键ID

	private String rec_Type;//中间记录标记

	private String msg_Id;//消息序列号

	private String chrg_Dn;//计费用户号码

	private String third_Dn;//第三方号码

	private String user_Type;//计费号码用户类型

	private String oper_Type;//业务类型

	private String sale_Mode;//优惠模式

	private String discount;//折扣率

	private String chrg_Type;//计费类型

	private String sp_Code;//SP代码

	private String oper_Code;//业务代码

	private int info_Fee;//信息费

	private int discount_Fee;//优惠后信息费

	private String chrg_Prov;//计费用户号码归属省

	private String access_Type;//定制方式

	private String apply_Time;//用户申请时间

	private String finish_Time;//话单记录生成时间

	private String channel_Id;//渠道代码

	private String imsi;//IMSI

	private String imei;//手机终端设备标识

	private String create_Time;//记录创建时间

	private String batchNum;//批次号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRec_Type() {
		return rec_Type;
	}

	public void setRec_Type(String rec_Type) {
		this.rec_Type = rec_Type;
	}

	public String getMsg_Id() {
		return msg_Id;
	}

	public void setMsg_Id(String msg_Id) {
		this.msg_Id = msg_Id;
	}

	public String getChrg_Dn() {
		return chrg_Dn;
	}

	public void setChrg_Dn(String chrg_Dn) {
		this.chrg_Dn = chrg_Dn;
	}

	public String getThird_Dn() {
		return third_Dn;
	}

	public void setThird_Dn(String third_Dn) {
		this.third_Dn = third_Dn;
	}

	public String getUser_Type() {
		return user_Type;
	}

	public void setUser_Type(String user_Type) {
		this.user_Type = user_Type;
	}

	public String getOper_Type() {
		return oper_Type;
	}

	public void setOper_Type(String oper_Type) {
		this.oper_Type = oper_Type;
	}

	public String getSale_Mode() {
		return sale_Mode;
	}

	public void setSale_Mode(String sale_Mode) {
		this.sale_Mode = sale_Mode;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getChrg_Type() {
		return chrg_Type;
	}

	public void setChrg_Type(String chrg_Type) {
		this.chrg_Type = chrg_Type;
	}

	public String getSp_Code() {
		return sp_Code;
	}

	public void setSp_Code(String sp_Code) {
		this.sp_Code = sp_Code;
	}

	public String getOper_Code() {
		return oper_Code;
	}

	public void setOper_Code(String oper_Code) {
		this.oper_Code = oper_Code;
	}

	public int getInfo_Fee() {
		return info_Fee;
	}

	public void setInfo_Fee(int info_Fee) {
		this.info_Fee = info_Fee;
	}

	public int getDiscount_Fee() {
		return discount_Fee;
	}

	public void setDiscount_Fee(int discount_Fee) {
		this.discount_Fee = discount_Fee;
	}

	public String getChrg_Prov() {
		return chrg_Prov;
	}

	public void setChrg_Prov(String chrg_Prov) {
		this.chrg_Prov = chrg_Prov;
	}

	public String getAccess_Type() {
		return access_Type;
	}

	public void setAccess_Type(String access_Type) {
		this.access_Type = access_Type;
	}

	public String getApply_Time() {
		return apply_Time;
	}

	public void setApply_Time(String apply_Time) {
		this.apply_Time = apply_Time;
	}

	public String getFinish_Time() {
		return finish_Time;
	}

	public void setFinish_Time(String finish_Time) {
		this.finish_Time = finish_Time;
	}

	public String getChannel_Id() {
		return channel_Id;
	}

	public void setChannel_Id(String channel_Id) {
		this.channel_Id = channel_Id;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getCreate_Time() {
		return create_Time;
	}

	public void setCreate_Time(String create_Time) {
		this.create_Time = create_Time;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
}