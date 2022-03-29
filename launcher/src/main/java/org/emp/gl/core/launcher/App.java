package org.emp.gl.core.launcher;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimeChangeProvider;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    static {
        Lookup.getInstance().register(TimerService.class, new DummyTimeServiceImpl());
    }

    public static void main(String[] args) {

//        TimerChangeListener afficheurHeureSurConsole=new AfficheurHeureSurConsole();
//        Lookup.getInstance().getService(TimerService.class).addTimeChangeListener(afficheurHeureSurConsole);

//        TimerChangeListener compteARebour=new CompteARebour(5);
//        Lookup.getInstance().getService(TimerService.class).addTimeChangeListener(compteARebour);
        CompteARebour compteARebour=new CompteARebour(5);
        Lookup.getInstance().getService(TimerService.class).addTimeChangeListener(compteARebour,"SECONDS");
        //instantiation random
//        for(int i=0;i<10;i++)
//        {
//            Random random=new Random();
//            CompteARebour compteARebour=new CompteARebour(random.nextInt(10)+5);
//            Lookup.getInstance().getService(TimerService.class).addTimeChangeListener(compteARebour);
//        }

    }


    private static void testDuTimeService() {

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
