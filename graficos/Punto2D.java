/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase que reprensenta la figura punto. Tiene la funcionalidad necesaria
 * para inicializar el punto.
 * @author Antonio Manuel Fernández Cantos
 */
public class Punto2D extends Figura {

    /**
     * Punto donde se encuentra ubicado la figura punto.
     */
    Point2D p1;
    
    /**
     * Constructor que inicializa el punto
     * @param p coordenada donde se asigna el punto.
     */
    public Punto2D(Point2D p){
        this.geometria = new Line2D.Double(p, p);
        p1=p;
        tipo = Figura.PUNTO;
    }
    /**
     * Método que actualiza la posición del punto.
     * @param p coordenada nueva.
     */
    @Override
    public void setLocation(Point2D p) {
        ((Line2D.Double)geometria).setLine(p,p);
        p1=p;
    }
    
    /**
     * Indica la posición donde se encuentra la figura punto.
     * @return coordenada donde está la figura punto.
     */
    @Override 
    public Point2D getLocation(){
        return this.p1;
    }

    /**
     * Método que almacena el punto de la figura punto. Con este método nos permite
     * arrastrar la figura punto por el lienzo cuando se está dibujando.
     * @param p2 coordenada donde se encuentra el punto.
     */
    @Override
    public void setP2(Point2D p2) {
        this.p1 = p2;
        ((Line2D.Double)geometria).x1=p1.getX();
        ((Line2D.Double)geometria).y1=p1.getY();
        ((Line2D.Double)geometria).x2=p1.getX();
        ((Line2D.Double)geometria).y2=p1.getY();
        
        
    }
    /**
     * Método que pinta el punto en el gráfico.
     * @param g2d de tipo Graphics2D, donde se pinta el punto.
     */
    @Override
    public void pintar(Graphics2D g2d){
        g2d.setComposite(atributos.getComposite());
        g2d.setRenderingHints(atributos.getRenderingHints());
        g2d.setPaint(atributos.getColor());
        //this.atributos.setLineaDiscontinua(false);
        g2d.setStroke(atributos.getStroke());
        g2d.draw(geometria);
    }  
    
    /**
     * 
     * @param p coordenada "cerca" del punto.
     * @return Devuelve si la linea está cerca del punto pasado como parámetro.
     */
    private boolean isNear(Point2D p){ 
        return ((Line2D.Double)geometria).ptLineDist(p)<=2.0;
    }
    
    
    /**
     * Devuelve si el punto contiene p.
     * @param p coordenada para ver si la linea contiene el punto.
     * @return devuelve true si la linea contiene al punto.
     */
    @Override
    public boolean contains(Point2D p) {
        return isNear(p);
    }
    
}
