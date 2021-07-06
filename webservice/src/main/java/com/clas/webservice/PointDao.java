package com.clas.webservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointDao extends JpaRepository<PointBean, Integer> {//<Bean, Typage Id>
}
