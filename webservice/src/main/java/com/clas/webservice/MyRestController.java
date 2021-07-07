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
    public List<PointBean> getPoints(){
        List<PointBean> pointlist = pointDao.findAll();
        return pointlist;
    }

    //envoyer la position des points
    //http://localhost:8080/sendPoint
    @PostMapping("/sendPoint")
    public ErrorBean sendPoint(@RequestBody PointBean pointBean, HttpServletResponse response) {
        try {
            if ((pointBean.getLat_point() = null || pointBean.getLon_point() != null) && ()){
                sendPoint();
            }
            throw new Exception("Coordonnées hors limites");

            //controle
            System.out.println("/sendPoint : lat > " + pointBean.getLat_point() + " & lon > " + pointBean.getLon_point());
            pointDao.save(pointBean);

            return null;
        }
        catch(Exception e) {
            e.printStackTrace();
            response.setStatus(COORDONNEES_INCORRECTES);
            return new ErrorBean("Erreur : " + e.getMessage());
        }

    }
}
