/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * @author tina
 */

public class DummyTimeServiceImpl
        extends TimerTask
        implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;
    @Delegate
    PropertyChangeSupport var=new PropertyChangeSupport(this);

    /**
     * Constructeur du DummyTimeServiceImpl Ici, nnous avons hérité de la classe
     * TimerTask, et nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tocs à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 100, 100);
        setTimeValues();
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

    @Override
    public void run() {
        timeChanged();
    }

    List<TimerChangeListener> listeners = new LinkedList<>();
    List<TimerChangeListener> timerChangeListenerSecondsList=new LinkedList<TimerChangeListener>();
    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
//        listeners.add(pl);
        var.addPropertyChangeListener(pl);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl, String prop) {
        // TODO
//        if(prop=="SECONDS"){
//            timerChangeListenerSecondsList.add(pl);
//        }
        var.addPropertyChangeListener(prop,pl);

    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
//        listeners.remove(pl);
        var.removePropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl,String prop) {
//        if(prop.equals("SECONDS"))
//            timerChangeListenerSecondsList.remove(pl);
        var.removePropertyChangeListener(prop,pl);
    }

    private void timeChanged() {
        setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;

        // informer les listeners !
        dixiemeDeSecondesChanged(oldValue, dixiemeDeSeconde);
    }

    private void dixiemeDeSecondesChanged(int oldValue, int newValue) {
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                    oldValue, dixiemeDeSeconde);
        }
    }


    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;

        secondesChanged(oldValue, secondes);
    }


    private void secondesChanged(int oldValue, int secondes) {

//        for (TimerChangeListener l : listeners) {
//            l.propertyChange(TimerChangeListener.SECONDE_PROP,
//                    oldValue, secondes);
//        }
//
//        for(TimerChangeListener l:timerChangeListenerSecondsList)
//        {
//            PropertyChangeEvent evt=new PropertyChangeEvent(secondes,"SECONDS",null,null);
////            l.propertyChange(TimerChangeListener.SECONDE_PROP,
////                    oldValue, secondes);
//             l.propertyChange(evt);
//        }

        var.firePropertyChange("SECONDS",null,null);
    }


    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;

        minutesChanged (oldValue, minutes) ;
    }

    private void minutesChanged(int oldValue, int minutes) {
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.MINUTE_PROP,
                    oldValue, minutes);
        }
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;

        heuresChanged (oldValue, heures) ;
    }

    private void heuresChanged(int oldValue, int heures) {
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.HEURE_PROP,
                    oldValue, heures);
        }
    }


    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}
