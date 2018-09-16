package com.cmcc.andedu.hdsyn.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDedQueryEntity;

@Deprecated
@Repository
public interface EduSynHdDedQueryInter{

	@Select
    ("select * "
    		+ "from EDU_SYN_HD_DED"
    		+ "where PROVINCE=#{province} and BATCHNUM=#{batchnum}")
	public List<EduSynHdDedQueryEntity> select(String province,String batchnum);
    
}
