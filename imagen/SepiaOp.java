/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;
import sm.image.BufferedImagePixelIterator;

/**
 * Clase que representa la operación sepia sobre una imagen. La operación sepia 
 * transforma a marron la imagen con tonalidades blanco y negro.
 * 
 * @author Antonio Manuel Fernández Cantos
 */
public class SepiaOp extends BufferedImageOpAdapter {
    
    /**
     * Constructor por defecto.
     */
    public SepiaOp(){
        
    }
    
    /**
     * Método que realiza la operación sepia sobre una imagen, almacena el resultado en otra
     * y lo devuelve al final del método.
     * @param src imagen fuente.
     * @param dest imagen destino.
     * @return devuelve la imagen nueva.
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dest){ 
        
        if (src == null) { // Imagen fuente a null
            throw new NullPointerException("src image is null");
        }
    
        if (dest == null) { // Imagen destino a null
            // Crea la imagen
            dest = createCompatibleDestImage(src, null);
        }
        
        // Iterador sobre el pixel.
        BufferedImagePixelIterator.PixelData pixel;
        
        WritableRaster destRaster = dest.getRaster();
        
        
        int rojo, verde, azul;
        
        // Recorre todos los pixeles de la imagen
        for(BufferedImagePixelIterator it=new BufferedImagePixelIterator(src); it.hasNext();) {
            pixel = it.next();
            
            // Obtiene el valor de rojo, verde, azul
            rojo = pixel.sample[0];
            verde= pixel.sample[1];
            azul = pixel.sample[2];
            
            // La función min devuelve el número más pequeño de entre dos
            pixel.sample[0] = (int) Math.min(255, 0.393*rojo + 0.769*verde + 0.189*azul);
            pixel.sample[1] = (int) Math.min(255, 0.349*rojo + 0.686*verde + 0.168*azul);
            pixel.sample[2] = (int) Math.min(255, 0.272*rojo + 0.534*verde + 0.131*azul);
            
            // Almacena el pixel modificado.
            destRaster.setPixel(pixel.col, pixel.row, pixel.sample);
            
        }
        
        return dest; // Devuelve la imagen modificada.
    }
    
}
