package com.clas.webservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PointDao extends JpaRepository<PointBean, Integer> {//<Bean, Typage Id>

}
