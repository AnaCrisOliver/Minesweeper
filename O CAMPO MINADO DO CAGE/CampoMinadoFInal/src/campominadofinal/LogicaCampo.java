
package campominadofinal;

import java.util.Random;

/**
 *
 * @author Ana Cristina
 */
public class LogicaCampo {
 
    
    public int[][] campoMinado = new int[9][9];
    
    
    public LogicaCampo() {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {  
                this.campoMinado[i][j] = 0;
            } 
        }
    }//Construtor
    
    public void insereMinasNoCampo()
    {
        int sortI = 0;
        int sortJ = 0;
        
        Random gerador = new Random();
        for(int i=0;i<10;i++) //Adcionando as minas ao campo randomicamente
        {
            sortI = gerador.nextInt(9);
            sortJ = gerador.nextInt(9);
            if(campoMinado[sortI][sortJ] == 10)
            {
                i--;
            }else{
                campoMinado[sortI][sortJ] = 10;
            }
        }
        
    }
    public void insereNumerosNoCampo()
    {
        int contMinasAoRedor = 0;
        
        for(int i=0;i<9;i++)//Inserindo os numeros nos campos onde ha alguma mina por perto
        {
            /*Para o tratamento dos campos localizados nas bordas do campo minado, existem 8 cases:
            *Case Y = primeiro quadrado do campo
            *Case X = ultimo quadrado inferior a esquerda do campo
            *Case B = ultimo quadrado inferior a direta do campo
            *Case A = primeiro quadrado superior a direita do campo
            *Case E = quadrados á direita de Y e á esquerda de A
            *Case Z = quadrados Y e X
            *Case C = quadrados entre A e B
            *Case D = quadrados entre X e B
             */

            for(int j=0;j<9;j++)
            {
                if(campoMinado[i][j] != 10)
                {
                    if (i == 0)//se for case Y, E, ou A
                    {
                        contMinasAoRedor = 0;
                        if (j == 0) //Se for o case Y, verifica cada posicao ao redor e add +1 ao contador caso haja mina
                        {

                            contMinasAoRedor = 0;
                            if (campoMinado[0][1] == 10)
                            {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[1][1] == 10)
                            {

                                contMinasAoRedor++;
                            }
                            if (campoMinado[1][0] == 10)
                            {

                                contMinasAoRedor++;
                            }
                            campoMinado[i][j] = contMinasAoRedor;
                            contMinasAoRedor = 0;
                        } else if (j == 8)//Se for o case A, verifica cada posicao ao redor e add +1 ao contador caso haja mina
                        {
                            contMinasAoRedor = 0;
                            if (campoMinado[0][7] == 10) {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[1][7] == 10) {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[1][8] == 10) {
                                contMinasAoRedor++;
                            }
                            campoMinado[i][j] = contMinasAoRedor;
                            contMinasAoRedor = 0;
                        } else if (j > 0 && j < 8)//Se for o case E, verifica cada posicao ao redor e add +1 ao contador caso haja mina
                        {
                            contMinasAoRedor = 0;
                            if (campoMinado[i][j + 1] == 10) {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i + 1][j + 1] == 10) {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i + 1][j] == 10) {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i + 1][j - 1] == 10) {
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i][j - 1] == 10) {
                                contMinasAoRedor++;
                            }
                            campoMinado[i][j] = contMinasAoRedor;
                            contMinasAoRedor = 0;
                        }
                    } else if (i > 0)
                    { //General case
                        if(i < 8 && j > 0 && j < 8)
                        {
                            contMinasAoRedor = 0;
                            if (campoMinado[i][j - 1] == 10) {//campo a esquerda
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i - 1][j]== 10) {//campo superior
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i - 1][j + 1]== 10) {//campo superior a direita
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i - 1][j - 1] == 10) {//campo superior a esquerda
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i][j + 1] == 10) { //campo a direita
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i + 1][j - 1] == 10) { //campo inferior a esquerda
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i + 1][j] == 10) { //campo inferior
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i + 1][j + 1] == 10) { //campo inferior a direita
                                contMinasAoRedor++;
                            }
                            campoMinado[i][j] = contMinasAoRedor;
                            contMinasAoRedor = 0;
                        }
                        if(j == 0)//cases x e z
                        {
                            if(i == 8) //case X
                            {
                                contMinasAoRedor = 0;
                                if (campoMinado[i][j+1] == 10) {//campo a direita
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j] == 10) {//campo superior
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j + 1] == 10) {//campo superior a direita
                                    contMinasAoRedor++;
                                }
                                campoMinado[i][j]= contMinasAoRedor;
                                contMinasAoRedor = 0;
                            }
                            if(i > 0 && i < 8) //case Z
                            {
                                contMinasAoRedor = 0;
                                if (campoMinado[i][j+1] == 10) {//campo a direita
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j] == 10) {//campo superior
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j + 1]== 10) {//campo superior a direita
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i + 1][j] == 10) {//campo inferior
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i + 1][j + 1] == 10) {//campo inferior a direita
                                    contMinasAoRedor++;
                                }
                                campoMinado[i][j] = contMinasAoRedor;
                                contMinasAoRedor = 0;
                            }
                        }
                        if(j == 8)//Cases B e C
                        {
                            if(i == 8) //Case B
                            {
                                contMinasAoRedor = 0;
                                if (campoMinado[i][j-1] == 10) {//campo a esquerda
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j] == 10) {//campo superior
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j - 1] == 10) {//campo superior a esquerda
                                    contMinasAoRedor++;
                                }
                                campoMinado[i][j] = contMinasAoRedor;
                                contMinasAoRedor = 0;
                            }
                            if( i > 0 && i < 8) //Case C
                            {
                                contMinasAoRedor = 0;
                                if (campoMinado[i][j-1] == 10) {//campo a esquerda
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j] == 10) {//campo superior
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i - 1][j -1] == 10) {//campo superior a esquerda
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i + 1][j] == 10) {//campo inferior
                                    contMinasAoRedor++;
                                }
                                if (campoMinado[i + 1][j - 1] == 10) {//campo inferior a esquerda
                                    contMinasAoRedor++;
                                }
                                campoMinado[i][j] = contMinasAoRedor;
                                contMinasAoRedor = 0;
                            }
                        }
                        if(i == 8 && j > 0 && j < 8)//Case D
                        {
                            contMinasAoRedor = 0;
                            if (campoMinado[i][j-1] == 10) {//campo a esquerda
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i - 1][j] == 10) {//campo superior
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i - 1][j - 1] == 10) {//campo superior a esquerda
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i][j+1] == 10) {//campo a direita
                                contMinasAoRedor++;
                            }
                            if (campoMinado[i - 1][j + 1] == 10) {//campo superior a direita
                                contMinasAoRedor++;
                            }
                            campoMinado[i][j] = contMinasAoRedor;
                            contMinasAoRedor = 0;
                        }
                    }
                }
            }
        }
    }
     public void print()
    {
        System.out.println("-------------GABARITO-------------");
        for(int i=0; i<9;i++)//Imprimindo o campo minado pronto (as respostas)
        {
            System.out.println("\t");
            for(int j=0; j<9; j++)
            {

                System.out.print(campoMinado[i][j]);
                System.out.print("    ");
            }
        }

    }
     
     public int verificaConteudoDaPosicao(int linha, int coluna)
     {
         if(campoMinado[linha][coluna] == 0)//Se nao tiver nada
         {
              return 0;
         }                
         if(campoMinado[linha][coluna] == 10)//Se for uma mina
         {
            return 10;
         }
         return campoMinado[linha][coluna];//Se tiver um numero
     }
    
     public void verificaEspacosVazios(int linha,int coluna)
     {
         int j = coluna;
         for(int i=linha;i<9;i++)
         {
             while(campoMinado[i][j] == 0)
             {
            
                //Retornar os botoes aqui
                j++;
             
             }
         }
         
     }

}
