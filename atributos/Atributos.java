/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.atributos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.Stroke;

/**
 * Clase que almacena el valor de los atributos de una figura. Lo que pretendemos
 * es que cada figura tenga una instancia de esta clase. Si queremos añadir algún 
 * atributo nuevo, lo harermos una vez aquí, así nos ahorramos ir escribiendo
 * clase por clase el atributo nuevo, con sus correspondientes set y get. Lo mismo
 * sucede con borrar, si tenemos los atributos en una clase, solo tendremos que borrar
 * de esta clase el atributo.
 * 
 * 
 * @author Antonio Manuel Fernández Cantos
 * @version 1.0
 */
public class Atributos implements Cloneable{
    
    /**
     * Atributo de tipo Color. Almacena un color que se le pasa
     * al respectivo método.
     */
    private Color color;
    /**
     * Atributo de tipo stroke. Almacena el grosor de una figura.
     */
    private Stroke stroke;
    /**
     * Atributo de tipo boolean. Alacena si una figura tiene relleno o no.
     */
    private boolean relleno;
    /**
     * Atributo de tipo Composite. Indica el nivel de transparencia de un
     * shape.
     */
    private Composite composite; // Nivel de transparencia
    /**
     * Atributo de tipo RenderingHints. Indica el nivel de alisado de un atributo.
     */
    private RenderingHints render; // Alisar
    
    /**
     * Atributo de tipo boolean. Indica cuando está activado el relleno de una figura.
     */
    private boolean rellDegradado;
    /**
     * Atributo de tipo GradientPaint. Da forma al relleno degradado.
     */
    private GradientPaint degradado;
    /**
     * Indica si la linea discontinua está activada.
     */
    private boolean discontinua;
    /**
     * Constructor de la clase que inicializa todos sus atributos por defecto.
     * 
     */
    public Atributos(){
        color = Color.BLACK; // Color negro
        
        stroke = new BasicStroke(1); // Por defecto grosor 1.
        discontinua = false; // No hay linea discontinua
        relleno = false; // No hay relleno
        // Con este valor no aplica nivel de transparencia a la figura.
        composite = AlphaComposite.getInstance(AlphaComposite.SRC, 1);
        // Por defecto, render desactivado
        this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_OFF);
        this.rellDegradado = false; // No hay relleno degradado
        this.degradado = null; // El objeto degradado a null
    }
    
    /**
     * Método que clona la instancia actual y la devuelve.
     * @return de tipo Atributos, devuelve una copia de los atributos actuales.
     */
    @Override
    public Atributos clone(){
        
        Atributos atributos;
        try{
            
            atributos = (Atributos) super.clone();

        }
        catch(Exception e){
            atributos = new Atributos();
        }
        
        return atributos;
    }
    
    /**
     * Método que activa/desactiva el atributo degradado.
     * @param degradado indica si hay degradado.
     * @param c1 El primer color del degradado.
     * @param c2 El segundo color del degradado.
     * @param vertical indica si el degradado es vertical, en caso de no ser vertical es
     * horizontal.
     * @param ancho indica el límite del degradado a lo ancho del lienzo.
     * @param alto indica el límite del degradado a lo alto del lienzo.
     */
    public void setRellenoDegradado(boolean degradado, Color c1, Color c2, boolean vertical, int ancho, int alto){
        
        if(degradado){
            this.rellDegradado=true;
            
            if(vertical) // Degradado vertical
                this.degradado = new GradientPaint(0, 0, c1, 0, alto, c2);
            else // Degradado horizontal
                this.degradado = new GradientPaint(0, 0, c1, ancho, 0, c2);
        }
        else{ // Sin degradado
            this.rellDegradado=false;
            this.degradado = null;
        }
    }
    
    /**
     * 
     * @return Devuelve el pbjeto degradado. 
     */
    public GradientPaint getDegradado(){
        return this.degradado;
    }
    
    /**
     * 
     * @return indica si el degradado está activo.
     */
    public boolean getRellenoDegradado(){
        return this.rellDegradado;
    }
    
    /**
     * Método que actualiza el atributo color.
     * @param c de tipo Color. Actualiza el atributo color.
     */
    public void setColor(Color c){
        this.color=c;
    }
    
    /**
     * 
     * @return Devuelve un objeto de tipo Color. El objeto devuelto corresponde
     * con el color actual de la instancia.
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * Método que almacena el grosor que se le pasa como parámetro.
     * @param grosor de tipo int, contiene el valor para darle un nuevo valor
     * al grosor.
     */
    public void setStroke(int grosor){
        if(discontinua){
            // Array que corresponde el tamaño de las lineas.
            float tam[] = {7};
            this.stroke = new BasicStroke(grosor,  BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 5.5f, tam, 0.0f);   
        
        }
        else
            this.stroke = new BasicStroke(grosor);
            
    }
    
    /**
     * Método que activa/desactiva la linea discontinua.
     * @param discontinua 
     */
    public void setLineaDiscontinua(boolean discontinua){
        // Actualizamos si la linea es discontinua o no
        this.discontinua = discontinua;
        this.setStroke(this.grosor());
    }
    
    /**
     * Método que devuelve un booleano indicando si la linea discontinua está activa.
     * @return 
     */
    public boolean getLineaDiscontinua(){
        return this.discontinua;
    }
    
    
    /**
     * Método que actualiza el grosor.
     * @param s de tipo Stroke, es el nuevo grosor.
     */
    public void setStroke(Stroke s){
        this.stroke = s;
    }
    
    /**
     * 
     * @return devuelve el grosor actual.
     */
    public Stroke getStroke(){
        return this.stroke;
    }
    
    /**
     * Método que crea el nivel de transparencia en el caso de que el parámetro booleano
     * sea true. Si se recibe false, composite se pone a null.
     * @param transparencia de tipo boolean. True para crear el objeto composite y asignarlo
     * al atributo, false para poner el atributo composite a null.
     */
    public void setComposite(boolean transparencia){
        
        if(transparencia){
            this.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        }
        else{
            this.composite = AlphaComposite.getInstance(AlphaComposite.SRC, 1);
            
        }
    }
    
    /**
     * 
     * @return Devuelve el nivel de transparencia actual. 
     */
    public Composite getComposite(){
        return this.composite;
    }
    
    /**
     * Método que crea una instancia de RenderingHints en el caso de que el parámetro
     * recibido tenga el valor de true. En caso contrarío ponemos el atributo render
     * a null.
     * @param alisar de tipo boolean, true para crear el objeto de tipo RendeginHints
     * y asignarlo al atributo render. False para poner render a null.
     * 
     */
    public void setRenderingHints(boolean alisar){
        if(alisar){ 
            this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);
        }
        else{
            this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_OFF);
        }
    }
    /**
     * Método que actualiza el el valor del objeto render (Alisar).
     * @param r objeto de tipo RenderingHints que es asignado al atributo
     * render.
     */
    public void setRenderingHints(RenderingHints r){
        this.render = r;
        
    }
    
    /**
     * Método que devuelve el valor del alisado.
     * @return Devuelve un objeto de tipo RenderingHints
     */
    public RenderingHints getRenderingHints(){
        return this.render;
    }
    
    /**
     * Método que almacena si el relleno está activo.
     * @param relleno valor del relleno actual.
     */
    public void setRelleno(boolean relleno){
        this.relleno = relleno;
    }
    
    /**
     * 
     * @return Devuelve un booleano con el valor de relleno actual.
     */
    public boolean getRelleno(){
        return this.relleno;
    }
    
    /**
     * Comprueba si el composite está activo.
     * @return Devuelve true en caso de que el composite esté activado. False en
     * caso contrario.
     */
    public boolean compositeActivo(){
        // Devolvemos si composite está activado
        return ((AlphaComposite) this.composite).getRule() == AlphaComposite.SRC_OVER;
    }
    
    /**
     * 
     * @return Devuelve un entero que se corresponde con el grosor actual.
     */
    public int grosor(){
        return (int) ((BasicStroke)this.stroke).getLineWidth();
    }
    
    /**
     * 
     * @return Devuelve true cuando el alisado está activado. 
     */
    public boolean alisadoActivo(){
        return (((RenderingHints)this.render).values()).contains(RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    /**
     * 
     * @return Devuelve un string indicando el nombre del color actual. 
     */
    public String getNombreColor(){
        
        if(this.color == Color.black)
            return "Negro";
        
        if(this.color == Color.red)
            return "Rojo";
        
        if(this.color == Color.yellow)
            return "Amarillo";
        
        if(this.color == Color.blue)
            return "Azul";
        
        if(this.color == Color.green)
            return "Verde";
        
        if(this.color == Color.white)
            return "Blanco";
        
        return null;
    }
    
    
    
}
