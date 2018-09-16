package com.cmcc.andedu.hdsyn.domain;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/13 9:10
 * @Modified By:
 * @Version: 1.0
 */
public class EduSynHdDed {
    private int id;//主键ID

    private String midFlag;//中间记录标识

    private String serviceType;//业务类型

    private String cutType;//核减类型

    private String woNo;//工单流水号

    private String feeDn;//计费用户号码

    private String spId;//SP代码

    private String serviceCode;//业务代码

    private String feeType;//计费类型

    private String useTime;//业务使用时间

    private String cutTime;//核减时间

    private int cutFee;//核减金额

    private String contentId;//内容ID

    private String tFlag;//第三方标识

    private String seq;//序列号

    private String addKey1;//附加查重关键字一

    private String addKey2;//附加查重关键字二

    private String orderType;//订购方式

    private String rechargeSeq;//充值序列号

    private String province;//用户归属生代码

    private String createTime;//记录创建时间

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

    public String getCutType() {
        return cutType;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

    public String getFeeDn() {
        return feeDn;
    }

    public void setFeeDn(String feeDn) {
        this.feeDn = feeDn;
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

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getCutTime() {
        return cutTime;
    }

    public void setCutTime(String cutTime) {
        this.cutTime = cutTime;
    }

    public int getCutFee() {
        return cutFee;
    }

    public void setCutFee(int cutFee) {
        this.cutFee = cutFee;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String gettFlag() {
        return tFlag;
    }

    public void settFlag(String tFlag) {
        this.tFlag = tFlag;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getAddKey1() {
        return addKey1;
    }

    public void setAddKey1(String addKey1) {
        this.addKey1 = addKey1;
    }

    public String getAddKey2() {
        return addKey2;
    }

    public void setAddKey2(String addKey2) {
        this.addKey2 = addKey2;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRechargeSeq() {
        return rechargeSeq;
    }

    public void setRechargeSeq(String rechargeSeq) {
        this.rechargeSeq = rechargeSeq;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
