/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.File;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import sm.sound.SMRecorder;
import sm.sound.SMSoundRecorder;

/**
 * Clase VentanaInternaGrabador que hereda de VentanaInterna. Lo que se pretende 
 * con esta clase es almacenar un fichero de audio para grabar lo que el usuario
 * desee encima de el. La clase contiene la funcionalidad necesaria para empezar y parar
 * la grabación, un método para devolver el tipo de ventana que es y un manejador de eventos.
 * 
 * @author Antonio Manuel Fernández Cantos
 */
public class VentanaInternaGrabador extends VentanaInterna {

    /**
     * Atributo de tipo SMSoundRecorder que almacena el archivo para grabar y que
     * contiene métodos para operar sobre el.
     */
    SMSoundRecorder smrecorder;


    /**
     * Constructor de la clase, almacena tanto el archivo para grabar, como
     * un puntero a la ventana principal y el nombre del fichero.
     * Esta ventana principal es la que contiene
     * la instancia de esta ventana.
     * @param f de tipo File, contiene el archivo para grabar.
     * @param vp de tipo VentanaPrincipal, contiene la instancia de VentanaPrincipal
     * que contiene la instancia de esta clase.
     */
    private VentanaInternaGrabador(File f, VentanaPrincipal vp, String nombre){
        super(vp, VentanaInterna.GRABAR, nombre);
        initComponents();
        this.smrecorder = new SMSoundRecorder(f);
        this.setManejadorEventos();
        
    }
    
    /**
     * Método de clase que crea una instancia de esta clase y posteriormente devuelve esta
     * instancia creada. Si la ventana no se ha podido crear correctamente, devuelve null
     * para indicar que la ventana no contiene los valores correctamente.
     * @param f contiene el archivo con el que se realizarán las operaciones. De tipo File.
     * @param vp corresponde con la ventana que está intentando crear esta ventana. De tipo VentanaPrincipal
     * @param nombre es el nombre del ficheor. De tipo string.
     * @return Devuelve la instancia creada. En caso de que no se cree correctamente la ventana
     * devuelve null.
     */
    public static VentanaInternaGrabador getInstance(File f, VentanaPrincipal vp, String nombre){
        VentanaInternaGrabador v = new VentanaInternaGrabador(f, vp, nombre);
        
        if(v.smrecorder != null)
            return v;
        else
            return null;
    }
    
    /**
     * 
     * @return Devuelve el nombre del fichero. De tipo string.
     */
    @Override
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Almacena el nombre del fichero que está utilizando esta ventana.
     * @param n contiene el nombre del fichero a almacenar. De tipo string.
     */
    @Override
    public void setNombre(String n){
        this.nombre = n;
    }
    
    /**
     * Método que permite comenzar la grabación encima del fichero.
     */
    public void record(){
        
        if(smrecorder != null)
            smrecorder.record();

    }
    
    /**
     * Método que permite parar la grabación sobre el fichero.
     */
    public void stop(){
        
        if(smrecorder != null)
            smrecorder.stop();
    }
    
    /**
     * Método que asigna al atributo smrecorder la clase manejadora de eventos
     * para esta clase.
     */
    private void setManejadorEventos(){
        this.smrecorder.setLineListener(new ManejadorAudio());
    }



    /**
     * Método que devuelve el tipo de ventana.
     * @return un entero que indica que la ventana es de tipo grabador.
     */
    @Override
    public int getTipo() {
        return this.tipo;
    }

    /** 
     * Clase manejadora de enventos en la clase VentanaInternaGrabador.
     * En ella se actualizan los botones de la ventana principal que hacen referencia a
     * esta VentanaInternaGrabador.
     */
    class ManejadorAudio implements LineListener {
        /**
         * Método que actualiza los botones de la ventana principal en función
         * de lo que suceda en la ventana de grabación.
         * @param event 
         */
        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.START) {
                vp.actualizaBotonGrabar(true);
                
            }
            if (event.getType() == LineEvent.Type.STOP) {
                vp.actualizaBotonGrabar(false);
                stop();
            }
            if (event.getType() == LineEvent.Type.CLOSE) {
                vp.actualizaBotonGrabar(false);
                
                
            }
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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
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
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que envia un mensaje a la instancia de la Ventana principal almacenada
     * en esta instancia para que desactive el botón grabar.
     * @param evt 
     */
    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        vp.actualizaBotonGrabar(false);
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
