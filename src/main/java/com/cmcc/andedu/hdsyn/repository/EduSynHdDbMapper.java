package com.cmcc.andedu.hdsyn.repository;


import com.cmcc.andedu.hdsyn.domain.EduSynHdDb;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDbQueryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/13 11:29
 * @Modified By:
 * @Version: 1.0
 */
@Repository
public interface EduSynHdDbMapper {

    @Insert("insert into EDU_SYN_HD_DB (REC_TYPE,MSG_ID,CHRG_DN,THIRD_DN,USER_TYPE,OPER_TYPE,SALE_MODE,DISCOUNT," +
            "CHRG_TYPE,SP_CODE,OPER_CODE,INFO_FEE,DISCOUNT_FEE,CHRG_PROV,ACCESS_TYPE,APPLY_TIME,FINISH_TIME,CHANNEL_ID,IMSI," +
            "IMEI,CREATE_TIME,BATCHNUM,ID) " +
            "values(#{recType},#{msgId},#{chrgDn},#{thirdDn},#{userType},#{operType},#{saleMode},#{discount}," +
            "#{chrgType},#{spCode},#{operCode},#{infoFee},#{discountFee},#{chrgProv},#{accessType},#{applyTime},#{finishTime}," +
            "#{channelId},#{imsi},#{imei},to_date(#{createTime}, 'yyyy-mm-dd hh24-mi-ss'),#{batchNum},EDU_SEQ_SYN_HD_DB.NEXTVAL)")
    int insert(EduSynHdDb eduSynHdDb);

    @Select("select * from EDU_SYN_HD_DB where rec_type=#{recType}")
    EduSynHdDb select(String recType);

    @Select("select * from EDU_SYN_HD_DB where CHRG_PROV=#{arg0} and BATCHNUM=#{arg1}")
    List<EduSynHdDbQueryEntity> findAllItems(String chrgProv, String batchnum) ;


    @Insert(
            {"<script>",
                    "insert into EDU_SYN_HD_DB ",
                    "(REC_TYPE,MSG_ID,CHRG_DN,THIRD_DN,USER_TYPE,OPER_TYPE,SALE_MODE,DISCOUNT,",
                    "CHRG_TYPE,SP_CODE,OPER_CODE,INFO_FEE,DISCOUNT_FEE,CHRG_PROV,ACCESS_TYPE,APPLY_TIME,FINISH_TIME,CHANNEL_ID,IMSI,",
                    "IMEI,CREATE_TIME,BATCHNUM,ID) ",
                    "SELECT A.* ,EDU_SEQ_SYN_HD_DB.NEXTVAL FROM (",
                    "<foreach collection ='list' item='hddb' separator ='union all'>",
            "(SELECT #{hddb.recType},#{hddb.msgId},#{hddb.chrgDn},#{hddb.thirdDn},#{hddb.userType},#{hddb.operType},#{hddb.saleMode},#{hddb.discount},",
                    "#{hddb.chrgType},#{hddb.spCode},#{hddb.operCode},#{hddb.infoFee},#{hddb.discountFee},#{hddb.chrgProv},#{hddb.accessType},#{hddb.applyTime},#{hddb.finishTime},",
                    "#{hddb.channelId},#{hddb.imsi},#{hddb.imei},to_date(#{hddb.createTime}, 'yyyy-mm-dd hh24-mi-ss'),#{hddb.batchNum} FROM DUAL)",
            "</foreach> ) A",
            "</script>"
            }
    )
    int batchInsert(List<EduSynHdDb> eduSynHdDbList);


    @Insert(
            {"<script>",
                    "insert into EDU_SYN_HD_DB ",
                    "(REC_TYPE,MSG_ID,CHRG_DN,THIRD_DN,USER_TYPE,OPER_TYPE,SALE_MODE,DISCOUNT,",
                    "CHRG_TYPE,SP_CODE,OPER_CODE,INFO_FEE,DISCOUNT_FEE,CHRG_PROV,ACCESS_TYPE,APPLY_TIME,FINISH_TIME,CHANNEL_ID,IMSI,",
                    "IMEI,CREATE_TIME,BATCHNUM) ",
                    "VALUES",
                    "<foreach collection ='list' item='hddb' separator =','>",
                    "(#{hddb.recType},#{hddb.msgId},#{hddb.chrgDn},#{hddb.thirdDn},#{hddb.userType},#{hddb.operType},#{hddb.saleMode},#{hddb.discount},",
                    "#{hddb.chrgType},#{hddb.spCode},#{hddb.operCode},#{hddb.infoFee},#{hddb.discountFee},#{hddb.chrgProv},#{hddb.accessType},#{hddb.applyTime},#{hddb.finishTime},",
                    "#{hddb.channelId},#{hddb.imsi},#{hddb.imei},#{hddb.createTime},#{hddb.batchNum})",
                    "</foreach> ",
                    "</script>"
            }
    )
    int batchInsert_mysql(List<EduSynHdDb> eduSynHdDbList);


    @Select("select distinct (CHRG_PROV) from  (select CHRG_PROV from EDU_SYN_HD_DB where BATCHNUM = #{arg0})")
    List<String> getProvicesByBatchnum(String batchnum);

    @Select("select distinct (BATCHNUM) from  EDU_SYN_HD_DB order by BATCHNUM asc")
    List<String> getAllBatchnum();


}
