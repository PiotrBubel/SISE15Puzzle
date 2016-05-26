package com.mycompany.sisezad1;

import com.mycompany.sisezad1.utils.ConsoleMode;
import com.mycompany.sisezad1.utils.ReportsGenerator;


/**
 * @author Piotrek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //TODO uncomment this
        //String[] argsTmp = new String[]{"-a", "a", "2"};
        //ConsoleMode.mainLoop(argsTmp);

        ReportsGenerator.generateAllStatistics("report");
        //ConsoleMode.oldMain();

        System.exit(0);
    }
}
