package com.cmcc.andedu.hdsyn.repository;

import java.util.List;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDedQueryEntity;

@Deprecated
public interface EduSynHdDedQueryConInter{

	public List<EduSynHdDedQueryEntity> findAllItems(String province,String batchnum);
    
}
