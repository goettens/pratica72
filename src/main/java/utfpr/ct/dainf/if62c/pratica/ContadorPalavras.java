/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.ct.dainf.if62c.pratica;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Guilherme
 */
public class ContadorPalavras {
    
    private BufferedReader reader;

    public ContadorPalavras(String caminho) {
        if (caminho == null){
            System.err.println("Nenhum arquivo especificado");
            System.exit(1);
        }
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)));
        }catch(FileNotFoundException e){
            System.out.println("Arquivo inválido \n Cod. Erro:"+ e);
            System.exit(0);
        }   
    }
    
    public Map<String, Integer> getPalavras() throws IOException{
        Map<String, Integer> mapa = new HashMap<>();
        String line = this.reader.readLine();
        while (line != null){
            Pattern pattern = Pattern.compile("\\w+");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                String t = matcher.group();
                Integer qtd = mapa.get(t);
                if (qtd!=null){
                    mapa.put(t, qtd+1);
                }
                else{
                    mapa.put(t, 1);
                }
            }
            line = this.reader.readLine();
        }
        reader.close();
        return mapa;
    }     
}
