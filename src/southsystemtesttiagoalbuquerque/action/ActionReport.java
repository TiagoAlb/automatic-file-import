/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package southsystemtesttiagoalbuquerque.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import southsystemtesttiagoalbuquerque.model.Report;
import southsystemtesttiagoalbuquerque.utils.Utils;

/**
 *
 * @author Tiago
 */
public class ActionReport {
    
    public Report analyzesFile(String fileDirectory) {
        Report report = new Report();
        try (Stream<String> lines = Files.lines(Paths.get(fileDirectory))){
            lines.forEach((line)->{
                line=line.trim();
                String [] data = line.split("ç");
             
                if(data[0].contains("001"))
                    report.setCountSellerQtd();
                else if(data[0].contains("002"))
                    report.setCountCustomerQtd(); 
                else if(data[0].contains("003")) {
                    String [] params = data[2].replace("[", "").replace("]", "").split(",");
                    double sumSale = 0;
                            
                    for(String param : params) {
                        String [] sale = param.split("-");
                        sumSale += parseDouble(sale[2]);
                    }
                        
                    if(report.getSumWorstSale() > sumSale) {
                        report.setSumWorstSale(sumSale);
                        report.setWorstSelesman(data[3]);
                    }
                    
                    if(report.getSumMostExpensiveSale() < sumSale) {
                        if(report.getSumMostExpensiveSale()==0) {
                            report.setSumWorstSale(sumSale);
                            report.setWorstSelesman(data[3]);
                        }
                        report.setSumMostExpensiveSale(sumSale);
                        report.setMostExpensiveSale(data[1]);
                    } 
                }
            });
            
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
	} finally {
            return report;
        }
    }
    
    public void generateCompleteReport(String directoty, Report[] reports) {
        try {
            Report reportComplete = new Report();
            File file = new File(directoty);
            
            if(!file.isDirectory())
                file.mkdirs();

            directoty = directoty + reportComplete.getReportName() + ".done.dat";
            file = new File(directoty);
            
            Utils util = new Utils();
            
            if(file.createNewFile()) {
                FileWriter writer = new FileWriter(directoty);
                LocalDate localDate = LocalDate.now();
                // HEADER COMPLETE
                writer.write(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate) 
                                + util.putSpace(" DATA ANALYSIS REPORT - SOUTH SYSTEM COMPANY ", 40) 
                                + util.putSpace(" -- RELATÓRIO DO TIAGO -- ", 25) 
                                + reportComplete.getReportName());        
                
                writer.write("\n");
                writer.write(util.completeWith("_", 123));
                writer.write("\n");
                
                String idMostExpensiveSale = reports[0].getMostExpensiveSale();
                double sumMostExpensiveSale = reports[0].getSumMostExpensiveSale();
                
                String worstSelesman = reports[0].getWorstSelesman();
                double sumWorstSale = reports[0].getSumWorstSale();
                
                for(Report report : reports) {
                    // HEADER REPORT FILE
                    writer.write(util.completeWith(".", 123));
                    writer.write("\n");
                    writer.write("FILE NAME: " 
                            + util.putSpace(report.getReportFileInName(), 20)
                            + "GENERETED NAME: " + report.getReportName());
                    writer.write("\n");
                    writer.write(util.completeWith(".", 123));
                    writer.write("\n");
                    
                    writer.write(util.putSpace("CUSTOMERS", 20));
                    writer.write(util.putSpace("SELLERS", 20));
                    writer.write(util.putSpace("MOST EXPENSIVE SALE ID", 50));
                    writer.write("WORST SELESMAN");
                    
                    writer.write("\n");
                    
                    // BODY FILE
                    writer.write(util.putSpace(String.valueOf(report.getCustomerQtd()), 20));
                    writer.write(util.putSpace(String.valueOf(report.getSellerQtd()), 20));
                    writer.write(util.putSpace(String.valueOf(report.getMostExpensiveSale()), 50));
                    writer.write(report.getWorstSelesman());
                    
                    writer.write("\n");
                    writer.write("\n");
                    
                    reportComplete.setCustomerQtd(reportComplete.getCustomerQtd() + report.getCustomerQtd());
                    reportComplete.setSellerQtd(reportComplete.getSellerQtd() + report.getSellerQtd());
                    
                    if(report.getSumMostExpensiveSale() > sumMostExpensiveSale) {
                        idMostExpensiveSale = report.getMostExpensiveSale();
                        sumMostExpensiveSale = report.getSumMostExpensiveSale();
                    }
                    
                    if(report.getSumMostExpensiveSale() > sumMostExpensiveSale) {
                        worstSelesman = report.getWorstSelesman();
                        sumWorstSale = report.getSumWorstSale();
                    }
                }
                
                // FOOTER FILES
                writer.write("\n");
                writer.write(util.completeWith(".", 123));
                writer.write("\n");
                writer.write("TOTALS:");
                writer.write("\n");
                writer.write(util.completeWith(".", 123));
                writer.write("\n");
                
                writer.write(util.putSpace("CUSTOMERS", 20));
                writer.write(util.putSpace("SELLERS", 20));
                writer.write(util.putSpace("MOST EXPENSIVE SALE ID", 50));
                writer.write("WORST SELESMAN");
                
                writer.write("\n");
                
                writer.write(util.putSpace(String.valueOf(reportComplete.getCustomerQtd()), 20));
                writer.write(util.putSpace(String.valueOf(reportComplete.getSellerQtd()), 20));
                writer.write(util.putSpace(idMostExpensiveSale, 50));
                writer.write(worstSelesman);
                
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
	} finally {
            System.out.println("RELARÓRIO GERADO COM SUCESSO!");
        }
    }
}
