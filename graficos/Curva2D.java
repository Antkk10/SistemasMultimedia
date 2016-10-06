/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * Clase que representa una figura curva. Hereda de la clase Figura.
 * Contiene los métodos para construir una curva, dibujarla en un lienzo y moverla.
 * @author Antonio Manuel Fernández Cantos.
 */
public class Curva2D extends Figura{


    /**
     * Constructor de la clase para crear una curva en función de los puntos.
     * @param punto representa el punto inicio de la curva
     * @param punto2 representa el punto de control de la curva.
     * @param punto3 representa el punto final de la curva.
     */
    public Curva2D(Point2D punto, Point2D punto2, Point2D punto3){
        this.geometria = new QuadCurve2D.Float();
        ((QuadCurve2D.Float)this.geometria).setCurve(punto, punto2, punto3);
        this.tipo=Figura.CURVA;

    }
    
    /**
     * Método que actualiza la localización de la curva en el punto que recibe como parámetro.
     * El punto final y punto de control son calculados para que la curva siga teniendo la misma forma.
     * @param p indica el punto de ínicio de la figura.
     */
    @Override
    public void setLocation(Point2D p) {
       
       double x = p.getX() - ((QuadCurve2D)this.geometria).getX1();
       double y = p.getY() - ((QuadCurve2D)this.geometria).getY1();
       
       double x2 = ((QuadCurve2D)this.geometria).getX2();
       double y2 = ((QuadCurve2D)this.geometria).getY2();
       
       Point2D p2 = new Point2D.Double(x2 + x, y2 + y);
       
       double xcontrol =((QuadCurve2D)this.geometria).getCtrlX();
       double ycontrol = ((QuadCurve2D)this.geometria).getCtrlY();
       
       Point2D p3 = new Point2D.Double(xcontrol + x, ycontrol + y);
       
       ((QuadCurve2D)this.geometria).setCurve(p, p3, p2);
    }
    
    /**
     * Método que actualiza la localización de la curva en punto del punto pasado 
     * como parámetro.
     * @param p indica el punto de ínicio de la figura.
     * @param offset de tipo int.
     */
    public void setLocation(Point2D p, int offset){
       double x = p.getX() - ((QuadCurve2D)this.geometria).getX1();
       double y = p.getY() - ((QuadCurve2D)this.geometria).getY1();
       
       double x2 = ((QuadCurve2D)this.geometria).getX2();
       double y2 = ((QuadCurve2D)this.geometria).getY2();
       
       Point2D p2 = new Point2D.Double(x2 + x, y2 + y);
       
       double xcontrol =((QuadCurve2D)this.geometria).getCtrlX();
       double ycontrol = ((QuadCurve2D)this.geometria).getCtrlY();
       
       Point2D p3 = new Point2D.Double(xcontrol + x, ycontrol + y);
       
       ((QuadCurve2D)this.geometria).setCurve(p, p3, p2);
    }
    
    /**
     * 
     * @return Devuelve un punto con la localización del primer punto en el lienzo. 
     */
    @Override
    public Point2D getLocation(){
        return ((QuadCurve2D)this.geometria).getP1();
    }
    


    /**
     * Método que pinta en un Graphics2D la curva con sus atributos.
     * @param g2d 
     */
    @Override
    public void pintar(Graphics2D g2d) {
        g2d.setComposite(atributos.getComposite());
        g2d.setRenderingHints(atributos.getRenderingHints());
        g2d.setPaint(atributos.getColor());
        g2d.setStroke(atributos.getStroke());
        g2d.draw(geometria);
    }

    /**
     * Método que asigna al punto final de la curva.
     * @param p2 el punto donde acaba la curva.
     */
    @Override
    public void setP2(Point2D p2) {
        this.setPuntoFinal(p2);
    }
    
    /**
     * Método que asigna el inicio de la curva.
     * @param punto donde comienza  la curva.
     */
    public void setPuntoInicio(Point2D punto){
        ((QuadCurve2D)geometria).setCurve(punto, ((QuadCurve2D)geometria).getCtrlPt(), ((QuadCurve2D)geometria).getP2());
    }
    
    /**
     * Método que asigna el final de la curva.
     * @param p donde finaliza la curva.
     */
    public void setPuntoFinal(Point2D p){
        ((QuadCurve2D)geometria).setCurve(((QuadCurve2D)geometria).getP1(), ((QuadCurve2D)geometria).getCtrlPt(), p);
    }
    
    /**
     * Método que asigna el punto de control de la curva.
     * @param p donde está el punto de control de la curva.
     */
    public void setPuntoControl(Point2D p){
        ((QuadCurve2D)geometria).setCurve(((QuadCurve2D)geometria).getP1(), p, ((QuadCurve2D)geometria).getP2());
    }
    
    
}
