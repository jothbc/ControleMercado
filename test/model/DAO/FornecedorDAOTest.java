/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.bean.Fornecedor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class FornecedorDAOTest {
    
    public FornecedorDAOTest() {
    }
    
    @Test
    @Ignore
    public void inserir() {
        Fornecedor cat= new Fornecedor("test", 123);
        FornecedorDAO dao = new FornecedorDAO();
        if(dao.save(cat)){
            System.out.println("Salvo com sucesso!");
        }else{
            fail("Erro ao salvar");
        }
    }
    @Test
    @Ignore
    public void Atualizar() {
        Fornecedor fornecedor= new Fornecedor("test");
        fornecedor.setId(1);
        FornecedorDAO dao = new FornecedorDAO();
        if(dao.update(fornecedor)){
            System.out.println("Atualização realizada com sucesso!");
        }else{
            fail("Erro ao salvar");
        }
    }
    
    @Test
    @Ignore
    public void listar(){
        FornecedorDAO dao = new FornecedorDAO();
        for (Fornecedor f: dao.findAll()){
            System.out.println("Descricao: "+f.getNome());
        }
    }
    
    @Test
    @Ignore
    public void deletar(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1);
        
        FornecedorDAO dao = new FornecedorDAO();
        
        if (dao.delete(fornecedor)){
            System.out.println("Deletado com sucesso!");
        }else{
            fail("Erro ao deletar.");
        }
    }
    
}
