package com.cmcc.andedu.hdsyn.service;


import java.util.List;

import com.cmcc.andedu.hdsyn.repository.EduSynHdDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.andedu.hdsyn.domain.EduSynHdDbQueryEntity;
import com.cmcc.andedu.hdsyn.repository.EduSynHdDbQueryInter;

@Deprecated
@Service
public class EduSynHdDbQueryService {

    //@Autowired
    ///EduSynHdDbQueryInter eduSynHdDbQueryInter;

    @Autowired
    EduSynHdDbMapper eduSynHdDbMapper;

    public List<EduSynHdDbQueryEntity> findAllOrder(String chrgProv, String batchnum) {
        List<EduSynHdDbQueryEntity> eduSynHdDbQueryData = eduSynHdDbMapper.findAllItems(chrgProv, batchnum);
        return eduSynHdDbQueryData;
    }
}
