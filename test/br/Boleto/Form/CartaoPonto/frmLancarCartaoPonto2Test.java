/*
 * Autor: Jonathan Comin Ribeiro
 */
package br.Boleto.Form.CartaoPonto;

import funcoes.CDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class frmLancarCartaoPonto2Test {

    public frmLancarCartaoPonto2Test() {
    }

    @Test
    public void doublePHora() {
        String hora = "07:20";
        System.out.println(CDate.horaPDecimal(hora));
        System.out.println(CDate.decimalPHora(CDate.horaPDecimal(hora), true));
    }

}
