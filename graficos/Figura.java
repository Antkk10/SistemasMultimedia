/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.graficos;

import com.sun.javafx.geom.PathIterator;
import com.sun.javafx.geom.RectBounds;
import java.awt.Shape;
import com.sun.javafx.geom.transform.BaseTransform;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import sm.AMFC.atributos.Atributos;



/**
 * Clase abstracta que contiene la funcionalidad general de las figuras. Además implementa
 * las funciones de la interfaz Shape.
 * @author Antonio Manuel Fernández Cantos.
 */
public abstract class Figura implements Shape {

    /**
     * Atributo de tipo Atributos, contiene el estado de la figura creada.
     */
    protected Atributos atributos;
    /**
     * Atributo que almacena la figura creada.
     */
    protected Shape geometria;
    /**
     * Atributo que almacena el tipo de figura creada.
     */
    protected int tipo;
    
    public static final int PUNTO = 1;
    public static final int LINEA = 2;
    public static final int RECTANGULO = 3;
    public static final int ELIPSE = 4;
    public static final int POLIGONO = 5;
    public static final int CURVA = 6;
    
    /**
     * 
     * @return Devuelve el tipo de figura creada. 
     */
    public int getTipo(){
        return tipo;
    }
    /**
     * Método que actualiza la posición de la figura en el lienzo.
     * @param p coordenada nueva donde se ubica la figura.
     */
    public abstract void setLocation(Point2D p);
    
    /**
     * 
     * @return Devuelve el punto de inicio de una figura. 
     */
    public abstract Point2D getLocation();
    
    /**
     * Método para dibujar en el lienzo la figura.
     * @param g de tipo Graphics2D es donde se pinta la figura.
     */
    abstract public void pintar(Graphics2D g);
    
    /**
     * Actualiza el atributo privado de la clase.
     * @param a el valor de atributo nuevo.
     */
    public void setAtributos(Atributos a){
        this.atributos=a;
    }
    
    /**
     * 
     * @return Devuelve el valor atributo actual. 
     */
    public Atributos getAtributos(){
        return this.atributos;
    }
    /**
     * Returns an integer {@link Rectangle} that completely encloses the
     * <code>Shape</code>.  Note that there is no guarantee that the
     * returned <code>Rectangle</code> is the smallest bounding box that
     * encloses the <code>Shape</code>, only that the <code>Shape</code>
     * lies entirely within the indicated  <code>Rectangle</code>.  The
     * returned <code>Rectangle</code> might also fail to completely
     * enclose the <code>Shape</code> if the <code>Shape</code> overflows
     * the limited range of the integer data type.  The
     * <code>getBounds2D</code> method generally returns a
     * tighter bounding box due to its greater flexibility in
     * representation.
     *
     * <p>
     * Note that the <a href="{@docRoot}/java/awt/Shape.html#def_insideness">
     * definition of insideness</a> can lead to situations where points
     * on the defining outline of the {@code shape} may not be considered
     * contained in the returned {@code bounds} object, but only in cases
     * where those points are also not considered contained in the original
     * {@code shape}.
     * </p>
     * <p>
     * If a {@code point} is inside the {@code shape} according to the
     * {@link #contains(double x, double y) contains(point)} method, then
     * it must be inside the returned {@code Rectangle} bounds object
     * according to the {@link #contains(double x, double y) contains(point)}
     * method of the {@code bounds}. Specifically:
     * </p>
     * <p>
     *  {@code shape.contains(x,y)} requires {@code bounds.contains(x,y)}
     * </p>
     * <p>
     * If a {@code point} is not inside the {@code shape}, then it might
     * still be contained in the {@code bounds} object:
     * </p>
     * <p>
     *  {@code bounds.contains(x,y)} does not imply {@code shape.contains(x,y)}
     * </p>
     * @return an integer <code>Rectangle</code> that completely encloses
     *                 the <code>Shape</code>.
     * @see #getBounds2D
     * @since 1.2
     */
    @Override
    public Rectangle getBounds() {
        return geometria.getBounds();
    }
   /**
     * Returns a high precision and more accurate bounding box of
     * the <code>Shape</code> than the <code>getBounds</code> method.
     * Note that there is no guarantee that the returned
     * {@link Rectangle2D} is the smallest bounding box that encloses
     * the <code>Shape</code>, only that the <code>Shape</code> lies
     * entirely within the indicated <code>Rectangle2D</code>.  The
     * bounding box returned by this method is usually tighter than that
     * returned by the <code>getBounds</code> method and never fails due
     * to overflow problems since the return value can be an instance of
     * the <code>Rectangle2D</code> that uses double precision values to
     * store the dimensions.
     *
     * <p>
     * Note that the <a href="{@docRoot}/java/awt/Shape.html#def_insideness">
     * definition of insideness</a> can lead to situations where points
     * on the defining outline of the {@code shape} may not be considered
     * contained in the returned {@code bounds} object, but only in cases
     * where those points are also not considered contained in the original
     * {@code shape}.
     * </p>
     * <p>
     * If a {@code point} is inside the {@code shape} according to the
     * {@link #contains(Point2D p) contains(point)} method, then it must
     * be inside the returned {@code Rectangle2D} bounds object according
     * to the {@link #contains(Point2D p) contains(point)} method of the
     * {@code bounds}. Specifically:
     * </p>
     * <p>
     *  {@code shape.contains(p)} requires {@code bounds.contains(p)}
     * </p>
     * <p>
     * If a {@code point} is not inside the {@code shape}, then it might
     * still be contained in the {@code bounds} object:
     * </p>
     * <p>
     *  {@code bounds.contains(p)} does not imply {@code shape.contains(p)}
     * </p>
     * @return an instance of <code>Rectangle2D</code> that is a
     *                 high-precision bounding box of the <code>Shape</code>.
     * @see #getBounds
     * @since 1.2
     */
    @Override
    public Rectangle2D getBounds2D() {
        return geometria.getBounds2D();
    }

    /**
     * 
     * @param x columna
     * @param y fila
     * @return Devuelve true si la figura contiene las coordenadas x,y
     */
    @Override
    public boolean contains(double x, double y) {
        return geometria.contains(x, y);
    }

    //@Override
    //public boolean contains(Point2D p)

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return geometria.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return geometria.intersects(r);
    }

    /**
     * 
     * @param x coordenada x
     * @param y coordenada y
     * @param w ancho
     * @param h alto
     * @return Devuelve true si la figura contiene los parámetros. False en caso contrario.
     */
    @Override
    public boolean contains(double x, double y, double w, double h) {
        return geometria.contains(x, y, w, h);
    }

    /**
     * 
     * @param r rectangulo
     * @return Devuelve true si la figura contiene el rectangulo r. False en caso
     * contrario.
     */
    @Override
    public boolean contains(Rectangle2D r) {
        return geometria.contains(r);
    }
    
    /**
     * 
     * @param p coordenada
     * @return Devuelve true si la figura contiene el punto. False en caso contrario.
     */
    @Override
    public boolean contains(Point2D p){
        return geometria.contains(p);
    }

    @Override
    public java.awt.geom.PathIterator getPathIterator(AffineTransform at) {
        return geometria.getPathIterator(at);
    }

    @Override
    public java.awt.geom.PathIterator getPathIterator(AffineTransform at, double flatness) {
        return geometria.getPathIterator(at, flatness);
    }
    
    /**
     * Método que modifica el segundo punto de una figura.
     * @param p2 corresponde al segundo punto a modificar.
     */
    abstract public void setP2(Point2D p2);
    
    

}
