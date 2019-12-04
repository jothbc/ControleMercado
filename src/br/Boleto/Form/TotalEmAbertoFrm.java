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
        jLabel6 = new javax.swing.JLabel();
        txt_folhas = new javax.swing.JTextField();
        folhas_pagas = new javax.swing.JCheckBox();

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

        jLabel6.setText("Folhas");

        txt_folhas.setEditable(false);
        txt_folhas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_folhasMouseClicked(evt);
            }
        });

        folhas_pagas.setBackground(new java.awt.Color(255, 255, 255));
        folhas_pagas.setToolTipText("Se selecionado indica que as folhas Já foram pagas.");
        folhas_pagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folhas_pagasActionPerformed(evt);
            }
        });

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
                        .addComponent(ultAtualizacaotxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(8, 8, 8)
                        .addComponent(folhas_pagas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_folhas, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(chequetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(impostotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_folhas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(folhas_pagas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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

    private void folhas_pagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folhas_pagasActionPerformed
        setVerificacaoCartaoPago();
    }//GEN-LAST:event_folhas_pagasActionPerformed

    private void txt_folhasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_folhasMouseClicked
        if (evt.getClickCount() == 2 && !folhas_pagas.isSelected()) {
            exibirValoresDaFolhaFuncionarios();
        }
    }//GEN-LAST:event_txt_folhasMouseClicked

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
        Calendar calendario = Calendar.getInstance();
        //não é preciso decrementar o mes ja que o Calendar trabalha com janeiro = 0
        folhas_pagas.setSelected(new CartaoPontoDAO().folhaPago(calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR)));
        double folha = 0;
        if (!folhas_pagas.isSelected()) {
            folha = verFolhas(calendario);
        }

        double boleto = new BoletoDAO().getValorEmAberto(),
                cheque = new ChequeDAO().getValorEmAberto(),
                imposto = new ImpostoDAO().getValorEmAberto();
        boleto = CDbl.CDblDuasCasas(boleto);
        cheque = CDbl.CDblDuasCasas(cheque);
        imposto = CDbl.CDblDuasCasas(imposto);
        double total = boleto + cheque + imposto + folha;
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
        txt_folhas.setText("R$ " + colocarPonto(Double.toString(folha).replaceAll("\\.", ",")));

        totaltxt.setText("R$ " + t);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField boletotxt;
    public javax.swing.JTextField chequetxt;
    private javax.swing.JCheckBox folhas_pagas;
    public javax.swing.JTextField impostotxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField totaltxt;
    public javax.swing.JTextField txt_folhas;
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

    public double verFolhas(Calendar calendario) {
        double valor = 0;
        area.setText("");
//        for (Funcionario f : funcionarios) {
//            int reg = new CartaoPontoDAO().lancado(f, calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR));
//            if (f.getSalario() != 0 && reg != -1) {
////                CartaoPonto cartao = new CartaoPontoDAO().getLancado2(f, calendario.get(Calendar.MONTH) + 1, calendario.get(Calendar.YEAR));
////                valor += calcularAproximacao(f, cartao, calendario);
//            }
//        }
        return CDbl.CDblDuasCasas(0);
    }

    private double calcularAproximacao(Funcionario funcionario, CartaoPonto cartao, Calendar calendario) {
        area.append("\n#######Calculando ######");
        area.append("\nCodigo: " + funcionario.getCodigo());
        area.append("\nFuncionario :" + funcionario.getNome());
        area.append("\nSalario Base: " + CDbl.CDblDuasCasasString(funcionario.getSalario()));
        variaveis.stream().filter((f) -> (f.getId() == 5)).forEachOrdered((f) -> {
            area.append("\n" + f.getCodigo() + " Horas Extra: " + cartao.getExtra());
        });
        variaveis.stream().filter((f) -> (f.getId() == 2)).forEachOrdered((f) -> {
            area.append("\n" + f.getCodigo() + " Horas Falta: " + cartao.getFalta());
        });
        variaveis.stream().filter((f) -> (f.getId() == 3)).forEachOrdered((f) -> {
            area.append("\n" + f.getCodigo() + " Horas Noturna: " + cartao.getNoturna());
        });
        Calendar cal = calendario;
        int domingos = 0;
        int feriados_do_mes = 0;
        int feriados_trabalhados = 0;

        for (FeriadosBrasil feriado : feriados) {
            if (feriado.getAno() == cartao.getAno() && feriado.getMes() == cartao.getMes()) {
                feriados_do_mes++;
                String temp = (String) cartao.getTabela().getValueAt(feriado.getDia() - 1, 7);
                String situacao = "";
                try {
                    situacao = (String) cartao.getTabela().getValueAt(feriado.getDia() - 1, 0);
                } catch (Exception e) {
                    situacao = "";
                }
                //não pode ser nulo, nen horas trabalhadas = 00:00 nem Atestado
                if (temp != null && !"00:00".equals(temp) && !"A".equals(situacao)) {
                    variaveis.stream().filter((f) -> (f.getId() == 4)).forEachOrdered((f) -> {
                        area.append("\n" + f.getCodigo() + " Feriado Trabalhado: " + feriado + " +R$ 72,00");
                    });
                    feriados_trabalhados++;
                }
            }
        }
        cal.add(Calendar.MONTH, -1); //ajustado o mes para -1
        int totalDias = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int x = 1; x <= totalDias; x++) {
            cal.set(Calendar.DAY_OF_MONTH, x);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                domingos++;
            }
        }
        for (int x = 0; x < cartao.getTabela().getRowCount(); x++) {
            String temp = (String) cartao.getTabela().getValueAt(x, 0);
            String hora_trabalhada_naquele_dia = (String) cartao.getTabela().getValueAt(x, 7);
            if (temp.equals("F") || (hora_trabalhada_naquele_dia != null && !"00:00".equals(hora_trabalhada_naquele_dia) && CDate.horaPDecimal(hora_trabalhada_naquele_dia) < CDate.horaPDecimal(cartao.getJornada()) / 2)) {
                int dia = x + 1;
                variaveis.stream().filter((f) -> (f.getId() == 1)).forEachOrdered((f) -> {
                    area.append("\n" + f.getCodigo() + " " + f.getDescricao() + ": " + cartao.getJornada() + "  Referente ao dia: " + dia);
                });
                if (temp.equals("F")) {
                    area.append("\nMotivo: Falta");
                } else if (CDate.horaPDecimal(hora_trabalhada_naquele_dia) < CDate.horaPDecimal(cartao.getJornada()) / 2) {
                    area.append("\nMotivo: Faltou mais de meio período de Trabalho. (" + hora_trabalhada_naquele_dia + ")");
                }
            }
        }

        domingos += feriados_do_mes;

        double jornada = CDate.horaPDecimal(cartao.getJornada());
        double horas_mes = jornada * 30;

        double valor_hora_extra;
        try {
            double valor_hora = funcionario.getSalario() / horas_mes;
            valor_hora_extra = valor_hora * 1.55;
            double total = valor_hora * horas_mes;
            double extra = 0;
            try {
                extra = CDate.horaPDecimal(cartao.getExtra()) + CDate.horaPDecimal(cartao.getNoturna());
                extra *= valor_hora_extra;
            } catch (Exception e) {
            }
            double dsr = 0;
            try {
                dsr = CDbl.CDblDuasCasas((extra / (totalDias - domingos)) * domingos);
            } catch (Exception i) {
            }

            //rever o código daqui pra baixo e acima tbm claro
            total += extra + dsr + 72 * feriados_trabalhados; //conta como se o funcionario estivesse trabalhado no feriado
            //total -= (double) txt_descontos.getValue(); sem descontos por enquanto
            double inss = total * ((9 / (double) 100)); //9% de INSS
            inss = CDbl.CDblDuasCasas(inss);
            total -= inss;
            area.append("\nVALOR DSR: " + CDbl.CDblDuasCasas(dsr));
            area.append("\nVALOR INSS: " + CDbl.CDblDuasCasas(inss));
            area.append("\nSALARIO TOTAL : " + CDbl.CDblDuasCasas(total));
            return CDbl.CDblDuasCasas(total);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    private void setVerificacaoCartaoPago() {
        Calendar c = Calendar.getInstance();
        if (!new CartaoPontoDAO().alterarStatusFolhaPago(folhas_pagas.isSelected(), c.get(Calendar.MONTH), c.get(Calendar.YEAR))) {
            JOptionPane.showMessageDialog(null, "Problemas ao alterar no banco de dados essa informação.");
        } else {
            tick();
        }
    }

    private void exibirValoresDaFolhaFuncionarios() {
        JFrame frame = new JFrame("Folha");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        area.setSize(200, 200);
        frame.add(area);
        frame.setVisible(true);
    }
}
