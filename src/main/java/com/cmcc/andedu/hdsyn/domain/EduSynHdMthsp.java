package com.cmcc.andedu.hdsyn.domain;

import org.springframework.stereotype.Component;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/12 14:40
 * @Modified By:
 * @Version: 1.0
 */
@Component
public class EduSynHdMthsp {

    private int id;//主键ID

    private String midFlag;//中间记录标记

    private String serviceType;//业务类型

    private String feedN;//计费用户号码

    private String tdn;//第三方号码

    private String spId;//SP代码

    private String serviceCode;//业务代码

    private String feeType;//计费类别

    private String memProperty;//会员属性

    private String channel;//订购渠道

    private String feeMonth;//结算月份

    private String orderTime;//最后订购时间

    private int fee;//标准信息费

    private int preferentialFee;//优惠后信息费

    private String hdTime;//话单生成日期

    private String province;//用户归属生代码

    private String hdSeq;//话单序列号

    private String createDate;//记录创建日期

    private String batchNum;//批次号


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMidFlag() {
        return midFlag;
    }

    public void setMidFlag(String midFlag) {
        this.midFlag = midFlag;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getFeedN() {
        return feedN;
    }

    public void setFeedN(String feedN) {
        this.feedN = feedN;
    }

    public String getTdn() {
        return tdn;
    }

    public void setTdn(String tdn) {
        this.tdn = tdn;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getMemProperty() {
        return memProperty;
    }

    public void setMemProperty(String memProperty) {
        this.memProperty = memProperty;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFeeMonth() {
        return feeMonth;
    }

    public void setFeeMonth(String feeMonth) {
        this.feeMonth = feeMonth;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getPreferentialFee() {
        return preferentialFee;
    }

    public void setPreferentialFee(int preferentialFee) {
        this.preferentialFee = preferentialFee;
    }

    public String getHdTime() {
        return hdTime;
    }

    public void setHdTime(String hdTime) {
        this.hdTime = hdTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHdSeq() {
        return hdSeq;
    }

    public void setHdSeq(String hdSeq) {
        this.hdSeq = hdSeq;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
}
