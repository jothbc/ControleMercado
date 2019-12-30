/*
 * Autor: Jonathan Comin Ribeiro
 */
package br.Boleto.Form.CartaoPonto;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.bean.FeriadosBrasil;

/**
 *
 * @author User
 */
public class cellRenderCartaoPonto extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        boolean test = true;
        for (FeriadosBrasil f : CartaoPontoJF.feriados) {
            if (f.getDia() == row + 1) {
                setBackground(Color.CYAN);
                setForeground(Color.BLACK);
                test = false;
            }
        }
        if (test) {
            setBackground(Color.white);
            setForeground(Color.BLACK);
        }
        if (String.valueOf(jtable.getValueAt(row, 0)).equals("D") || String.valueOf(jtable.getValueAt(row, 0)).equals("R")) {
            setBackground(new Color(181, 230, 29));
        }
        else if (String.valueOf(jtable.getValueAt(row, 0)).equals("F")) {
            setBackground(new Color(255, 25, 25));
        }
        else if (String.valueOf(jtable.getValueAt(row, 0)).equals("A")) {
            setBackground(new Color(255, 255, 0));
        }
        return this;
    }
}
