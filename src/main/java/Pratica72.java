import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utfpr.ct.dainf.if62c.pratica.ContadorPalavras;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * IF62C - Fundamentos de Programação 2
 * 
 * Template de projeto de programa Java usando Maven.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Pratica72 {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o caminho total do arquivo .txt, iclusive a extenção");
        String caminho = entrada.nextLine();
        
        ContadorPalavras conta = new ContadorPalavras(caminho);
        
        Map<String, Integer> mapa = conta.getPalavras();
        
        class ParaLista{
            String p;
            Integer n;
            
            public ParaLista() {
            }

            public ParaLista(String p, Integer n) {
                this.p = p;
                this.n = n;
            }

            public String getP() {
                return p;
            }

            public void setP(String p) {
                this.p = p;
            }

            public Integer getN() {
                return n;
            }

            public void setN(Integer n) {
                this.n = n;
            }
        }
               
        class Comp implements Comparator<ParaLista>{

            int compInt(ParaLista a, ParaLista b){
                return -(a.getN() - b.getN());
            }
            int compString (ParaLista a, ParaLista b){
                return a.getP().compareTo(b.getP());
            }            
            @Override
            public int compare(ParaLista o1, ParaLista o2) {
                return compInt(o1,o2)!=0? compInt(o1,o2):compString(o1,o2);
            }  
        }
        
        List <ParaLista> lista = new ArrayList<>();
        
        for (Map.Entry<String,Integer> m : mapa.entrySet()){
            ParaLista p = new ParaLista(m.getKey(),m.getValue());
            lista.add(p);
        }
        
        Comp cmp = new Comp();
        Collections.sort(lista,cmp);
        String saida = caminho+".out";
        System.out.println("Salvo como: " + saida);
        
        BufferedWriter arq = new BufferedWriter(new FileWriter(saida));
        
        for (ParaLista l : lista){
            arq.write(l.getP() + "," + l.getN());
            arq.newLine();
        }
        
        arq.close();
    }
}
