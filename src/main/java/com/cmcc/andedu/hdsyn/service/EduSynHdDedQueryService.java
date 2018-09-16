package com.cmcc.andedu.hdsyn.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDedQueryEntity;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDedQueryInter;

@Deprecated
@Service
public class EduSynHdDedQueryService {

    @Autowired
    EduSynHdDedQueryInter eduSynHdDedQueryInter;

    public List<EduSynHdDedQueryEntity> findAllOrder(String province, String batchnum) {
        List<EduSynHdDedQueryEntity> eduSynHdDebQueryData = eduSynHdDedQueryInter.select(province, batchnum);
        return eduSynHdDebQueryData;
    }
}
