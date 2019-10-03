/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominadofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
/**
 *
 * @author Ana Cristina
 */
class CampoMinado extends JFrame implements ActionListener{

    //Declaracao de variaveis que irao receber as imagens que serao utilizadas
    public final Icon iconMina = new javax.swing.ImageIcon(getClass().getResource("NC 1.png"));
    public final Icon flagIcon = new javax.swing.ImageIcon(getClass().getResource("flag.png"));
    public final Icon endIcon = new javax.swing.ImageIcon(getClass().getResource("end.png"));
    public final Icon icon1 = new javax.swing.ImageIcon(getClass().getResource("1.png"));
    public final Icon icon2 = new javax.swing.ImageIcon(getClass().getResource("2.png"));
    public final Icon icon3 = new javax.swing.ImageIcon(getClass().getResource("3.png"));
    public final Icon icon4 = new javax.swing.ImageIcon(getClass().getResource("4.png"));
    public final Icon icon5 = new javax.swing.ImageIcon(getClass().getResource("5.png"));
    public final Icon icon6 = new javax.swing.ImageIcon(getClass().getResource("6.png"));
    public final Icon icon7 = new javax.swing.ImageIcon(getClass().getResource("7.png"));
    public final Icon icon8 = new javax.swing.ImageIcon(getClass().getResource("8.png"));
    public final Icon word = new javax.swing.ImageIcon(getClass().getResource("word.png"));
    public final Icon iconAjuda = new javax.swing.ImageIcon(getClass().getResource("ajuda.png"));
    public final Icon noIcon = new javax.swing.ImageIcon();
       
    //Contadores
    public int contFlags = 10;
    public String contBandeiras;
    int contClickMouse = 0;
    public int contButtonSelected = 0;
    int cont =0;
    
    //Containers de interface
    JTextField contMinasShow = new JTextField("    " + contFlags + "    ");
    JLabel labelPanico = new JLabel("Panic!");
    JInternalFrame quadroComBotoes = new JInternalFrame();
    JPanel panel2 = new JPanel();
    JToggleButton[][] botoes =  new JToggleButton [9][9];
    JButton botaoResetaCampo = new JButton();
    JButton botaoAjuda = new JButton();
    JLabel panelBotaoAjuda = new JLabel();
    LogicaCampo campoMinado = new LogicaCampo();    
    JPanel panel = new JPanel();
    
    //Matriz auxiliar para verificar clicks com o botao direito
    int[][] clickDireita = new int[9][9];
    public boolean ocupado = false; 
       
    MouseHandler acaoDoMouse = new MouseHandler();
    MouseBotaoReinicia acaoMouseBotaoReinicia = new MouseBotaoReinicia();
    MouseModoPanico acaoModoPanico = new MouseModoPanico();
    MouseBotaoAjuda acaoBotaoAjuda = new MouseBotaoAjuda();
        
    public int coluna;
    public int linha;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    CampoMinado() {
        super("Campo Minado 2.0");
        initComponents();
    } //Construtor

    public void setIconMina() {
        botoes[linha][coluna].setIcon(iconMina);
    }    
    public void setIcon1() {
       botoes[linha][coluna].setIcon(icon1);
    }
    public void setIcon2() {
       botoes[linha][coluna].setIcon(icon2);
    }
    public void setIcon3() {
        botoes[linha][coluna].setIcon(icon3);
    }
    public void setIcon4() {
        botoes[linha][coluna].setIcon(icon4);
    }
    public void setIcon5() {
        botoes[linha][coluna].setIcon(icon5);
    }
    public void setIcon6() {
        botoes[linha][coluna].setIcon(icon6);
    }
    public void setIcon7() {
        botoes[linha][coluna].setIcon(icon7);
    }
    public void setIcon8() {
       botoes[linha][coluna].setIcon(icon8);
    }
    public void setFlagIcon() {
        this.ocupado = true;
       botoes[linha][coluna].setIcon(flagIcon);
    }
    public void setNoIcon() {
        ocupado = false;
        botoes[linha][coluna].setIcon(noIcon);
    }

    public String retornaContBandeiras()
    {
        if(ocupado == false)
        {
            //setFlagIcon();
            contFlags++;
            contBandeiras = String.valueOf(contFlags++);                    
        }
        else if(ocupado == true){
           // setNoIcon();
            contFlags--;
            contBandeiras = String.valueOf(contFlags++);
        }
        return contBandeiras;
    }
    public boolean verificaSeVenceu() //se false, nao venceu ainda
    {
        boolean contPerdeu = true;
        for(int i=0;i<9;i++) 
        {
           for(int j=0;j<9;j++)
           {
               if(campoMinado.campoMinado[i][j] > 0 && campoMinado.campoMinado[i][j] < 10)
               {
                   if(!botoes[i][j].isSelected())
                   {
                       contPerdeu = false;
                   }
               }
           }
        }
        return contPerdeu;
    }
    public void minasFicamVerdesAoVencer(){
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(campoMinado.campoMinado[i][j] == 10)
                    botoes[i][j].setBackground(new java.awt.Color(50, 205, 50));
                  
            }
        }
        
        
    }
    public void minasFicamVermelhasAoPerder(){
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(campoMinado.campoMinado[i][j] == 10)
                    botoes[i][j].setBackground(Color.red);
                  
            }
        }
        
        
    }
    public void ativaUmBotaoNumeroSemClicar(int i, int j)
    {
        switch(campoMinado.campoMinado[i][j])
        {
            case 1:
                botoes[i][j].setIcon(icon1);
                break;
            case 2:
                botoes[i][j].setIcon(icon2);
                break;
            case 3:
                botoes[i][j].setIcon(icon3);
                break;
            case 4:
                botoes[i][j].setIcon(icon4);
                break;
            case 5:
                botoes[i][j].setIcon(icon5);
                break;
            case 6:
                botoes[i][j].setIcon(icon6);
                break;
            case 7:
                botoes[i][j].setIcon(icon7);
                break;
            case 8:
                botoes[i][j].setIcon(icon8);
                break;
        }
    }
    public void ativaUmBotaoNumero(int i, int j){
    if(!botoes[i][j].isSelected())
    {
        botoes[i][j].doClick();
         switch(campoMinado.campoMinado[i][j])
        {
            case 1:
                botoes[i][j].setIcon(icon1);
                break;
            case 2:
                 botoes[i][j].setIcon(icon2);
                 break;
            case 3:
                 botoes[i][j].setIcon(icon3);
                 break;
            case 4:
                  botoes[i][j].setIcon(icon4);
                  break;
            case 5:
                 botoes[i][j].setIcon(icon5);
                  break;
            case 6:
                 botoes[i][j].setIcon(icon6);
                 break;
            case 7:
                 botoes[i][j].setIcon(icon7);
                 break;
            case 8:
                 botoes[i][j].setIcon(icon8);
                 break;   
        }
   }
}
    public void ativaTodosOsBotoes() 
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0; j<9;j++)
           {
               if(!botoes[i][j].isSelected())
               {
                                       
                    if(campoMinado.campoMinado[i][j] ==10)//Tem mina
                    {
                         botoes[i][j].doClick();
                         botoes[i][j].setIcon(iconMina);                           
                    }else
                    {
                       ativaUmBotaoNumero(i,j);
                    }
                }                   
            }  
        }
    }

    public void lookTop(int a,int b)
    {
        a--;
        if(a < 0)
        {
            return;
        }
        if(!botoes[a][b].isSelected())
        {   
            if(campoMinado.campoMinado[a][b] == 10){
                return;
            }
            if(campoMinado.campoMinado[a][b] != 0)
            {           
                ativaUmBotaoNumero(a,b);
            }
            else if(campoMinado.campoMinado[a][b] == 0){
                botoes[a][b].doClick();                
                lookRight(a,b);
                lookLeft(a,b);
                lookDown(a,b);
                lookTop(a,b);
            }            
        }

    }
    public void lookRight(int a,int b){
        b++;
        if(b > 8)
        {
            return;
        }
        if(!botoes[a][b].isSelected())
        {   
            if(campoMinado.campoMinado[a][b] == 10){
                return;
            }
            if(campoMinado.campoMinado[a][b] != 0)
            {           
                ativaUmBotaoNumero(a,b);
            }
            else if(campoMinado.campoMinado[a][b] == 0){
                botoes[a][b].doClick();
                lookLeft(a,b);
                lookDown(a,b);
                lookTop(a,b);
                lookRight(a,b);
            }            
        }
    }
    public void lookLeft(int a,int b){
        b--;
        if(b < 0)
        {
            return;
        }
        
        if(!botoes[a][b].isSelected())
        {   
            if(campoMinado.campoMinado[a][b] == 10){
                return;
            }
            if(campoMinado.campoMinado[a][b] != 0)
            {           
                ativaUmBotaoNumero(a,b);
            }
            else if(campoMinado.campoMinado[a][b] == 0){
                botoes[a][b].doClick();
                lookDown(a,b);
                lookTop(a,b);
                lookRight(a,b);
                lookLeft(a,b);
            }            
        }
    }
    public void lookDown(int a,int b)
    {
        a++;
        if(a > 8)
        {
            return;
        }
        if(!botoes[a][b].isSelected())
        {  
           
            if(campoMinado.campoMinado[a][b] == 10){
                return;
            }
            if(campoMinado.campoMinado[a][b] != 0)
            {           
                ativaUmBotaoNumero(a,b);
            }
            else if(campoMinado.campoMinado[a][b] == 0){
                botoes[a][b].doClick();
                lookLeft(a,b);
                lookTop(a,b);
                lookRight(a,b);
                lookDown(a,b);
            }            
        }
    }

    public void ativaOsBotoesVaziosAoRedor()
    {
       int i=linha;
       int j=coluna;
       lookTop(i,j);
       lookRight(i,j);
       lookLeft(i,j);
       lookDown(i,j);
    }
    void initComponents(){ 
        
        //Geracao da matriz, insercao das minas e tratamento das posicoes
                //setLocationRelativeTo(null);
                campoMinado.insereMinasNoCampo();
                campoMinado.insereNumerosNoCampo();
                campoMinado.print();             

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(700,700);
                setResizable(false);  
                
                setLayout(new BorderLayout(20,0));              
                
                panel.setSize(400,400);
                panel.setLayout(new GridLayout( 9, 9, 5, 5));
                panel.setBackground(new java.awt.Color(102, 0, 0));                
                
                
                JLabel tituloCampo = new JLabel("CAMPO MINADO DO CAGE");
                tituloCampo.setForeground(new java.awt.Color(255, 255, 255));
                tituloCampo.setFont(new java.awt.Font("Cabin Sketch", 1, 20));//1-fonte,2-negrito,3-tamanho da fonte
                
                panel2.setLayout(new GridLayout( 2, 3, 5, 5));
                panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
               // panel2.setLayout(new BorderLayout(20,50));
                panel2.setBackground(new java.awt.Color(102, 0, 0));
                panel2.setSize(100,100);
                //panel2.add(tituloCampo,BorderLayout.LINE_START); 
                panel2.add(tituloCampo); 
                
                botaoAjuda.addMouseListener(acaoBotaoAjuda);
                botaoResetaCampo.setPreferredSize(new Dimension(10, 10));
                botaoResetaCampo.setMaximumSize(new Dimension(10, 10));
                botaoResetaCampo.setIcon(iconMina);
                botaoResetaCampo.addMouseListener(acaoMouseBotaoReinicia);//adcionar aqui acao do mouse par reiniciar
                botaoResetaCampo.setBackground(new java.awt.Color(70, 70, 70));
                botaoResetaCampo.setForeground(new java.awt.Color(230, 230, 230));
                botaoResetaCampo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 51), null, null));
                
                contMinasShow.setBackground(new java.awt.Color(102, 0, 0));
                contMinasShow.setForeground(new java.awt.Color(255, 255, 255));
                contMinasShow.setFont(new java.awt.Font("Ace Sans Demo", 1, 20));
               
                JLabel labelContadorMinas = new JLabel("                   MINAS:");
                labelContadorMinas.setFont(new java.awt.Font("Ace Sans Demo", 1, 18));
                labelContadorMinas.setForeground(new java.awt.Color(255, 255, 255));
              //  panel2.add(labelContadorMinas,BorderLayout.CENTER);
               // panel2.add(botaoResetaCampo,BorderLayout.CENTER);                
                panel2.add(botaoResetaCampo);
                panel2.add(labelContadorMinas);
                contMinasShow.setName("MINAS");
                botaoAjuda.setText("Ajuda");
                botaoAjuda.setForeground(new java.awt.Color(255, 255, 255));
                botaoAjuda.setBackground(new java.awt.Color(51, 51, 51));
                botaoAjuda.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 12)); // NOI18N
                botaoAjuda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
               // panel2.add(contMinasShow,BorderLayout.LINE_END);
                panel2.add(contMinasShow);
 
               quadroComBotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                quadroComBotoes.setBackground(new java.awt.Color(102, 0, 0));
                quadroComBotoes.setVisible(true);
                javax.swing.GroupLayout quadroComBotoesLayout = new javax.swing.GroupLayout(quadroComBotoes.getContentPane());
                quadroComBotoes.getContentPane().setLayout(quadroComBotoesLayout);
                
                 quadroComBotoesLayout.setHorizontalGroup(
                    quadroComBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quadroComBotoesLayout.createSequentialGroup()
                        
                        .addComponent(botaoAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(tituloCampo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGap(53, 53, 53)
                        .addComponent(botaoResetaCampo)
                        .addGap(38, 38, 38)
                        //.addComponent(contMinasShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contMinasShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                quadroComBotoesLayout.setVerticalGroup(
                        
                        
                        quadroComBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quadroComBotoesLayout.createSequentialGroup()
                            .addContainerGap(44, Short.MAX_VALUE)
                            .addGroup(quadroComBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(botaoResetaCampo)
                                .addComponent(tituloCampo)
                                .addComponent(contMinasShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quadroComBotoesLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(botaoAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))

                );   
                add(quadroComBotoes,BorderLayout.PAGE_START);
                for(int i=0;i<9;i++) 
                {
                    for(int j=0;j<9;j++)
                    {
                        final int curRow = i;
                        final int curCol = j;
                        botoes[i][j] = new JToggleButton();
                        botoes[i][j].setPreferredSize(new Dimension(15, 15));
                        clickDireita[i][j] = 0;
                        botoes[i][j].addMouseListener(acaoDoMouse);
                        botoes[i][j].setBackground(new java.awt.Color(30, 30, 30));
                        botoes[i][j].setForeground(new java.awt.Color(230, 230, 230));
                        botoes[i][j].addKeyListener(enter);
                        botoes[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 51), null, null));
                        botoes[i][j].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                           switch (e.getKeyCode()) {
                           case KeyEvent.VK_UP:
                              if (curRow > 0){
                                 botoes[curRow - 1][curCol].requestFocus();
                                 botoes[curRow - 1][curCol].setBorderPainted(true);
                                 botoes[curRow - 1][curCol].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
                                 botoes[curRow][curCol].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 51), null, null));
                                }
                                 
                              break;
                           case KeyEvent.VK_DOWN:
                              if (curRow < botoes.length - 1){
                                 botoes[curRow + 1][curCol].requestFocus();
                                 botoes[curRow + 1][curCol].setBorderPainted(true);
                                 botoes[curRow + 1][curCol].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
                                 botoes[curRow][curCol].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 51), null, null));
                              }
                              break;
                           case KeyEvent.VK_LEFT:
                              if (curCol > 0){
                                botoes[curRow][curCol - 1].requestFocus();
                                botoes[curRow][curCol - 1].setBorderPainted(true);
                                botoes[curRow][curCol - 1].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
                                botoes[curRow][curCol].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 51), null, null));
                              }
                              break;
                           case KeyEvent.VK_RIGHT:
                              if (curCol < botoes[curRow].length - 1){
                                 botoes[curRow][curCol + 1].requestFocus();
                                 botoes[curRow][curCol + 1].setBorderPainted(true);
                                 botoes[curRow][curCol + 1].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
                                 botoes[curRow][curCol].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 51), null, null));
                              }
                              break;
                           default:
                              break;
                           }
                        }
                        });                       
                        
                        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                        panel.add(botoes[i][j]);
                                                                
                    }
                }
                labelPanico.addMouseListener(acaoModoPanico);               
                JLabel contMinasNaTela = new JLabel("");
                add(panel,BorderLayout.CENTER);
                labelPanico.setSize(200,200);
                panelBotaoAjuda.setSize(100,100);
                
                add(labelPanico,BorderLayout.PAGE_END);
                panelBotaoAjuda.setVisible(false);

        
    } //Inicializa todos os componentes. É chamado pelo construtor
    
    public static void main(String[] args) {
        new CampoMinado().setVisible(true);
    }
   
    public void reinicia() //Reinicia o jogo
    {
        
         JOptionPane.showMessageDialog(null,"Aguarde! O Jogo irá reiniciar!","Aguarde",JOptionPane.INFORMATION_MESSAGE,noIcon);
         for(int i=0;i<9;i++)//"Desclicando" os botoes que foram clicados
        {
            for(int j=0; j<9;j++)
           {
              
              if(clickDireita[i][j] == 1)
              {
                  clickDireita[i][j] = 0;
              }
              if(botoes[i][j].isSelected())
              {
                botoes[i][j].doClick();
                linha=i;
                coluna = j;                
              } 
              botoes[i][j].setBackground(new java.awt.Color(30, 30, 30));
              botoes[i][j].setIcon(noIcon);
           }
        }
         for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {  
                campoMinado.campoMinado[i][j] = 0;
            } 
        }
         campoMinado.insereMinasNoCampo();
         campoMinado.insereNumerosNoCampo();
         campoMinado.print();
         contFlags = 10; 
         contMinasShow.setText("    " + contFlags + "   ");
         botaoResetaCampo.setIcon(iconMina);
    }
    
    public KeyListener enter = new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
         if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            for(int i=0;i<9;i++)
            {
                for(int j=0; j<9;j++)
                {  
                   if(e.getSource() == botoes[i][j])
                   {
                       linha = i;
                       coluna = j;
                      
                       botoes[linha][coluna].setBorderPainted(true);
                       botoes[linha][coluna].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 255, 255), null, null));
                       botoes[linha][coluna].doClick();
                   }
                }
            }        
            
            if(verificaSeVenceu())
                {
                    contButtonSelected++;
                    ativaTodosOsBotoes();
                    JLabel ganhouImagem = new JLabel("           Você ganhou!");
                    ganhouImagem.setIcon(iconMina);
                    JOptionPane.showMessageDialog(null,"Parabéns, você ganhou!!","Vencedor",JOptionPane.INFORMATION_MESSAGE,iconMina);
                    reinicia();
                }
                
                if(campoMinado.campoMinado[linha][coluna] == 0)//Nao tem nada
                {
                    setNoIcon();
                    ativaOsBotoesVaziosAoRedor();
                    contButtonSelected++;
                }else if(campoMinado.campoMinado[linha][coluna]== 10)//Tem uma mina
                {
                    setIconMina();
                    contButtonSelected++;
                    ativaTodosOsBotoes();
                    JLabel perdeuImagem = new JLabel("           Você perdeu!");
                    perdeuImagem.setIcon(endIcon);
                    JOptionPane.showMessageDialog(null,"Você perdeu!","Loser",JOptionPane.INFORMATION_MESSAGE,endIcon);                         
                    reinicia();

                }else{//Tem um numero
                    ativaUmBotaoNumeroSemClicar(linha, coluna);
                }
         }
      }
   }; //Controla os eventos do teclado
  
    class MouseBotaoAjuda extends MouseAdapter
    {
        @Override
         public void mouseClicked(MouseEvent event)
         {
             if(event.isMetaDown() == false) {                
                JOptionPane.showMessageDialog(null,"","Ajuda",JOptionPane.INFORMATION_MESSAGE,iconAjuda);
             }
         }
    }
    class MouseHandler extends MouseAdapter {           
  
              
        @Override
       
        public void mouseClicked(MouseEvent event) 
        {
         

          if(event.isMetaDown() == false) //se for com o botao esquerdo
          {
            contClickMouse = event.getClickCount();
              if(event.getClickCount() < 2)
              {
                    for(int i=0;i<9;i++)
                    {
                        for(int j=0; j<9;j++)
                        {
                            if(event.getSource() == botoes[i][j])
                            {
                                linha = i;
                                coluna = j;

                            }                       
                        }                   
                    }  
                    contClickMouse = event.getClickCount();
                    if(verificaSeVenceu())
                    {
                        contButtonSelected++;
                        minasFicamVerdesAoVencer();
                        ativaTodosOsBotoes();
                        JLabel ganhouImagem = new JLabel("           Você ganhou!");
                        ganhouImagem.setIcon(iconMina);
                        JOptionPane.showMessageDialog(null,"Parabéns, você ganhou!!","Vencedor",JOptionPane.INFORMATION_MESSAGE,iconMina);
                        reinicia();
                    }else{
                        if(campoMinado.campoMinado[linha][coluna] == 0)
                        {
                            setNoIcon();
                            ativaOsBotoesVaziosAoRedor();
                            contButtonSelected++;
                        }else if(campoMinado.campoMinado[linha][coluna] == 10){
                            setIconMina();
                            minasFicamVermelhasAoPerder();
                            botaoResetaCampo.setIcon(endIcon);
                            contButtonSelected++;                         
                            ativaTodosOsBotoes();
                            JLabel perdeuImagem = new JLabel("           Você perdeu!");
                            perdeuImagem.setIcon(endIcon);
                            JOptionPane.showMessageDialog(null,"Você perdeu!","Loser",JOptionPane.INFORMATION_MESSAGE,endIcon);                         
                            reinicia();
                        }else{
                            ativaUmBotaoNumeroSemClicar(linha, coluna);
                        }                       
                    }
                }
           }                    
               
            if(event.isMetaDown() == true) //se for com o botao direto
            {  
                for(int i=0;i<9;i++)//Identifica qual botão foi clicado com o botão direito
                   {
                       for(int j=0; j<9;j++)
                       {
                           if(event.getSource() == botoes[i][j])
                           {
                               linha = i;
                               coluna = j;
                           }                       
                       }                   
                   }     
                switch(clickDireita[linha][coluna])
                {
                    case 0: 
                        setFlagIcon();
                        clickDireita[linha][coluna] = 1; 
                        contFlags--;                                   
                        contMinasShow.setText("    " + contFlags + "   ");
                        break;
                    case 1: 
                        setNoIcon();
                        clickDireita[linha][coluna] = 0;
                        contFlags++; 
                        contMinasShow.setText("    " + contFlags + "   ");
                        break;
                        
                }
                //event.getSource();//retorna o objeto ao qual ocorreu a acao
                // event.isMetaDown();//true se for direita  
            }
           
        }

    } //Controla os eventos de mouse dos botoes do campo minado em si
    class MouseBotaoReinicia extends MouseAdapter {
              
        @Override
       
        public void mouseClicked(MouseEvent event) {
            if(event.isMetaDown() == false) {//se for com o botao esquerdo
                reinicia();
            }
        }
          
    }//Controla os eventos de mouse do botao de reiniciar
    class MouseModoPanico extends MouseAdapter//Controla os eventos do modo panico
    {
        @Override
        
        public void mouseEntered(MouseEvent e)
        {
            labelPanico.setIcon(word);
            quadroComBotoes.setVisible(false);
            panel.setVisible(false);
                               
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            quadroComBotoes.setVisible(true);
            
            panel.setVisible(true);
        
            labelPanico.setIcon(noIcon);
        }
    }
    
}
