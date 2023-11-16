package com.mygdx.juego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements Runnable {

    //Declaramos las variables necesarias para la conexion y comunicacion
    private Socket cliente;
    private DataOutputStream out;
    private DataInputStream in;
    //El puerto debe ser el mismo en el que escucha el servidor
    private int puerto = 2027;
    //Si estamos en nuestra misma maquina usamos localhost si no la ip de la maquina servidor
    private String host = "localhost";

    //Variables del frame 
    private String mensaje;


    //Constructor recibe como parametro la ventana (Frame), para poder hacer modificaciones sobre los botones
    public Client() {
        try {
            //Creamos el socket con el host y el puerto, declaramos los streams de comunicacion
            cliente = new Socket(host, puerto);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            mensaje = in.readUTF();
            String split[] = mensaje.split(";");
            System.out.println("Desde cliente: "+mensaje);

            //Ciclo infinito
            while (true) {
                //Recibimos el mensaje
                mensaje = in.readUTF();
                String[] mensajes = mensaje.split(";");
                System.out.println("Desde cliente: "+mensaje);                               

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion sirve para enviar la jugada al servidor
    public void enviar(String mensaje_out) {
        try {
            out.writeUTF(mensaje_out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
