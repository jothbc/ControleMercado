/*
 * Autor: Jonathan Comin Ribeiro
 */
package br.Boleto.Form.CartaoPonto;

import funcoes.CDate;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class frmLancarCartaoPonto2Test {

    public frmLancarCartaoPonto2Test() {
    }

    @Test
    @Ignore
    public void doublePHora() {
        String hora = "07:20";
        System.out.println(CDate.horaPDecimal(hora));
        System.out.println(CDate.decimalPHora(CDate.horaPDecimal(hora), true));
    }

    @Test
    public void testJFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Exportação para PDF");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("PDF", "pdf");
        chooser.setFileFilter(extensionFilter);
        int op = chooser.showSaveDialog(null);
        if(op == JFileChooser.APPROVE_OPTION){
            System.out.println(chooser.getSelectedFile().getAbsolutePath());
        }
    }

}
