/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.bean;

import model.DAO.PendenciaDAO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class PendenciaTest {
    
    public PendenciaTest() {
    }

    @Test
    public void testSomeMethod() {
        Pendencia p = new Pendencia();
        Fornecedor f = new Fornecedor();
        f.setNome("teste");
        
        p.setFornecedor(f);
        p.setMotivo("um motivo");
        p.setValor(0);
        if (new PendenciaDAO().addPendencia(p)){
            System.out.println("foi");
        }
    }
    
}
