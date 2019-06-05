/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.bean.Boleto;
import model.bean.Fornecedor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class BoletoDAOTest {

    public BoletoDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1);
        Boleto boleto = new Boleto();
        boleto.setCd_barras("123456789");
        boleto.setFornecedor_id(fornecedor);
        boleto.setValor(50);
        boleto.setVencimento("23/10/2018");

        BoletoDAO dao = new BoletoDAO();
        if (dao.save(boleto)) {
            System.out.println("Salvo com sucesso!");
        } else {
            fail("Erro ao salvar");
        }
    }
    @Test
    //@Ignore
    public void Pagar() {
        Boleto boleto = new Boleto();
        boleto.setCd_barras("1");
        boleto.setPago("24/10/2018");
        BoletoDAO dao = new BoletoDAO();
        if (dao.update(boleto)) {
            System.out.println("Salvo com sucesso!");
        } else {
            fail("Erro ao salvar");
        }
    }

}
