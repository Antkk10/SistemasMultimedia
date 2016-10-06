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
 * Clase que representa la operación que aumenta el color azul de una imagen.
 * 
 * @author Antonio Manuel Fernández Cantos.
 */
public class AumentarAzul extends BufferedImageOpAdapter {

    /**
     * Atributo que indica cuanto se aumenta el azul.
     */
    private int aumento;
    
    /**
     * Constructor de la clase que indica cuanto se aumenta el azul de la imagen.
     * @param aumento 
     */
    public AumentarAzul(int aumento){
        this.aumento = aumento;
    }
    
    /**
     * Método que aumenta el azul de una imagen y la almacena en otra nueva.
     * Esta nueva imagen es devuelta al objeto que llama a este método.
     * @param src imagen fuente
     * @param dest imagen destino. Puede ser null.
     * @return Devuelve la imagen con el color azul aumentado.
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        
        if (src == null) { // No hay imagen fuente.
            throw new NullPointerException("src image is null");
        }
    
        if (dest == null) { // Si imagen destino es null la inicializa.
            // Crea la imagen compatible.
            dest = createCompatibleDestImage(src, null);
        }
        // Iterador sobre pixel
        BufferedImagePixelIterator.PixelData pixel;
        
        WritableRaster destRaster = dest.getRaster();
        
        
        int azul;
        // Recorre todos los pixeles de la imagen
        for(BufferedImagePixelIterator it=new BufferedImagePixelIterator(src); it.hasNext();) {
            pixel = it.next();
            // Obtiene el azul del pixel
            azul = pixel.sample[2];
            // Obtiene el nuevo valor del azul
            pixel.sample[2] = (int) Math.min(255, azul + this.aumento);
            // Almacena el pixel modificado.
            destRaster.setPixel(pixel.col, pixel.row, pixel.sample);
        }
        
        return dest;
    }
    
}
