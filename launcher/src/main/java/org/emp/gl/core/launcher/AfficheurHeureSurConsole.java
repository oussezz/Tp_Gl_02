/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.core.launcher;

import java.beans.PropertyChangeEvent;
import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author tina
 */
public class AfficheurHeureSurConsole  implements TimerChangeListener{


    @Override
    public void propertyChange(String propertyName, Object oldValue, Object newValue) {
        Lookup lookup=Lookup.getInstance();
        TimerService timerService=lookup.getService(TimerService.class);
        System.out.println(timerService.getHeures()+":"+timerService.getMinutes()+":"+ timerService.getSecondes()+":"+timerService.getDixiemeDeSeconde());

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
