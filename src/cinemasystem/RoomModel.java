/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author PC
 */
public class RoomModel extends Observable{
    
    private List<Room> rooms = new ArrayList<Room>();

    public void addRoom(List<Room> r){
        this.rooms = r;
        setChanged();
        notifyObservers(rooms);
    }
    
    
}
