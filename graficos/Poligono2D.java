/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import sm.AMFC.atributos.Atributos;

/**
 * Clase que representa un polígono. Tiene la funcionalidad necesaria para inicializar,
 * modificarlo y obtener información del polígono.
 * 
 * @author Antonio Manuel Fernández Cantos.
 */
public class Poligono2D extends Figura {
    
    /**
     * Constructor por defecto. Inicializa los atributos de la clase.
     */
    public Poligono2D(){
        this.atributos = new Atributos();
        this.geometria = new Polygon();
        tipo = Figura.POLIGONO;

    }
    
    /**
     * Método que modifica la localización del polígono. El punto que recibe como parámetro
     * es el primer punto donde estará el polígono. Los siguientes puntos hay que calcularlos
     * para que el polígono siga teniendo la misma forma.
     * @param p punto de inicio del polígono.
     */
    @Override
    public void setLocation(Point2D p) {
        
        int x = (int) p.getX() - ((Polygon)geometria).xpoints[0];
        int y = (int) p.getY() - ((Polygon)geometria).ypoints[0];
        
        ((Polygon)geometria).xpoints[0] = (int) p.getX();
        ((Polygon)geometria).ypoints[0] = (int) p.getY();
        
        for(int i=1; i < ((Polygon)geometria).npoints; i++){
            ((Polygon)geometria).xpoints[i] = ((Polygon)geometria).xpoints[i] + x;
            ((Polygon)geometria).ypoints[i] = ((Polygon)geometria).ypoints[i] + y;
        }
        
        
        /*
        Esta parte sirve para que cuando se edite el polígono en el lienzo, cuando este
        ha cambiado de posición, si se quiere volver a editar, que el usuario pueda seleccionarlo.
        Sin esta parte, al ser movido el polígono, el sistema no lo detectaría en el lienzo cuando
        se quiera volver a editar.
        
        */
        
        // Creamos un nuevo polígono
        Polygon aux = new Polygon();
        
        // Asignamos los puntos.
        for(int i=0; i < ((Polygon)geometria).npoints; i++)
            aux.addPoint(((Polygon)geometria).xpoints[i], ((Polygon)geometria).ypoints[i]);
        
        geometria = aux;
        
        
    }
    
    /**
     * 
     * @return Devuelve la localización del primer punto. 
     */
    @Override
    public Point2D getLocation(){
        
        int x = ((Polygon)this.geometria).xpoints[0];
        int y = ((Polygon)this.geometria).ypoints[0];
        
        return new Point2D.Double(x, y);
        
        
    }

    /**
     * Método que pinta en un Gráfico el polígono con sus atributos.
     * @param g2d 
     */
    @Override
    public void pintar(Graphics2D g2d) {
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
     * Almacena un segundo punto en el polígono.
     * @param p indica el segundo punto.
     */
    @Override
    public void setP2(Point2D p) {
        this.setPunto(p);
    }
    
    /**
     * Método que modifica el valor del último punto.
     * @param p el punto a modificar en el último punto.
     */
    public void setPunto(Point2D p){
        int contador = ((Polygon)this.geometria).npoints;
        
        // Si todavía no existe puntos en el polígono, los añadimos
        if(contador <= 1){
            this.addPunto(p);
        }
        else{
            contador--;
        
            ((Polygon)this.geometria).xpoints[contador]=(int)p.getX();
            ((Polygon)this.geometria).ypoints[contador]=(int)p.getY();
        }
            
        
    }
    
    /**
     * Método que añade un punto al polígono.
     * @param p el punto a añadir en el polígono.
     */
    public void addPunto(Point2D p){
        ((Polygon)this.geometria).addPoint((int)p.getX(),(int) p.getY());
    }
}
