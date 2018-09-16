package com.cmcc.andedu.hdsyn.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDbQueryEntity;

@Deprecated
@Repository
public interface EduSynHdDbQueryInter {

	@Select
    ("select * "
    		+ "from EDU_SYN_HD_DB"
    		+ "where CHRG_PROV=#{chrgProv} and BATCHNUM=#{batchnum}")
	public List<EduSynHdDbQueryEntity> select(String chrgProv,String batchnum);
    
}