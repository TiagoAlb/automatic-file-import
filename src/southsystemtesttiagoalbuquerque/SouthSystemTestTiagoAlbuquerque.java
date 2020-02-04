/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package southsystemtesttiagoalbuquerque;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import southsystemtesttiagoalbuquerque.action.ActionReport;
import southsystemtesttiagoalbuquerque.model.Report;

/**
 *
 * @author Tiago
 */
public class SouthSystemTestTiagoAlbuquerque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Thread(threadFiles).start();
    }
    
    private static Runnable threadFiles = new Runnable() {
        public void run() {
            try{
                String inDirectory = System.getProperty("user.home") + "\\data\\in\\";
                String outDirectory = System.getProperty("user.home") + "\\data\\out\\";
                File file = new File(inDirectory);
                
                boolean noConditionToStop = true;
                while(noConditionToStop) {
                    String [] files = file.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                           return file.isDirectory() && name.endsWith(".dat");
                        }
                    });

                    ActionReport reportAction = new ActionReport();
                    Report[] reports = new Report[files.length];

                    for(int i=0; i<files.length; i++) {
                        reports[i] = reportAction.analyzesFile(inDirectory + files[i]);
                        reports[i].setReportFileInName(files[i]);
                    }

                    reportAction.generateCompleteReport(outDirectory, reports);
                    
                    Thread.currentThread().sleep(1000);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    };

}

