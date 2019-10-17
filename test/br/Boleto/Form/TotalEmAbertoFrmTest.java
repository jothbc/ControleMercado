/*
 * Autor: Jonathan Comin Ribeiro
 */
package br.Boleto.Form;

import java.util.Calendar;
import java.util.List;
import model.DAO.funcionario.CartaoPontoDAO;
import model.DAO.funcionario.FuncionarioDAO;
import model.bean.Funcionario;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class TotalEmAbertoFrmTest {

    public TotalEmAbertoFrmTest() {
    }

    @Test
    @Ignore
    public void testSomeMethod() {
        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.MONTH, -1);
        System.out.println(calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        for (int x = 1; x <= calendario.getActualMaximum(Calendar.DAY_OF_MONTH); x++) {
            calendario.set(Calendar.DAY_OF_MONTH, x);
            if(calendario.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                System.out.println(x);
            }
        }
    }
    @Test
    public void testCartao(){
        List<Funcionario> fun = new FuncionarioDAO().findAll();
        Calendar c = Calendar.getInstance();
        //c.add(Calendar.MONTH, -1);
        for(Funcionario f:fun){
            System.out.println(f.getNome());
            System.out.println(f.getSalario());
            System.out.println(new CartaoPontoDAO().lancado(f, c.get(Calendar.MONTH), c.get(Calendar.YEAR)));
            
        }
    }

}
