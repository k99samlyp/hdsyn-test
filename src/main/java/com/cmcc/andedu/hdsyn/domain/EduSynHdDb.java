package com.cmcc.andedu.hdsyn.domain;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/13 9:20
 * @Modified By:
 * @Version: 1.0
 */
public class EduSynHdDb {

    private String recType;//中间记录标记

    private String msgId;//消息序列号

    private String chrgDn;//计费用户号码

    private String thirdDn;//第三方号码

    private String userType;//计费号码用户类型

    private String operType;//业务类型

    private String saleMode;//优惠模式

    private String discount;//折扣率

    private String chrgType;//计费类型

    private String spCode;//SP代码

    private String operCode;//业务代码

    private int infoFee;//信息费

    private int discountFee;//优惠后信息费

    private String chrgProv;//计费用户号码归属省

    private String accessType;//定制方式

    private String applyTime;//用户申请时间

    private String finishTime;//话单记录生成时间

    private String channelId;//渠道代码

    private String imsi;//IMSI

    private String imei;//手机终端设备标识

    private String createTime;//记录创建时间

    private String batchNum;//批次号


    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getChrgDn() {
        return chrgDn;
    }

    public void setChrgDn(String chrgDn) {
        this.chrgDn = chrgDn;
    }

    public String getThirdDn() {
        return thirdDn;
    }

    public void setThirdDn(String thirdDn) {
        this.thirdDn = thirdDn;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getSaleMode() {
        return saleMode;
    }

    public void setSaleMode(String saleMode) {
        this.saleMode = saleMode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getChrgType() {
        return chrgType;
    }

    public void setChrgType(String chrgType) {
        this.chrgType = chrgType;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    public int getInfoFee() {
        return infoFee;
    }

    public void setInfoFee(int infoFee) {
        this.infoFee = infoFee;
    }

    public int getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(int discountFee) {
        this.discountFee = discountFee;
    }

    public String getChrgProv() {
        return chrgProv;
    }

    public void setChrgProv(String chrgProv) {
        this.chrgProv = chrgProv;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
}
