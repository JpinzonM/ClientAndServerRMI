/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.distribuida.rmi.server;

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

    
}
