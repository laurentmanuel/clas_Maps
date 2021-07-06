package com.clas.webservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MyRestController {

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
    public ArrayList<PointBean> getPoints(){
        ArrayList<PointBean> listPoints = new ArrayList<PointBean>();
        return listPoints;
    }

    //envoyer la position des points
    //http://localhost:8080/sendPoint
    @PostMapping("/sendPoint")
    public void sendPoint(PointBean pointBean){

    }
}
