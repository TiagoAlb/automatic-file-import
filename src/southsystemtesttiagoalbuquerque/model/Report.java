/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package southsystemtesttiagoalbuquerque.model;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author Tiago
 */
public class Report {
    private int customerQtd = 0;
    private int sellerQtd = 0;
    private double sumMostExpensiveSale = 0;
    private double sumWorstSale = 0;
    private String mostExpensiveSale = "";
    private String worstSelesman = "";
    private String reportFileInName = "";
    private String reportName;

    public Report() {
        Random numberRandom = new Random();
        this.reportName = "REPORT-FILE-" + numberRandom.nextInt(1000) + "-" + LocalDateTime.now().toString().replace(":", "").replace(".", "");
    }
    
    public int getCustomerQtd() {
        return customerQtd;
    }

    public void setCustomerQtd(int customerQtd) {
        this.customerQtd = customerQtd;
    }
    
    public void setCountCustomerQtd() {
        this.customerQtd++;
    }

    public int getSellerQtd() {
        return sellerQtd;
    }

    public void setSellerQtd(int sellerQtd) {
        this.sellerQtd = sellerQtd;
    }
    
    public void setCountSellerQtd() {
        this.sellerQtd++;
    }
    
    public String getMostExpensiveSale() {
        return mostExpensiveSale;
    }

    public void setMostExpensiveSale(String mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public String getWorstSelesman() {
        return worstSelesman;
    }

    public void setWorstSelesman(String worstSelesman) {
        this.worstSelesman = worstSelesman;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportFileInName() {
        return reportFileInName;
    }

    public void setReportFileInName(String reportFileInName) {
        this.reportFileInName = reportFileInName;
    }

    public double getSumMostExpensiveSale() {
        return sumMostExpensiveSale;
    }

    public void setSumMostExpensiveSale(double sumMostExpensiveSale) {
        this.sumMostExpensiveSale = sumMostExpensiveSale;
    }

    public double getSumWorstSale() {
        return sumWorstSale;
    }

    public void setSumWorstSale(double sumWorstSale) {
        this.sumWorstSale = sumWorstSale;
    }
}
