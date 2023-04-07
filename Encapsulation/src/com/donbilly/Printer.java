package com.donbilly;

public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplexPrinter;

    public Printer(int tonerLevel, boolean duplexPrinter) {
        if (tonerLevel > -1 && tonerLevel <100){
            this.tonerLevel = tonerLevel;
        } else if (tonerLevel > 100) {
            this.tonerLevel = 100;
        } else {
            this.tonerLevel = -1;
        }

        this.pagesPrinted = 0;
        this.duplexPrinter = duplexPrinter;
    }
    public int fillToner(int tonerAmount){
        if (tonerAmount > 0 && tonerAmount <=100){
            if((this.tonerLevel + tonerAmount)>100){
                return -1;
            }
            this.tonerLevel += tonerAmount;
            return this.tonerLevel;
        }else{
            return -1;
        }
    }
    public int printPages(int pages){
        int pagesToPrint = pages;
        if(this.duplexPrinter){
            pagesToPrint = pagesToPrint/2 + pagesToPrint%2;
            System.out.println("Printing in duplex mode");
        }
        this.pagesPrinted += pagesToPrint;
        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }
}
