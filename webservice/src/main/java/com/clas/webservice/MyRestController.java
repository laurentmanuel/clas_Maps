package com.clas.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    private PointDao pointDao;

    //http://localhost:8080/testPoint
    @GetMapping("/testPoint")
    public String testPoint(){
        System.out.println("/testPoint");
        return "Hello World !";
    }

    //récupérer la position des points
    //http://localhost:8080/getPoints
    @PostMapping("/getPoints")
    public List<PointBean> getPoints(){
        List<PointBean> pointlist = pointDao.findAll();
        return pointlist;
    }

    //envoyer la position des points
    //http://localhost:8080/sendPoint
    @PostMapping("/sendPoint")
    public void sendPoint(@RequestBody PointBean pointBean){
        //controle
        System.out.println("/sendPoint : lat > " + pointBean.getLat_point() + " & lon > " + pointBean.getLon_point());
        pointDao.save(pointBean);
    }
}
