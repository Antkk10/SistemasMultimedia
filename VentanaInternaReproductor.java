/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import com.sun.media.BasicPlayer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Manager;
import javax.media.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.UnsupportedAudioFileException;
import sm.sound.SMClipPlayer;
import sm.sound.SMPlayer;


/**
 * Clase que hereda de VentanaInterna, muestra una ventana que almacena un audio
 * y que permite reproducir y parar el audio. Además esta clase contiene otra clase
 * que se utiliza como manejador de eventos para que según las acciones que se produzcan
 * en la ventana, la instancia almacenada en el atributo vp, se envien mensajes para que
 * actualice los botones de reproducir o pausar.
 * 
 * @author Antonio Manuel Fernández Cantos
 */
public class VentanaInternaReproductor extends VentanaInterna {

    /**
     * Atributo de tipo SMClipPlayer en el cual contiene el fichero de audio
     * y la funcionalidad necesaria para reproducir el propio audio contenido.
     */
    private SMClipPlayer smplayer;
    /**
     * Atributo de tipo Proceso. Este atributo tiene la funcionalidad necesaria para mostrar
     * el tipo que lleva de reproducción en ese momento el audio.
     */
    private Proceso proceso;
    /**
     * Entero que almacena el tipo completo del audio.
     */
    private int totalSegundos;
    /**
     * Atributo que indica si el audo se está reproduciendo. 
     */
    private boolean reproduciendo;
    /**
     * Constructor de la clase que inicializa los atributos VentanaInterna vp, y
     * el tipo de ventana. Además crea una instancia de smplayer pasando el archivo de audio 
     * y también se almacena el nombre de dicho audio.
     * 
     */
    private VentanaInternaReproductor(File f, VentanaPrincipal vp, String nombre) {
        super(vp, VentanaInterna.AUDIO, nombre);
        initComponents();
        this.smplayer= new SMClipPlayer(f);
        this.setManejadorEventos();
        this.obtenerSegundos(f);
        reproduciendo = false;
        
        
    }
    
    /**
     * Método que obtiene la cantidad total de segundos de un fichero de audio.
     * @param f contiene el fichero de audio del que se obtienen los segundos
     */
    private void obtenerSegundos(File f){
        try{
            // Abrimos el fichero de audio.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // Obtenemos el tipo en microsegundos y dividimos por 1.000.000 para obtener los segundos
            totalSegundos = (int) clip.getMicrosecondLength()/1000000;
            // Obtenemos los minutos y segundos
            int minutos = totalSegundos / 60;
            int segundos = totalSegundos % 60;
            // Lo almacenamos en la correspondiente etiqueta.
            this.segFin.setText(String.valueOf(minutos) + ":" + String.valueOf(segundos));
            this.segProgreso.setText("0:00");
        }
        catch(Exception e){
            
        }

        
    }
    
    
    /**
     * Método de clase que instancia y devuelve un objeto de esta clase.
     * @param f fichero que contiene el audio a almacenar
     * @param vp instancia de una VentanaPrincipal que crea esta ventana
     * @param nombre corresponde al nombre del archivo de audio.
     * @return Devuelve la instancia creada. En caso de que no se pueda crear la instancia
     * devuelve null.
     */
    public static VentanaInternaReproductor getInstance(File f, VentanaPrincipal vp, String nombre){
        VentanaInternaReproductor v = new VentanaInternaReproductor(f, vp, nombre);
        
        if(v.smplayer != null) // Si el archivo se almacena correctamente
            return v;
        else
            return null;
    }


    /**
     * 
     * @return Devuelve el nombre del fichero de audio.
     */
    @Override
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Almacena el nombre del fichero de audio.
     * @param n contiene el nombre del fichero.
     */
    @Override
    public void setNombre(String n){
        this.nombre = n;
    }
    /** 
     * Método play que consiste en reproducir el audio almacenado en la ventana.
     */
    public void play(){
        
        if(smplayer != null){
            smplayer.play();
            this.proceso = new Proceso();
            this.proceso.start();
            reproduciendo = true;
            
            
        }
    }
    
    /**
     * Método stop que pausa el audio almacenado en la ventana.
     */
    public void stop(){
        
        if(smplayer != null){
            smplayer.stop();
            if(this.proceso != null)
                this.proceso.interrumpir();
            reproduciendo = false;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        segProgreso = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        segFin = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximumSize(new java.awt.Dimension(260, 50));
        setMinimumSize(new java.awt.Dimension(260, 50));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        segProgreso.setMaximumSize(new java.awt.Dimension(40, 20));
        segProgreso.setMinimumSize(new java.awt.Dimension(40, 20));
        segProgreso.setPreferredSize(new java.awt.Dimension(40, 20));
        getContentPane().add(segProgreso);

        barraProgreso.setMaximumSize(new java.awt.Dimension(160, 40));
        barraProgreso.setMinimumSize(new java.awt.Dimension(160, 40));
        barraProgreso.setPreferredSize(new java.awt.Dimension(160, 40));
        getContentPane().add(barraProgreso);

        segFin.setMaximumSize(new java.awt.Dimension(40, 40));
        segFin.setMinimumSize(new java.awt.Dimension(40, 40));
        segFin.setPreferredSize(new java.awt.Dimension(40, 40));
        getContentPane().add(segFin);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que para el audio cuando el usuario cierra la ventana.
     * @param evt 
     */
    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.stop();
    }//GEN-LAST:event_formInternalFrameClosed

    /**
     * Método que envia un mensaje para activar el botón de la ventana principal cuando
     * esta ventana está activa en la ventana principal.
     * @param evt 
     */
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        this.vp.actualizaBotonPlay(reproduciendo);
    }//GEN-LAST:event_formInternalFrameActivated

    /**
     * Método que asigna a smplayer el manejador de eventos creado más abajo en la clase.
     */
    private void setManejadorEventos(){
        smplayer.setLineListener(new ManejadorAudio());
    }
    

    /**
     * Método que devuelve el tipo de VentanaInterna que en este caso es de audio.
     * @return 
     */
    @Override
    public int getTipo() {
        return tipo;
    }

    /**
     * Clase que se usará para manejar los eventos que se vayan produciendo. En este caso
     * actualiza los botones de la ventana principal en función de si el audio
     * se encuentra reproduciendo o pausado.
     */
    class ManejadorAudio implements LineListener {
        
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.START) {
                vp.actualizaEstado("Reproduciendo");
                vp.actualizaBotonPlay(reproduciendo);
            }
            if (event.getType() == LineEvent.Type.STOP) {
                vp.actualizaEstado("Pausa");
                proceso.interrumpir();
                stop();
            }
            if (event.getType() == LineEvent.Type.CLOSE) {
                vp.actualizaBotonStop();
                vp.actualizaBotonPlay(false);
                proceso.interrumpir();
            }
        }
    }
    
    /**
     * Método que actualiza la barra de estado de esta ventana.
     * @param x corresponde al porcentaje de progreso de la barra de progreso.
     * Los valores deben ser entre 0 y 100.
     */
    private void setValorBarraProgreso(int x){
        this.barraProgreso.setValue(x);
    }
    
    /**
     * Clase que implementa una hebra para que se pueda actualizar en la ventana
     * interna reproductor el tiempo de la canción y la barra de progreso.
     * 
     * En el uso de hebras, se puede instanciar y comenzar con sus operaciones, pero a la hora
     * de pausarlas necesitamos una booleano (ejecutar) para terminar el proceso de ejecución de 
     * la hebra ya que actualmente no existen métodos para detener la hebra.
     */
    class Proceso implements Runnable{
        /**
         * Atributo de Thread que se corresponde con la hebra que ejecutará de forma
         * paralela mientras el usuario realiza otras operaciones.
         */
        private Thread th;
        /**
         * Corresponde a los minutos de la canción en un momento determinado.
         */
        private int minutos;
        /**
         * Corresponde a los segundos de la canción en un momento determinado.
         */
        private int segundos;
        /**
         * Indica si la hebra de seguir reproduciendo o no.
         */
        boolean ejecutar;
        /**
         * Constructor de la clase que inicializa los valores por defecto.
         */
        public Proceso(){
            // Indicamos como parámetro this para decir que ejecutará el método run de esta clase.
            th = new Thread(this);
            minutos = 0;
            segundos = 0;
            ejecutar = true;
        }
        
        /**
         * Método que inicializa la hebra.
         */
        public void start(){
            th.start();
            
        }
        
        /**
         * Método que pone a false el atributo ejecutar. Esto lo utilizamos para parar
         * la ejecución de la hebra.
         */
        public void interrumpir(){
            ejecutar = false;
        }
        
        /**
         * Método que ejecuta la hebra cuando es inicializada. En este método se irá actualizando
         * los segundos de reproducción mostrados en la ventana al igual que la barra de progreso.
         */
        @Override
        public void run() {
            while(ejecutar){
                
                try {
                    
                    // Espera un segundo
                    Thread.sleep(1000);
                    segundos++;
                    // Actualizamos correctamente los valores.
                    if(segundos == 60){
                        segundos=0;
                        minutos++;
                    }
                    
                    // Actualizamos la barra de progreso
                    // Obtenemos los segundos totales
                    int aux = minutos * 60 + segundos;
                    
                    // Usamos regla de tres, si la variable totalSegundos tiene
                    // el 100% de los segundos, entonces segundos actuales x 100
                    // dividido entre totalSegundos
                    setValorBarraProgreso((100*aux)/totalSegundos);
                    
                    segProgreso.setText(String.valueOf(minutos) + ":" + String.valueOf(segundos));
                } catch (InterruptedException ex) {
                   
                }
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JLabel segFin;
    private javax.swing.JLabel segProgreso;
    // End of variables declaration//GEN-END:variables
}
