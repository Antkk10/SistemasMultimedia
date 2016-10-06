/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import sm.AMFC.atributos.Atributos;



/**
 * Clase que representa un rectangulo. Tiene la funcionalidad necesaria para
 * construir, modificar y obtener información del rectángulo.
 * 
 * 
 * @author Antonio Manuel Fernández Cantos
 */
public class Rectangulo2D extends Figura{
    
    /**
     * Esquina superior izquierda del rectángulo.
     */
    Point2D p;
    
    /**
     * Constructor de la clase que inicializa el rectángulo con los valores pasados
     * como parámetros.
     * @param p coordenada esquina superior izquierda del rectángulo.
     * @param ancho indica el ancho del rectángulo.
     * @param alto indica el alto del rectángulo.
     */
    public Rectangulo2D(Point2D p, int ancho, int alto){
        geometria = new Rectangle2D.Double(p.getX(), p.getY(), ancho, alto);
        atributos = new Atributos(); // Atributos por defecto.
        this.p=p;
        tipo = Figura.RECTANGULO;
    }

    /**
     * Constructor de la clase que inicializa el rectángulo con los valores pasados como
     * parámetros.
     * @param p1 coordenada esquina superior izquierda del rectángulo.
     * @param p2 coordenada esquina inferior derecha del rectángulo.
     */
    public Rectangulo2D(Point2D p1, Point2D p2){
        geometria = new Rectangle2D.Double();
        ((Rectangle2D.Double)geometria).setFrameFromDiagonal(p1, p2);
        atributos = new Atributos();
        this.p=p1;
        tipo = Figura.RECTANGULO;
    }

    /**
     * Método que dibuja al rectángulo con sus valores en un Graphics2D.
     * @param g2d 
     */
    @Override
    public void pintar(Graphics2D g2d){
        g2d.setComposite(atributos.getComposite());
        g2d.setRenderingHints(atributos.getRenderingHints());
        if(atributos.getRellenoDegradado()) // Si tiene relleno degradado.
            g2d.setPaint(atributos.getDegradado());
        else // Sin relleno degradado
            g2d.setPaint(atributos.getColor());
        g2d.setStroke(atributos.getStroke());
        
        if(atributos.getRelleno()) // Relleno normal
            g2d.fill(geometria);
        else
            g2d.draw(geometria);
    } 
    
    /**
     * Método que actualiza la segunda coordenada del rectángulo.
     * @param p coordenada donde finaliza el rectángulo. Normalmente es la esquina
     * inferior derecha.
     */
    public void setP2(Point2D p){
        
        
        ((Rectangle2D.Double)geometria).setFrameFromDiagonal(this.p.getX(), this.p.getY(), p.getX(), p.getY());
    }
    
    /**
     * Almacena la nueva localización del rectángulo. El rectángulo sigue teniendo el mismo ancho y alto.
     * @param p coordenada donde inicia el rectángulo.
     */
    @Override
    public void setLocation(Point2D p) {
        double ancho = ((Rectangle2D.Double)geometria).width;
        double alto = ((Rectangle2D.Double)geometria).height;
        
        ((Rectangle2D)geometria).setFrame(p.getX(), p.getY(), ancho, alto);
        this.p=p;
    }
    
    /**
     * 
     * @return Devuelve la localización de la primera coordenada del rectángulo. 
     */
    @Override
    public Point2D getLocation(){
        return this.p;
    }



}
