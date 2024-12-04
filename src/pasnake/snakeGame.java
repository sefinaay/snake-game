package pasnake;

import java.awt.Color;
import javax.swing.JFrame;

public class snakeGame {
   public snakeGame() {
   }

   public static void main(String[] args) {
      JFrame f = new JFrame();
      f.setTitle("Snake Game -by Sefina");
      f.setBounds(10, 10, 855, 650);
      f.setResizable(false);
      f.setVisible(true);
      f.setDefaultCloseOperation(3);
      f.setBackground(Color.DARK_GRAY);
      gamePlay gameplay = new gamePlay();
      f.add(gameplay);
   }
}
