package com.cmcc.andedu.hdsyn.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cmcc.andedu.hdsyn.domain.EduSynHdMthspQueryEntity;

@Deprecated
@Repository
public interface EduSynHdMthspQueryInter {

	@Select
    ("select * "
    		+ "from EDU_SYN_HD_MTHSP"
    		+ "where PROVINCE=#{province} and BATCHNUM=#{batchnum}")
	public List<EduSynHdMthspQueryEntity> select(String province,String batchnum);
    
}
