/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.bean;

import funcoes.CDate;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author User
 */
public class cellRenderBoleto extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1); //To change body of generated methods, choose Tools | Templates.
        Object pago = jtable.getValueAt(i, 5);
        if (pago!=null){
            setBackground(Color.yellow);
            setForeground(Color.red);
        }else{
            setBackground(Color.white);
            setForeground(Color.BLACK);
        }
        return this;
    }
    
}
