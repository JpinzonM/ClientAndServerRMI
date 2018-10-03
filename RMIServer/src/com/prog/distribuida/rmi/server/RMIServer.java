/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.distribuida.rmi.server;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author user
 */
public class RMIServer implements RMIServerInterface{

    /**
     * @param args the command line arguments
     */
    private String file=""; 
    public static void main(String[] args) {
        try {
            RMIServer obj = new RMIServer();
            RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(obj, 0);            
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RMIServerInterface", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public byte[] GetSomeBytesFromServerSide(String stringToConvert) throws RemoteException {
        return stringToConvert.getBytes();
        
    }

    @Override
    public String prueba(String name) throws RemoteException {
        System.out.println(name);
        return "hola"+name;
    }
    
    public void setFile(String f){
        file = f; 
    }
    
    public boolean sendData(RMIServerInterface RMISI) throws RemoteException {
        try {
            File f1 = new File(file);
            FileInputStream in = new FileInputStream(f1);
            byte[] datafile = new byte[1024*1024];
            int mylen = in.read(datafile);
            while (mylen >0) {                
                RMISI.sendData(f1.getName(), datafile, mylen);
                mylen = in.read(datafile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false; 
    }

    @Override
    public boolean sendData(String filename, byte[] data, int len) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    

    
}
