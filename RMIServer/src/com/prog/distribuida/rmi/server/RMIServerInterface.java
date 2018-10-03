/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.distribuida.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public interface RMIServerInterface extends Remote{
    
    public byte[] GetSomeBytesFromServerSide(String stringToConvert) throws RemoteException;
    
    public String prueba(String name) throws RemoteException; 
    
}
