import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class play_game
{
    String[] temp=new String[200];
    int m,M,N;
	
    dict dp;
    play_game(int m,int M,int N,dict dp)
    {
        this.m=m;
        this.M=M;
        this.N=N;
        this.dp=dp;
    }
	
    public void check_oneword(char boggle[][], boolean visited1[][], int i, 
                   int j, String str) throws IOException 
    { 
        visited1[i][j] = true; 
        str = str + boggle[i][j];
        if (dp.isWord(str)) 
        {
            
            temp[m]=str;
        	m++;
        }
 
        for (int row=i-1; row<=i+1 && row<M; row++) 
        {
            for (int col=j-1; col<=j+1 && col<N; col++)
            { 
                if (row>=0 && col>=0 && !visited1[row][col])
                {

                      if(str.length()<=5)
                      {
                         check_oneword(boggle,visited1, row, col, str);
                      }
                } 
            } 
        }
        str=str.substring(0,str.length()-1); 
        visited1[i][j] = false;
    }
    public void findone(char boggle[][],char o) throws IOException
    { 
        boolean visited1[][] = new boolean [M][N];
        for(int k=0;k<M;k++)
        {
            for(int l=0;l<N;l++)
            {
                visited1[k][l]=false;
            }
        }
        String str = ""; 
        for (int i=0; i<M; i++) 
        for (int j=0; j<N; j++) 
        {
        		if(boggle[i][j]==o)
        		{
        			  check_oneword(boggle, visited1, i, j, str); 
        		}
        }
    } 
    public void strcheck(String str1)
    {
        int flag=0;
        String tmp;
        for(int z=0;z<temp.length;z++)
        {
            tmp=temp[z];
            try
            {
                if(tmp.compareTo(str1)==0) 
                {
                    System.out.println("This word is possible....");
                    flag=1;
                    break;
                }
            }
            catch(NullPointerException e) 
            {
                flag=0;
            }
        }
        if(flag==0)
        {
            System.out.println("This word is not  possible....");
        }
    }
}
public class wordgame implements ActionListener
{
    int M,N;
    dict dp;
	static	JFrame f=new JFrame("Mera");
	static JButton[][] b=new JButton[4][4];
	static JButton[][] b1=new JButton[5][5];
	static JButton[][] b2=new JButton[3][3];
    wordgame(int M,int N,dict dp)
    {
        this.M=M;
        this.N=N;
        this.dp=dp;
    }
   wordgame()
   {

   }
    public void findWordsUtil(char boggle[][], boolean visited[][], int i, 
                   int j, String str)   throws IOException 
    { 
        visited[i][j] = true; 
        str = str + boggle[i][j]; 
        if (dp.isWord(str)) 
        {
            System.out.println(str);
        }
           
        for (int row=i-1; row<=i+1 && row<M; row++) 
        {
            for (int col=j-1; col<=j+1 && col<N; col++) 
            {
                if (row>=0 && col>=0 && !visited[row][col]) 
                {
                    if(str.length()<=5)
                    findWordsUtil(boggle,visited, row, col, str);
                }

            }
        }
         
        str=str.substring(0,str.length()-1); 
        visited[i][j] = false; 
	
    } 
    void findWords(char boggle[][]) throws IOException
    { 
        boolean visited[][] = new boolean [M][N];
        for(int k=0;k<M;k++)
        {
            for(int l=0;l<N;l++)
            {
                visited[k][l]=false;
            }
        }
        String str = ""; 
        for (int i=0; i<M; i++) 
        for (int j=0; j<N; j++) 
                findWordsUtil(boggle, visited, i, j, str); 
    } 
    void disp(char boggle[][],int n) 
    {
        System.out.println("YOUR GAME BOARD IS:");
           
        System.out.println("****************************"); 
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                
                 System.out.print("+---");
                 if(j==n-1)
                    System.out.print("+");
            }
            System.out.println("");
            for(int k=0;k<n;k++)
            {
                 System.out.print("| "+boggle[i][k]+" ");
                 if(k==n-1)
                    System.out.print("|");
            }
            System.out.println();
            if(i==n-1)
            {
                for(int p=0;p<n;p++)
                {
                    
                    System.out.print("+---");
                    if(p==n-1)
                        System.out.print("+");
                }
            }
        }  
        System.out.println("\n******************************");
    }
public void p1() throws IOException
{
Scanner sc =new Scanner(System.in);
        String str1;
        System.out.println("ENTER 1:To Play for 3x3 board");
        System.out.println("ENTER 2:To Play for 4x4 board");
        System.out.println("ENTER 3:To Play for 5x5 board");
        //System.out.println("ENTER 4:To Play for 6x6 board");
       // System.out.println("ENTER 5:To Play for 7x7 board");
       int n=sc.nextInt();
        String tmp="SOLUTION";
        str1="";
	
        dict dp=new dict();
		switch(n)
        {
        case 1:
        {    
        	char [][] boggle1 = new char [][]{{'G','I','Z'}, 
            {'U','E','K'}, 
            {'Q','S','E'}}; 
			for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				b2[i][j]=new JButton(String.valueOf(boggle1[i][j]));
				b2[i][j].setSize(10,10);
				b2[i][j].setBackground(Color.RED);
				b2[i][j].addActionListener(this);
				f.add(b2[i][j]);
			}
		}
		
		f.setLayout(new GridLayout(3,3)); 
  		f.setSize(300, 300);  
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            wordgame g1=new wordgame(3,3,dp);
            //g1.disp(boggle1,3);  //
            while(str1.compareTo(tmp)!=0)
            {
                System.out.println("Enter your word:");
                str1=sc.next();
                play_game p1=new play_game(0,3,3,dp);
                p1.findone(boggle1,str1.charAt(0));
                p1.strcheck(str1);
                System.out.println("********************************");
                System.out.println("For solution type solution");
            }
            System.out.println("****************SOLUTION****************");
            g1.findWords(boggle1); 
            break;
        }
        case 2:
        {
        	char [][] boggle2 = new char [][]{{'K','W','E','D'}, 
                {'E','E','O','F'}, 
                {'P','J','T','S'},
                {'K','A','T','D'}}; 
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				b[i][j]=new JButton(String.valueOf(boggle2[i][j]));
				b[i][j].setSize(10,10);
				b[i][j].setBackground(Color.GREEN);
				b[i][j].addActionListener(this);
				f.add(b[i][j]);
			}
		}
		
		f.setLayout(new GridLayout(4,4)); 
  		f.setSize(300, 300);  
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                wordgame g2=new wordgame(4,4,dp);
               // g2.disp(boggle2,4);   //  
            while(str1.compareTo(tmp)!=0)
            {
                System.out.println("Enter your word:");
                str1=sc.next();
                play_game p2=new play_game(0,4,4,dp);
                p2.findone(boggle2,str1.charAt(0));
                p2.strcheck(str1);
                System.out.println("********************************");
                System.out.println("For solution type solution");
            }
            System.out.println("****************SOLUTION****************");
            g2.findWords(boggle2); 	
            break;
        }
        case 3:
        {
        	char [][] boggle3 = new char [][]{{'P','H','R','C','A'}, 
                {'A','L','F','X','D'}, 
                {'G','A','E','O','A'},
                {'V','T','E','P','E'},
                {'O','H','O','D','R'}};
for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				b1[i][j]=new JButton(String.valueOf(boggle3[i][j]));
				b1[i][j].setSize(10,10);
				b1[i][j].setBackground(Color.YELLOW);
				b1[i][j].addActionListener(this);
				f.add(b1[i][j]);
			}
		}
		
		f.setLayout(new GridLayout(5,5)); 
  		f.setSize(500,500);  
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            wordgame g3=new wordgame(5,5,dp);
           // g3.disp(boggle3,5);  //
            while(str1.compareTo(tmp)!=0)
            {
                System.out.println("Enter your word:");
                str1=sc.next();
                play_game p3=new play_game(0,5,5,dp);
                //System.out.println("First charcter of word:" +str1.charAt(0));//
                p3.findone(boggle3,str1.charAt(0));
                p3.strcheck(str1);
                System.out.println("********************************");
                System.out.println("For solution type solution");
            }
            System.out.println("****************SOLUTION****************");
            g3.findWords(boggle3); 
            break;
        }
		
        }
		return;
    }
    public  void actionPerformed(ActionEvent e)
  {
    String str = e.getActionCommand();	    // to know which Java button user clicked
    
 
    if(str.equals("K"))
    {
      b[0][0].setBackground(Color.red);
    }
    else if(str.equals("A"))
    {
 	  b[1][1].setBackground(Color.red);
    }
    else if(str.equals("C"))
    {
     	  b[2][2].setBackground(Color.red);
    }
  }		

    public static void main(String args[])  throws IOException
    {
    wordgame l1=new wordgame();
    l1.p1();
	
    }
       
}    
