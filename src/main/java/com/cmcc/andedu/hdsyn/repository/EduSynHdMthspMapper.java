package com.cmcc.andedu.hdsyn.repository;


import com.cmcc.andedu.hdsyn.domain.EduSynHdMthsp;
import com.cmcc.andedu.hdsyn.domain.EduSynHdMthspQueryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: miaojiaxing
 * @Description:
 * @Date: Created in 2018/7/12 15:35
 * @Modified By:
 * @Version: 1.0
 */
@Repository
public interface EduSynHdMthspMapper {

    @Insert("insert into EDU_SYN_HD_MTHSP (ID,MIDFLAG,SERVICETYPE,FEEDN,TDN,SPID,SERVICECODE,FEETYPE,MEMPROPERTY," +
            "CHANNEL,FEEMONTH,ORDERTIME,FEE,PREFERENTIALFEE,HDTIME,PROVINCE,HDSEQ,CREATE_DATE,BATCHNUM) " +
            "values(EDU_SEQ_SYN_HD_MTHSP.NEXTVAL,#{midFlag},#{serviceType},#{feedN},#{tdn},#{spId},#{serviceCode},#{feeType},#{memProperty}," +
            "#{channel},#{feeMonth},#{orderTime},#{fee},#{preferentialFee},#{hdTime},#{province},#{hdSeq}," +
            "to_date(#{createDate}, 'yyyy-mm-dd hh24-mi-ss' ),#{batchNum})")
    int insert(EduSynHdMthsp eduSynHdMthsp);

    @Select("select * from EDU_SYN_HD_MTHSP where id=#{id}")
    EduSynHdMthsp select(int id);

    @Select("select * from EDU_SYN_HD_MTHSP where PROVINCE=#{arg0} and BATCHNUM=#{arg1}")
    List<EduSynHdMthspQueryEntity> findAllItems(String province, String batchnum) ;


    @Insert(
            {"<script>",
                    "insert into EDU_SYN_HD_MTHSP ",
                    "(ID,MIDFLAG,SERVICETYPE,FEEDN,TDN,SPID,SERVICECODE,FEETYPE,MEMPROPERTY,",
                    "CHANNEL,FEEMONTH,ORDERTIME,FEE,PREFERENTIALFEE,HDTIME,PROVINCE,HDSEQ,CREATE_DATE,BATCHNUM) ",
                    "SELECT EDU_SEQ_SYN_HD_MTHSP.NEXTVAL, A.* FROM (",
                    "<foreach collection ='list' item='hdmthsp' separator ='union all'>",
                    "(SELECT #{hdmthsp.midFlag},#{hdmthsp.serviceType},#{hdmthsp.feedN},#{hdmthsp.tdn},#{hdmthsp.spId},#{hdmthsp.serviceCode},#{hdmthsp.feeType},#{hdmthsp.memProperty},",
                    "#{hdmthsp.channel},#{hdmthsp.feeMonth},#{hdmthsp.orderTime},#{hdmthsp.fee},#{hdmthsp.preferentialFee},#{hdmthsp.hdTime},#{hdmthsp.province},#{hdmthsp.hdSeq},to_date(#{hdmthsp.createDate}, 'yyyy-mm-dd hh24-mi-ss'),#{hdmthsp.batchNum} FROM DUAL)",
                    "</foreach> ) A",
                    "</script>"
            }
    )
    int batchInsert(List<EduSynHdMthsp> eduSynHdMthspList);


    @Insert(
            {"<script>",
                    "insert into EDU_SYN_HD_MTHSP ",
                    "(MIDFLAG,SERVICETYPE,FEEDN,TDN,SPID,SERVICECODE,FEETYPE,MEMPROPERTY,",
                    "CHANNEL,FEEMONTH,ORDERTIME,FEE,PREFERENTIALFEE,HDTIME,PROVINCE,HDSEQ,CREATE_DATE,BATCHNUM) ",
                    "VALUES ",
                    "<foreach collection ='list' item='hdmthsp' separator =','>",
                    "(#{hdmthsp.midFlag},#{hdmthsp.serviceType},#{hdmthsp.feedN},#{hdmthsp.tdn},#{hdmthsp.spId},#{hdmthsp.serviceCode},#{hdmthsp.feeType},#{hdmthsp.memProperty},",
                    "#{hdmthsp.channel},#{hdmthsp.feeMonth},#{hdmthsp.orderTime},#{hdmthsp.fee},#{hdmthsp.preferentialFee},#{hdmthsp.hdTime},#{hdmthsp.province},#{hdmthsp.hdSeq},#{hdmthsp.createDate},#{hdmthsp.batchNum})",
                    "</foreach>",
                    "</script>"
            }
    )
    int batchInsert_mysql(List<EduSynHdMthsp> eduSynHdMthspList);

    @Select("select distinct (PROVINCE) from  (select PROVINCE from EDU_SYN_HD_MTHSP where BATCHNUM = #{arg0})")
    List<String> getProvicesByBatchnum(String batchnum);

    @Select("select distinct (BATCHNUM) from  EDU_SYN_HD_MTHSP order by BATCHNUM asc ")
    List<String> getAllBatchnum();
}
