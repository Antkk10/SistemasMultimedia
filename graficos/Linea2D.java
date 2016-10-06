/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import sm.AMFC.atributos.Atributos;

/**
 * Clase que representa una linea. Contiene la funcionalidad necesaria para crear una linea,
 * modificarla y obtener información sobre dicha linea.
 * @author Antonio Manuel Fernández Cantos
 */
public class Linea2D extends Figura {
    
    /**
     * Atributos de tipo Point2D corresponden con el punto de inicio y punto final
     * de la linea.
     */
    Point2D p1, p2;
    

    

    /**
     * Constructor de la clase que crea una linea con los puntos p1 y p2. Además inicializa
     * los atributos por defecto.
     * @param p1 inicio de la linea.
     * @param p2 fin de la linea.
     */
    public Linea2D(Point2D p1, Point2D p2){
        this.p1 = p1;
        this.p2 = p2;
        this.atributos = new Atributos();
        geometria = new Line2D.Double(p1, p2);
        tipo = Figura.LINEA;
    }
    /**
     * Método que pinta la linea en g2d.
     * @param g2d de tipo Graphics2D, es donde la linea se pinta
     */
    @Override
    public void pintar(Graphics2D g2d){
        g2d.setComposite(atributos.getComposite());
        g2d.setRenderingHints(atributos.getRenderingHints());
        g2d.setPaint(atributos.getColor());
        g2d.setStroke(atributos.getStroke());
        g2d.draw(geometria);
    }    
    /**
     * Devuelve si un punto está cerca.
     */
    private boolean isNear(Point2D p){ 
        return ((Line2D.Double)geometria).ptLineDist(p)<=2.0;
    }
    
    /** Devuelve el resultado de consultar al método isNear
     * 
     * @param p coordenada para comprobar si la linea está cerca.
     * @return true si el punto está cerca de la linea
     */
    @Override
    public boolean contains(Point2D p) {
        return isNear(p);
    }
    
    
    /**
     * Método que asigna la nueva localización de la linea
     * @param pos coordenada donde se ubica la linea.
     */
    @Override
    public void setLocation(Point2D pos){
        
        double dx=pos.getX()-this.p1.getX();
        double dy=pos.getY()-this.p1.getY();
        Point2D newp2 = new Point2D.Double(this.p2.getX()+dx,this.p2.getY()+dy);
        ((Line2D.Double)geometria).setLine(pos,newp2);
        p1=pos;
        p2=newp2;
    }
   
    /**
     * 
     * @return Devuelve el punto de inicio (punto1) de la linea.
     */
    @Override
    public Point2D getLocation(){
        return this.p1;
    }
    
    /**
     * Método que almacena el fin de la linea.
     * @param pos coordenada que indica el fin de la linea.
     */
    public void setP2(Point2D pos){
        this.p2=pos;
        ((Line2D.Double)geometria).x2 = pos.getX();
        ((Line2D.Double)geometria).y2 = pos.getY();
    }
    
}
