/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import sm.AMFC.atributos.Atributos;


/**
 * Clase que representa una Elipse y que hereda de la clase Figura. Tiene los métodos
 * necesarios para construir la Elipse, dibujarla, modificar sus parámetros y obtener información
 * sobre sus propios parámetros.
 * @author Antonio Manuel Fernández Cantos.
 */
public class Elipse2D extends Figura{
    
    /**
     * Punto de inicio de la elipse. 
     */
    Point2D p1;


    
    /**
     * Constructor que inicializa la elipse con la esquina superior izquierda
     * representada por p1 y la esquina inferior derecha por p2.
     * @param p coordenada de la esquina superior izquierda.
     * @param ancho indica el ancho de la elipse.
     * @param alto indica el alto de la elipse.
     */
    public Elipse2D(Point2D p, int ancho, int alto){
        this.geometria = new Ellipse2D.Double(p.getX(), p.getY(), ancho, alto);
        this.p1=p;
        atributos = new Atributos();
        tipo = Figura.ELIPSE;
    }
    
    /**
     * Constructor que inicializa la elipse con la esquina superior izquierda
     * representada por p1 y la esquina inferior derecha por p2.
     * @param p1 coordenada de la esquina superior izquierda.
     * @param p2 coordenada de la esquina inferior derecha.
     * @param atributos corresponde a los atributos actuales de la elipse.
     */
    public Elipse2D(Point2D p1, Point2D p2, Atributos atributos){
        ((Ellipse2D.Double)geometria).setFrameFromDiagonal(p1, p2);
        this.p1=p1;
        this.atributos = atributos;
        tipo = Figura.ELIPSE;
    }
    /**
     * Método que pinta en un Graphics2D la elipse con sus atributos.
     * @param g2d 
     */
    @Override
    public void pintar(Graphics2D g2d){
        g2d.setComposite(atributos.getComposite());
        g2d.setRenderingHints(atributos.getRenderingHints());
        if(atributos.getRellenoDegradado())
            g2d.setPaint(atributos.getDegradado());
        else
            g2d.setPaint(atributos.getColor());
        g2d.setStroke(atributos.getStroke());
        
        if(atributos.getRelleno())
            g2d.fill(geometria);
        else
            g2d.draw(geometria);
    } 

    /**
     * Método que asigna el segundo punto de la elipse.
     * @param p2 coordenada del segundo punto de la elipse-
     */
    @Override
    public void setP2(Point2D p2){
        //Point2D p = new Point2D.Double(((Ellipse2D.Double)geometria).x,((Ellipse2D.Double)geometria).y);
        ((Ellipse2D.Double)geometria).setFrameFromDiagonal(this.p1.getX(), this.p1.getY(),p2.getX(), p2.getY());
        //super.setFrameFromDiagonal(p_inicial,p2);
    }
    
    /**
     * Método que actualiza la posición del de la elipse en el lienzo.
     * @param p coordenada nueva donde se ubica la elipse.
     */
    @Override
    public void setLocation(Point2D p){
        /*this.p1=p;
        ((Ellipse2D.Double)geometria).x=p.getX();
        ((Ellipse2D.Double)geometria).y=p.getY();*/
        
        this.p1 = p;
        ((Ellipse2D.Double)geometria).setFrame(p.getX(), p.getY(), 
                ((Ellipse2D.Double)geometria).width, ((Ellipse2D.Double)geometria).height);
    }
    
    /**
     * 
     * @return Devuelve el punto de inicio de la elipse (esquina superior izquierda). 
     */
    @Override
    public Point2D getLocation(){
        return this.p1;
    }

    /**
     * Método que devuelve si la elipse contiene un punto.
     * @param p coordenada para comprobar si contiene la elipse.
     * @return true si el punto es contenido por la elipse, false en caso contrario.
     */
    @Override
    public boolean contains(Point2D p) {
        return ((Ellipse2D.Double)geometria).contains(p);
    }
    
}
