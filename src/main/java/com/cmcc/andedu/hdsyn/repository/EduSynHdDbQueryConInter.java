package com.cmcc.andedu.hdsyn.repository;

import java.util.List;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDbQueryEntity;

@Deprecated
public interface EduSynHdDbQueryConInter {
	
	public List<EduSynHdDbQueryEntity> findAllItems(String chrgProv,String batchnum) ;
	
}
