package com.clas.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MyRestController {
    private int COORDONNEES_INCORRECTES = 518;
    private int PT_INCORRECTS = 500;

    @Autowired
    private PointDao pointDao;

    //http://localhost:8889/testPoint
    @GetMapping("/testPoint")
    public String testPoint(){
        System.out.println("/testPoint");
        return "Hello World!";
    }

    //récupérer la position des points
    //http://localhost:8889/getPoints
    @PostMapping("/getPoints")
    public ErrorBean getPoints(HttpServletResponse response){
        System.out.println("/getPoints ");
        try{
            List<PointBean> pointlist = pointDao.findAll();
            return null;
        }
        catch(Exception e) {
            e.printStackTrace();
            response.setStatus(PT_INCORRECTS);
            return new ErrorBean("Erreur : " + e.getMessage());
        }
    }

    //envoyer la position des points
    //http://localhost:8080/sendPoint
    @PostMapping("/sendPoint")
    public ErrorBean sendPoint(@RequestBody PointBean point, HttpServletResponse response) {
        System.out.println("/sendPoint : lat = " + point.getLat_point() + " & lon = " + point.getLon_point());
        try {
            //controle
            if (point.getLon_point() == null || point.getLat_point() == null){
            throw new Exception("Coordonnées nulles");

            }   else if (point.getLon_point() < -90 || point.getLon_point() > 90 ||  point.getLat_point() < -90 || point.getLat_point() > 90) {
                throw new Exception("Coordonnées hors limites");
            }


            pointDao.save(point);
            return null;
        }

        catch(Exception e) {
            e.printStackTrace();
            response.setStatus(COORDONNEES_INCORRECTES);
            return new ErrorBean("Erreur : " + e.getMessage());
        }

    }

}
