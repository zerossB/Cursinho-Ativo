package toolbox.print;

/*
 * ImprString.java
 *
 * Created on 9 de Marco de 2005, 18:32
 *
 * ATENCAO: 1cm  = 28 pixels
 * ATENCAO: 1Pol = 72 pixels
 * tamanho da folha A4 630x864
 */
/**
 *
 * @author  Marcos Morise
 */
public class PrintHTML extends javax.swing.JEditorPane implements java.awt.print.Printable {

    /**
     * Construtor
     * @param html Código HTML que será impresso
     * @param pageFormat Formato da página
     */
    public PrintHTML(String html, java.awt.print.PageFormat pageFormat) {
        super("text/html", html);
        javax.swing.RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
        this.setSize((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());

        java.awt.print.PrinterJob prnJob = java.awt.print.PrinterJob.getPrinterJob();
        prnJob.setPrintable(this, pageFormat);

        if (prnJob.printDialog()) {
            try {
                prnJob.print();
            } catch (Exception PrinterException) {
                PrinterException.printStackTrace();
                System.out.println("Nao conseguiu imprimir !!");
            }
        }
    }

    /**
     * Construtor
     * @param html Código HTML que será impresso
     * @param larguraPag Largura da página em pixels 72 pixels = 1 polegada
     * @param alturaPag Altura da página em pixels
     * @param tamMargem Tamanho da margem página em pixels
     */
    public PrintHTML(String html, int larguraPag, int alturaPag, int tamMargem) {
        super("text/html", html);
        javax.swing.RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);

        java.awt.print.PrinterJob prnJob = java.awt.print.PrinterJob.getPrinterJob();
        java.awt.print.PageFormat pageFormat = new java.awt.print.PageFormat();
        java.awt.print.Paper paper = new java.awt.print.Paper();

        paper.setSize(larguraPag, alturaPag);
        paper.setImageableArea(tamMargem, tamMargem, larguraPag - tamMargem * 2, alturaPag - tamMargem * 2);
        pageFormat.setPaper(paper);
        this.setSize((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
        prnJob.setPrintable(this, pageFormat);

        if (prnJob.printDialog()) {
            try {
                prnJob.print();
            } catch (Exception PrinterException) {
                PrinterException.printStackTrace();
                System.out.println("Nao conseguiu imprimir !!");
            }
        }
    }

    /**
     * Construtor
     * @param html Código HTML que será impresso
     */
    public PrintHTML(String html) {
        super("text/html", html);
        javax.swing.RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);

        java.awt.print.PrinterJob prnJob = java.awt.print.PrinterJob.getPrinterJob();
        java.awt.print.PageFormat pageFormat = new java.awt.print.PageFormat();
        pageFormat = prnJob.pageDialog(pageFormat);
        this.setSize((int) pageFormat.getImageableWidth(), (int) pageFormat.getImageableHeight());
        prnJob.setPrintable(this, pageFormat);

        if (prnJob.printDialog()) {
            try {
                prnJob.print();
            } catch (Exception PrinterException) {
                PrinterException.printStackTrace();
                System.out.println("Nao conseguiu imprimir !!");
            }
        }
    }

    /**
     * Impressao
     */
    @Override
    public int print(java.awt.Graphics g, java.awt.print.PageFormat pageFormat, int pageIndex) throws java.awt.print.PrinterException {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

        //panel Dimension
        java.awt.Dimension d = this.getPreferredSize();
        double panelWidth = d.getWidth();
        double panelHeight = d.getHeight();
        d = this.getSize();
        if (d.getWidth() > panelWidth) {
            panelWidth = d.getWidth();
        }

        //page Dimension
        double pageWidth = pageFormat.getImageableWidth();
        double pageHeight = pageFormat.getImageableHeight();

        // Check the number of pages
        double scale = pageWidth / panelWidth;
        int totalNumPages = (int) Math.ceil(scale * panelHeight / pageHeight);

        // Check for empty pages
        if (pageIndex >= totalNumPages) {
            return java.awt.print.Printable.NO_SUCH_PAGE;
        }

        // Translate and scale the printing area
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY() - pageIndex * pageHeight);
        g2.scale(scale, scale);
        this.paint(g2);

        return java.awt.print.Printable.PAGE_EXISTS;
    }
}
