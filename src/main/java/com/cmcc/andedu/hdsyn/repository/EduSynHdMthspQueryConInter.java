package com.cmcc.andedu.hdsyn.repository;

import java.util.List;

import com.cmcc.andedu.hdsyn.domain.EduSynHdMthspQueryEntity;

@Deprecated
public interface EduSynHdMthspQueryConInter {

	List<EduSynHdMthspQueryEntity> findAllItems(String province,String batchnum);
    
}
