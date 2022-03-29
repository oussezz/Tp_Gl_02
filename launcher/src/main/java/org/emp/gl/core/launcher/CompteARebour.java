/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.core.launcher;

import java.beans.PropertyChangeEvent;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * @author tina
 */
public class CompteARebour implements TimerChangeListener {

    int timeToLeave=0;
    CompteARebour(int t)
    {
        this.timeToLeave=t;
    }
    @Override
    public void propertyChange(String propertyName, Object oldValue, Object newValue) {
        Object lock;

        if(propertyName=="seconde") {
            System.out.println("TimeReste: "+timeToLeave);
            if(timeToLeave==0)
            {


                Lookup lookup=Lookup.getInstance();
                lookup.getService(TimerService.class).removeTimeChangeListener(this);


            }
            else
                this.timeToLeave -= 1;
        }


    }
}
