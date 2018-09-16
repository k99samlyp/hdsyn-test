package com.cmcc.andedu.hdsyn.service;

import com.cmcc.andedu.hdsyn.domain.HbbOrderGdEntity;
import com.cmcc.andedu.hdsyn.repository.HbbOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiYangpan on 2018/7/13  4:33 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述: 查询广东和宝贝订单
 */

@Service
public class HbbCvsGeneratorService {

    @Autowired
    HbbOrderRepository hbbOrderRepository;

    public List<HbbOrderGdEntity> findAllItems() throws Exception{
        List<HbbOrderGdEntity> hddOrderData = hbbOrderRepository.findAllOrder();
        return hddOrderData;
    }

}

