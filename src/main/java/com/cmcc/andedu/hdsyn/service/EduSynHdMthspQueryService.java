package com.cmcc.andedu.hdsyn.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.andedu.hdsyn.domain.EduSynHdMthspQueryEntity;
import com.cmcc.andedu.hdsyn.repository.EduSynHdMthspQueryInter;

@Deprecated
@Service
public class EduSynHdMthspQueryService {

    @Autowired
    EduSynHdMthspQueryInter eduSynHdMthspQueryInter;

    public List<EduSynHdMthspQueryEntity> findAllOrder(String province, String batchnum) {
        List<EduSynHdMthspQueryEntity> eduSynHdMthspQueryData = eduSynHdMthspQueryInter.select(province, batchnum);
        return eduSynHdMthspQueryData;
    }
}
