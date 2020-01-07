/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Boleto.Form;

import funcoes.CDate;
import funcoes.CDbl;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import model.DAO.BoletoDAO;
import model.DAO.ChequeDAO;
import model.DAO.FeriadosBrasilDAO;
import model.DAO.ImpostoDAO;
import model.DAO.VariavelFolhaDAO;
import model.DAO.funcionario.CartaoPontoDAO;
import model.DAO.funcionario.FuncionarioDAO;
import model.bean.CartaoPonto;
import model.bean.FeriadosBrasil;
import model.bean.Funcionario;
import model.bean.VariavelFolha;

/**
 *
 * @author User
 */
public class TotalEmAbertoFrm extends javax.swing.JInternalFrame implements Runnable {

    private Thread thread;
    private boolean isRunning = true;
    private List<Funcionario> funcionarios;
    private List<FeriadosBrasil> feriados;
    private JTextArea area = new JTextArea();
    private List<VariavelFolha> variaveis;

    /**
     * Creates new form frmValorAberto
     */
    public TotalEmAbertoFrm() {
        initComponents();
        funcionarios = new FuncionarioDAO().findAll();
        feriados = new FeriadosBrasilDAO().findAll();
        variaveis = new VariavelFolhaDAO().getVariaveis();
        start();
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
            System.out.println("Parado....");
        } catch (InterruptedException ex) {
            Logger.getLogger(TotalEmAbertoFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        boletotxt = new javax.swing.JTextField();
        chequetxt = new javax.swing.JTextField();
        impostotxt = new javax.swing.JTextField();
        totaltxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ultAtualizacaotxt = new javax.swing.JTextField();

        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Valores em Aberto");
        setEnabled(false);
        setFocusable(false);
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setText("Boletos");

        jLabel2.setText("Cheques");

        jLabel3.setText("Impostos");

        boletotxt.setEditable(false);

        chequetxt.setEditable(false);

        impostotxt.setEditable(false);

        totaltxt.setEditable(false);

        jLabel4.setText("Total");

        jLabel5.setText("Ult. Atualização");

        ultAtualizacaotxt.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(impostotxt, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(chequetxt)
                            .addComponent(boletotxt)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(totaltxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultAtualizacaotxt)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(boletotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(chequetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(impostotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ultAtualizacaotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String colocarPonto(String s) {
        int index = s.indexOf(",");
        if (index > 3) {
            String sub = s.substring(0, index);
            int controle = 0;
            String temp = "";
            for (int x = 0; x < index + 1; x++) {
                if (x == index - 3) {
                    temp += '.';
                } else {
                    temp += sub.charAt(controle);
                    controle++;
                }
            }
            temp += s.substring(index);
            s = temp;
        }
        return s;
    }

    public void atualizar() {
        double boleto = new BoletoDAO().getValorEmAberto(),
                cheque = new ChequeDAO().getValorEmAberto(),
                imposto = new ImpostoDAO().getValorEmAberto();
        boleto = CDbl.CDblDuasCasas(boleto);
        cheque = CDbl.CDblDuasCasas(cheque);
        imposto = CDbl.CDblDuasCasas(imposto);
        double total = boleto + cheque + imposto;
        total = CDbl.CDblDuasCasas(total);

        String b, c, i, t;
        b = (Double.toString(boleto)).replaceAll("\\.", ",");
        c = (Double.toString(cheque)).replaceAll("\\.", ",");
        i = (Double.toString(imposto)).replaceAll("\\.", ",");
        t = (Double.toString(total)).replaceAll("\\.", ",");

        b = colocarPonto(b);
        c = colocarPonto(c);
        i = colocarPonto(i);
        t = colocarPonto(t);

        boletotxt.setText("R$ " + b);
        chequetxt.setText("R$ " + c);
        impostotxt.setText("R$ " + i);

        totaltxt.setText("R$ " + t);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField boletotxt;
    public javax.swing.JTextField chequetxt;
    public javax.swing.JTextField impostotxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField totaltxt;
    private javax.swing.JTextField ultAtualizacaotxt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        tick();
        while (isRunning) {
            try {
                Thread.sleep(60000);
                tick();
            } catch (InterruptedException ex) {
                Logger.getLogger(TotalEmAbertoFrm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        stop();

    }

    private void tick() {
        atualizar();
        ultAtualizacaotxt.setText(CDate.getHoraAtualPTBR());
    }

}
