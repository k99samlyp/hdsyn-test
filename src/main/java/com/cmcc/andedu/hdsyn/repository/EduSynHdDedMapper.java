package com.cmcc.andedu.hdsyn.repository;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDed;
import com.cmcc.andedu.hdsyn.domain.EduSynHdDedQueryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/13 11:36
 * @Modified By:
 * @Version: 1.0
 */
@Repository
public interface EduSynHdDedMapper {

    @Insert("insert into EDU_SYN_HD_DED (ID,MIDFLAG,SERVICETYPE,CUTTYPE,WONO,FEEDN,SPID,SERVICECODE,FEETYPE," +
            "USETIME,CUTTIME,CUTFEE,CONTENTID,TFLAG,SEQ,ADDKEY1,ADDKEY2,ORDERTYPE,RECHARGESEQ,PROVINCE,CREATE_TIME,BATCHNUM) " +
            "values(EDU_SEQ_SYN_HD_DED.NEXTVAL,#{midFlag},#{serviceType},#{cutType},#{woNo},#{feeDn},#{spId},#{serviceCode},#{feeType}," +
            "#{useTime},#{cutTime},#{cutFee},#{contentId},#{tFlag},#{seq},#{addKey1},#{addKey2},#{orderType},#{rechargeSeq}," +
            "#{province},to_date(#{createTime}, 'yyyy-mm-dd hh24-mi-ss'),#{batchNum})")
    int insert(EduSynHdDed duSynHdDed);

    @Select("select * from EDU_SYN_HD_DED where id=#{id}")
    EduSynHdDed select(int id);

    @Select("select * from EDU_SYN_HD_DED where PROVINCE=#{arg0} and BATCHNUM=#{arg1}")
    List<EduSynHdDedQueryEntity> findAllItems(String province, String batchnum) ;


    @Insert(
            {
                    "<script>",
                    "insert into EDU_SYN_HD_DED ",
                    "(ID,MIDFLAG,SERVICETYPE,CUTTYPE,WONO,FEEDN,SPID,SERVICECODE,FEETYPE,",
                    "USETIME,CUTTIME,CUTFEE,CONTENTID,TFLAG,SEQ,ADDKEY1,ADDKEY2,ORDERTYPE,RECHARGESEQ,PROVINCE,CREATE_TIME,BATCHNUM) ",
                    "SELECT EDU_SEQ_SYN_HD_DED.NEXTVAL, A.* FROM (",
                    "<foreach collection ='list' item='hdded' separator ='union all'>",
                    "(SELECT #{hdded.midFlag},#{hdded.serviceType},#{hdded.cutType},#{hdded.woNo},#{hdded.feeDn},#{hdded.spId},#{hdded.serviceCode},#{hdded.feeType},#{hdded.useTime},#{hdded.cutTime},#{hdded.cutFee},#{hdded.contentId},",
                    "#{hdded.tFlag},#{hdded.seq},#{hdded.addKey1},#{hdded.addKey2},#{hdded.orderType},#{hdded.rechargeSeq},#{hdded.province},to_date(#{hdded.createTime},'yyyy-mm-dd hh24-mi-ss'),#{hdded.batchNum} FROM DUAL)",
                    "</foreach> ) A",
                    "</script>"
            }
    )
    int batchInsert(List<EduSynHdDed> eduSynHdDedList);


    @Insert(
            {
                    "<script>",
                    "insert into EDU_SYN_HD_DED ",
                    "(MIDFLAG,SERVICETYPE,CUTTYPE,WONO,FEEDN,SPID,SERVICECODE,FEETYPE,",
                    "USETIME,CUTTIME,CUTFEE,CONTENTID,TFLAG,SEQ,ADDKEY1,ADDKEY2,ORDERTYPE,RECHARGESEQ,PROVINCE,CREATE_TIME,BATCHNUM) ",
                    "VALUES ",
                    "<foreach collection ='list' item='hdded' separator =','>",
                    "(#{hdded.midFlag},#{hdded.serviceType},#{hdded.cutType},#{hdded.woNo},#{hdded.feeDn},#{hdded.spId},#{hdded.serviceCode},#{hdded.feeType},#{hdded.useTime},#{hdded.cutTime},#{hdded.cutFee},#{hdded.contentId},",
                    "#{hdded.tFlag},#{hdded.seq},#{hdded.addKey1},#{hdded.addKey2},#{hdded.orderType},#{hdded.rechargeSeq},#{hdded.province},#{hdded.createTime},#{hdded.batchNum})",
                    "</foreach>",
                    "</script>"
            }
    )
    int batchInsert_mysql(List<EduSynHdDed> eduSynHdDedList);



    @Select("select distinct (PROVINCE) from  (select PROVINCE from EDU_SYN_HD_DED where BATCHNUM = #{arg0})")
    List<String> getProvicesByBatchnum(String batchnum);

    @Select("select distinct (BATCHNUM) from  EDU_SYN_HD_DED order by BATCHNUM asc ")
    List<String> getAllBatchnum();
}
