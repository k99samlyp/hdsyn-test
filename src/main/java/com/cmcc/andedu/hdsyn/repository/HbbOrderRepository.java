package com.cmcc.andedu.hdsyn.repository;

import com.cmcc.andedu.hdsyn.domain.HbbOrderGdEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiYangpan on 2018/7/13  3:46 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
@Repository
public interface HbbOrderRepository extends CrudRepository<HbbOrderGdEntity,Long> {


    @Query("select a from HbbOrderGdEntity a")
    List<HbbOrderGdEntity> findAllOrder();
}
