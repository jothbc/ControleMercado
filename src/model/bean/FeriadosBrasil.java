/*
 * Autor: Jonathan Comin Ribeiro
 */
package model.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jothbc
 * classe criada com o intuito de ter uma lista contendo todos os feriados brasileiros em formato Calendar
 * lembrando que janeiro é representado como 0 no calendar...
 * 
 */
public class FeriadosBrasil {
    List<Calendar> datas;

    public FeriadosBrasil() {
        datas= new ArrayList<>();
        Calendar dia = Calendar.getInstance();
        //2019
        dia.set(2019, 8, 7);
        datas.add(dia);
        dia.set(2019, 9, 12);
        datas.add(dia);
        dia.set(2019, 10, 2);
        datas.add(dia);
        dia.set(2019, 10, 15);
        datas.add(dia);
        dia.set(2019, 11, 25);
        datas.add(dia);
        //2020
        dia.set(2020, 3, 10);
        datas.add(dia);
        dia.set(2020, 3, 12);
        datas.add(dia);
        dia.set(2020, 3, 21);
        datas.add(dia);
        dia.set(2020, 4, 1);
        datas.add(dia);
        
        
    }
    
}
