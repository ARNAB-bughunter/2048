import java.awt.*;
import javax.swing.*;
import java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.LineBorder;
/**
 * @author Arnab Naha
 */
public class test extends JFrame{
    make panel;
   static Container c;
    public test() {
        setLayout(null);
        setSize(600,690+20);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("2048");
        panel=new make();
        addKeyListener(panel);
        add(panel);
    }
    public static void main(String [] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
         test frame = new test();
         frame.setResizable(false);
         frame.setOpacity(0.9f);        
         frame.setVisible(true);
         c=frame.getContentPane();
         c.setBackground(Color.darkGray);
     }
}

/***game class***/

class make extends JPanel implements KeyListener{
JLabel label_array[][]=new JLabel[4][4];
int num_array[][]=new int[4][4];
JPanel inner_panel;
JPanel win_panel;
Random rand;
boolean win_status=false;
boolean lose_status=false;
LineBorder line;
int arr0[],arr1[],arr2[],arr3[];
int score=0;

public void paint(Graphics g){
super.paint(g);
g.setColor(Color.green);
int t=614;
for(int i=0;i<9;i++){
g.drawLine(0,t,560,t);
t+=4;
}
int r=4;
for(int i=0;i<140;i++){
g.drawLine(r,610,r,648);
r+=4;
}


g.setColor(Color.red);
g.setFont(new Font("Comic Sans MS",Font.BOLD,25));
g.drawString("SCORE:"+""+score,0,595);
int x=140;
g.setColor(Color.black);
Graphics2D g2=(Graphics2D)g;
g2.setStroke(new BasicStroke(8));
g2.drawRect(0,0,560,560);
for(int i=0;i<4;i++){
g.drawLine(x,0,x,558);
g.drawLine(0,x,558,x);
x+=140;
}
g2.setColor(Color.darkGray);
g2.setStroke(new BasicStroke(10));
g2.drawLine(0,565,560,565);
g2.drawLine(0,605,560,605);

if(win_status==true){
g.setColor(new Color(0.0f,0.0f,0.0f,0.8f));
g.fillRect(0,0,560,560);
g.setFont(new Font("Comic Sans MS",Font.BOLD,100));
g.setColor(Color.cyan);
g.drawString("YOU WIN",70,250);
  }
if(is_empty()==false && is_game_over()==false){
g.setColor(new Color(0.0f,0.0f,0.0f,0.8f));
g.fillRect(0,0,560,560);
g.setFont(new Font("Comic Sans MS",Font.BOLD,70));
g.setColor(Color.yellow);
g.drawString("GAME OVER",70,250);
g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
g.drawString("PRESS 'ENTER' TO RESTART",65,290);
 }
}
make(){
arr0=new int[4];
arr1=new int[4];
arr2=new int[4];
arr3=new int[4];  
win_panel=new JPanel(){
public void paint(Graphics gx){
super.paint(gx);
gx.setColor(Color.cyan);
gx.setFont(new Font("Comic Sans MS",Font.BOLD,85));
gx.drawString("YOU WIN",70,230);  
  }
};
win_panel.setBounds(0,0,560,560);
win_panel.setBackground(new Color(0.0f,0.0f,0.0f,1f));
rand=new Random();    
int new_rand_i1=rand.nextInt(4);
int new_rand_j1=rand.nextInt(4);
int new_rand_i2=rand.nextInt(4);
int new_rand_j2=rand.nextInt(4);
inner_panel=new JPanel();
inner_panel.setLayout(new GridLayout(4,4));    
inner_panel.setBounds(0,0,560,560);
this.setBounds(15,15,560,650);
this.setBackground(Color.black);
this.setLayout(null);
this.add(inner_panel);
for(int i=0;i<4;i++){
    for(int j=0;j<4;j++){ 
       label_array[i][j]=new JLabel("",JLabel.CENTER);
       label_array[i][j].setForeground(Color.black);
       label_array[i][j].setBackground(Color.gray); 
       label_array[i][j].setOpaque(true);
       label_array[i][j].setFont(new Font("Comic Sans MS",Font.BOLD,50));
        inner_panel.add(label_array[i][j]);
    }
   }
num_array[new_rand_i1][new_rand_j1]=2*(rand.nextInt(2)+1);   
label_array[new_rand_i1][new_rand_j1].setText(Integer.toString(num_array[new_rand_i1][new_rand_j1]));   
label_array[new_rand_i1][new_rand_j1].setBackground(Color.gray);

num_array[new_rand_i2][new_rand_j2]=2*(rand.nextInt(2)+1); 
label_array[new_rand_i2][new_rand_j2].setText(Integer.toString(num_array[new_rand_i2][new_rand_j2]));   
label_array[new_rand_i2][new_rand_j2].setBackground(Color.gray);
getdata();
make_Background();
}
public void resart(){
score=0;
for(int i=0;i<4;i++)
  for(int j=0;j<4;j++)
      num_array[i][j]=0;    

for(int i=0;i<4;i++) 
  for(int j=0;j<4;j++)
    label_array[i][j].setText("");

int new_rand_i1=rand.nextInt(4);
int new_rand_j1=rand.nextInt(4);

int new_rand_i2=rand.nextInt(4);
int new_rand_j2=rand.nextInt(4);    

num_array[new_rand_i1][new_rand_j1]=2*(rand.nextInt(2)+1);   
label_array[new_rand_i1][new_rand_j1].setText(Integer.toString(num_array[new_rand_i1][new_rand_j1]));   
label_array[new_rand_i1][new_rand_j1].setBackground(Color.gray);

num_array[new_rand_i2][new_rand_j2]=2*(rand.nextInt(2)+1); 
label_array[new_rand_i2][new_rand_j2].setText(Integer.toString(num_array[new_rand_i2][new_rand_j2]));   
label_array[new_rand_i2][new_rand_j2].setBackground(Color.gray);
getdata();
setdata();
make_Background();
repaint();
}


public void getdata(){
for(int i=0;i<4;i++)
    for(int j=0;j<4;j++)
        if(label_array[i][j].getText()=="")
                num_array[i][j]=0;
        else
                num_array[i][j]=Integer.valueOf(label_array[i][j].getText());    
}
public void random_num(){
  while(is_empty()){
int rand_i=rand.nextInt(4);
int rand_j=rand.nextInt(4);
if(num_array[rand_i][rand_j]==0){
    num_array[rand_i][rand_j]=2*(rand.nextInt(2)+1);
    break;
       }
  }
}
public void setdata(){
for(int i=0;i<4;i++)
    for(int j=0;j<4;j++)
        if(num_array[i][j]==0)
                label_array[i][j].setText("");
        else{
            label_array[i][j].setText(Integer.toString(num_array[i][j]));  
            label_array[i][j].setBackground(Color.gray); 
            label_array[i][j].setOpaque(true);
            }
}
public void press_up(int a[][]){
  int i,j,li,ri;
  for(j=0;j<4;j++)
  {
    li=0;ri=j;
    for(i=1;i<4;i++)
    {
      if(a[i][j]!=0)
      {
        if(a[i-1][j]==0 || a[i-1][j]==a[i][j])
        {
          if(a[li][ri]==a[i][j])
          {
            a[li][ri]*=2;
            a[i][j]=0;
            score+=4;
          }
          else
          {
            if(a[li][ri]==0)
            {
              a[li][ri]=a[i][j];
              a[i][j]=0;
            }
            else
            {
              a[++li][ri]=a[i][j];
              a[i][j]=0;
            }
          }
        }
        else li++;
      }
    }
  }
}
public void press_down(int a[][]){
  int i,j,li,ri;
  for(j=0;j<4;j++)
  {
    li=3;ri=j;
    for(i=2;i>=0;i--)
    {
      if(a[i][j]!=0)
      {
        if(a[i+1][j]==0 || a[i+1][j]==a[i][j])
        {
          if(a[li][ri]==a[i][j])
          {
            a[li][ri]*=2;
            a[i][j]=0;
            score+=4;
          }
          else
          {
            if(a[li][ri]==0)
            {
              a[li][ri]=a[i][j];
              a[i][j]=0;
            }
            else
            {
              a[--li][ri]=a[i][j];
              a[i][j]=0;
            }
          }
        }
        else li--;
      }
    }
  }
}
public void press_left(int a[][]){
int i,j,li,ri;
  for(i=0;i<4;i++)
  {
    li=i;ri=0;
    for(j=1;j<4;j++)
    {
      if(a[i][j]!=0)
      {
        if(a[i][j-1]==0 || a[i][j-1]==a[i][j])
        {
          if(a[li][ri]==a[i][j])
          {
            a[li][ri]*=2;
            a[i][j]=0;
            score+=4;
          }
          else
          {
            if(a[li][ri]==0)
            {
              a[li][ri]=a[i][j];
              a[i][j]=0;
            }
            else
            {
              a[li][++ri]=a[i][j];
              a[i][j]=0;
            }
          }
        }
        else ri++;
      }
    }
  }
}
public void press_right(int a[][]){
int i,j,li,ri;
  for(i=0;i<4;i++)
  {
    li=i;ri=3;
    for(j=2;j>=0;j--)
    {
      if(a[i][j]!=0)
      {
        if(a[i][j+1]==0 || a[i][j+1]==a[i][j])
        {
          if(a[li][ri]==a[i][j])
          {
            a[li][ri]*=2;
            a[i][j]=0;
            score+=4;
          }
          else
          {
            if(a[li][ri]==0)
            {
              a[li][ri]=a[i][j];
              a[i][j]=0;
            }
            else
            {
              a[li][--ri]=a[i][j];
              a[i][j]=0;
            }
          }
        }
        else ri--;
      }
    }
  }
}
public boolean is_empty(){
for(int i=0;i<4;i++)
    for(int j=0;j<4;j++)
        if(num_array[i][j]==0)
            return true;
return false;
}

public boolean is_game_over(){
  for(int i=0;i<3;i++)
      for(int j=0;j<3;j++)
        if(num_array[i+1][j]==num_array[i][j] || num_array[i][j+1]==num_array[i][j])
          return true;
     return false; 
      }
public void is_win(){
for(int i=0;i<4;i++)
    for(int j=0;j<4;j++)
        if(num_array[i][j]==2048){
          win_status=true;
        break;
    }

}
public void make_Background() {
for(int i=0;i<4;i++){
for(int j=0;j<4;j++){
if(num_array[i][j]==2)
  label_array[i][j].setBackground(Color.red);
if(num_array[i][j]==4)
  label_array[i][j].setBackground(Color.magenta);
if(num_array[i][j]==8)
  label_array[i][j].setBackground(Color.pink);
if(num_array[i][j]==16)
  label_array[i][j].setBackground(Color.orange);
if(num_array[i][j]==32)
  label_array[i][j].setBackground(Color.yellow);
if(num_array[i][j]==64)
  label_array[i][j].setBackground(Color.cyan);
if(num_array[i][j]==128)
  label_array[i][j].setBackground(Color.white);
if(num_array[i][j]==256)
  label_array[i][j].setBackground(Color.blue);
if(num_array[i][j]==512)
  label_array[i][j].setBackground(Color.green);
if(num_array[i][j]==1024)
  label_array[i][j].setBackground(new Color(210,105,30));
if(num_array[i][j]==2048)
  label_array[i][j].setBackground(new Color(255,222,173));
if(num_array[i][j]==0)
    label_array[i][j].setBackground(Color.gray);
   }  
  }
}
public void keyReleased(KeyEvent key){}
public void keyTyped(KeyEvent key){}
public void keyPressed(KeyEvent key){
if(key.getKeyCode()==KeyEvent.VK_UP || key.getKeyChar()=='w'){
    getdata();
    press_up(num_array);
    random_num();
    setdata();
    make_Background();
    repaint();
    }
if(key.getKeyCode()==KeyEvent.VK_DOWN || key.getKeyChar()=='s'){
    getdata();
    press_down(num_array);
    random_num();
    setdata();
    make_Background();
    repaint();
    }

if(key.getKeyCode()==KeyEvent.VK_RIGHT || key.getKeyChar()=='d'){
    getdata();
    press_right(num_array);
    random_num();
    setdata();
    make_Background();
     repaint();

    }
if(key.getKeyCode()==KeyEvent.VK_LEFT || key.getKeyChar()=='a'){
    getdata();
    press_left(num_array);
    random_num();
    setdata();
    make_Background();
    repaint();
    }
if(key.getKeyCode()==KeyEvent.VK_ENTER && is_empty()==false && is_game_over()==false)
resart();    
 }
}
