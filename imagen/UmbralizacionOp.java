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
 * Clase que representa la operación de umbralización sobre una imagen. Con esta operación
 * lo que se pretende es que si un pixel supera un umbral, se ponga a blanco y en caso
 * contrario ese pixel a negro.
 * @author Antonio Manuel Fernández Cantos
 */
public class UmbralizacionOp extends BufferedImageOpAdapter {
    
    /**
     * El umbral para medir cuando un pixel se pondrá a blanco o negro.
     */
    private int umbral;
    
    /**
     * Constructor de la clase.
     * @param umbral indica el nivel de umbral.
     */
    public UmbralizacionOp(int umbral){
        this.umbral = umbral;
    }
    
    /**
     * Método en el cual se realiza la operación umbralización. Recibe la imagen fuente,
     * realiza la operación con los pixeles de la imagen fuente y los almacena en la imagen
     * destino.
     * @param src imagen fuente.
     * @param dest imagen destino.
     * @return Devuelve la imagen con el resultado de la operación de umbralización.
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dest){
        if (src == null) { // Imagen fuente no existe
            throw new NullPointerException("src image is null");
        }
    
        if (dest == null) {
            // Crea la imagen
            dest = createCompatibleDestImage(src, null);
        }
        
        // iterador sobre el pixel.
        BufferedImagePixelIterator.PixelData pixel;
        
        WritableRaster destRaster = dest.getRaster();
        
        
        int rojo, verde, azul;
        int intensidad;
        
        /*
        Este condicional actua sobre las imágenes de tipo gris, ya que solo tienen disponible
        el primer sample del pixel.
        */
        if(src.getType() == BufferedImage.TYPE_BYTE_GRAY){
            for(BufferedImagePixelIterator it = new BufferedImagePixelIterator(src); it.hasNext();){
                pixel = it.next();
                
                // Si el nivel de gris supera el umbral lo ponemos 255
                if(pixel.sample[0] >= this.umbral)
                    pixel.sample[0]=255;
                else // No supera el umbral
                    pixel.sample[0]=0;
                
                destRaster.setPixel(pixel.col, pixel.row, pixel.sample);
            }
            
        }
        else{ // Para las demás tipos de imágenes.
            // Recorre todos los pixeles de la imagen
            for(BufferedImagePixelIterator it=new BufferedImagePixelIterator(src); it.hasNext();) {
                pixel = it.next();
                // Obtiene el valor de rojo, verde, azul
                rojo = pixel.sample[0];
                verde= pixel.sample[1];
                azul = pixel.sample[2];

                // Calcula la intensidad
                intensidad = (rojo + verde + azul) / 3;

                if(intensidad >= this.umbral) // Supera el umbral
                    pixel.sample[0] = pixel.sample[1] = pixel.sample[2] = 255;
                else // No supera el umbral
                    pixel.sample[0] = pixel.sample[1] = pixel.sample[2] = 0;

                // Almacena el pixel modificado en la imagen destino.
                destRaster.setPixel(pixel.col, pixel.row, pixel.sample);

            }
        }


        return dest; // Devuelve la imagen destino.
    }
}
