package pasnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class gamePlay extends JPanel implements KeyListener, ActionListener {
   private int[] ularxlength = new int[750];
   private int[] ularylength = new int[750];
   private boolean kanan = false;
   private boolean kiri = false;
   private boolean atas = false;
   private boolean bawah = false;
   private ImageIcon kananUlar;
   private ImageIcon kiriUlar;
   private ImageIcon atasUlar;
   private ImageIcon bawahUlar;
   private ImageIcon badanUlar;
   private int[] maemxular = new int[]{25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800};
   private int[] maemyular = new int[]{75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575};
   private ImageIcon maemnyaUlar;
   private Random random = new Random();
   private int xular;
   private int yular;
   private Timer waktu;
   private int delay;
   private int panjangUlar;
   private int moves;
   private int scores;
   private ImageIcon judulGambar;

   public gamePlay() {
      this.xular = this.random.nextInt(29);
      this.yular = this.random.nextInt(17);
      this.delay = 100;
      this.panjangUlar = 3;
      this.moves = 0;
      this.scores = 0;
      this.addKeyListener(this);
      this.setFocusable(true);
      this.setFocusTraversalKeysEnabled(true);
      this.waktu = new Timer(this.delay, this);
      this.waktu.start();
   }
   
    private void increaseSpeed() {
        if (this.scores > 5 && this.delay > 50) { 
            this.delay -= 3; 
            this.waktu.setDelay(this.delay); 
        }
    }

   public void paint(Graphics g) {
       if (this.moves == 0) {
        this.ularxlength[0] = 100;
        this.ularxlength[1] = 75;
        this.ularxlength[2] = 50;
        this.ularylength[0] = 100;
        this.ularylength[1] = 100;
        this.ularylength[2] = 100;
    }

    g.setColor(Color.white);
    g.drawRect(24, 10, 801, 55);
    
    // Inisialisasi titleImage
    this.judulGambar = new ImageIcon(getClass().getResource("/resources/snakejudul.png"));
    
    // Periksa apakah gambar dimuat dengan benar
    if (this.judulGambar.getImageLoadStatus() != MediaTracker.COMPLETE) {
        System.out.println("Gambar judul tidak dimuat!");
    }
    
    this.judulGambar.paintIcon(this, g, 25, 11);
    
    g.setColor(Color.white);
    g.drawRect(24, 74, 801, 527);
    g.setColor(Color.BLACK);
    g.fillRect(25, 75, 800, 525);
    g.setColor(Color.WHITE);
    g.setFont(new Font("arial", 0, 14));
    g.drawString("Scores : " + this.scores, 730, 30);
    g.drawString("Length : " + this.panjangUlar, 730, 50);
    
    // Gambar bagian tubuh ular
    this.kananUlar = new ImageIcon(getClass().getResource("/resources/ularkanan.png"));
    this.kananUlar.paintIcon(this, g, this.ularxlength[0], this.ularylength[0]);

    int a;
    for (a = 0; a < this.panjangUlar; ++a) {
        if (a == 0 && this.kanan) {
            this.kananUlar = new ImageIcon(getClass().getResource("/resources/ularkanan.png"));
            this.kananUlar.paintIcon(this, g, this.ularxlength[a], this.ularylength[a]);
        }

        if (a == 0 && this.kiri) {
            this.kiriUlar = new ImageIcon(getClass().getResource("/resources/ularkiri.png"));
            this.kiriUlar.paintIcon(this, g, this.ularxlength[a], this.ularylength[a]);
        }

        if (a == 0 && this.atas) {
            this.atasUlar = new ImageIcon(getClass().getResource("/resources/ularatas.png"));
            this.atasUlar.paintIcon(this, g, this.ularxlength[a], this.ularylength[a]);
        }

        if (a == 0 && this.bawah) {
            this.bawahUlar =new ImageIcon(getClass().getResource("/resources/ularbawah.png"));
            this.bawahUlar.paintIcon(this, g, this.ularxlength[a], this.ularylength[a]);
        }

        if (a != 0) {
            this.badanUlar = new ImageIcon(getClass().getResource("/resources/ularbadan.png"));
            this.badanUlar.paintIcon(this, g, this.ularxlength[a], this.ularylength[a]);
        }
    }

    // Gambar makanan
    this.maemnyaUlar = new ImageIcon(getClass().getResource("/resources/maemular.png")); 
    if (this.maemxular[this.xular] == this.ularxlength[0] && this.maemyular[this.yular] == this.ularylength[0]) {
        ++this.panjangUlar;
        ++this.scores;
        increaseSpeed();
        this.xular = this.random.nextInt(29);
        this.yular = this.random.nextInt(17);
    }

    this.maemnyaUlar.paintIcon(this, g, this.maemxular[this.xular], this.maemyular[this.yular]);

    // Cek tabrakan ular dengan dirinya sendiri
    for (a = 1; a < this.panjangUlar; ++a) {
        if (this.ularxlength[a] == this.ularxlength[0] && this.ularylength[a] == this.ularylength[0]) {
            this.kanan = false;
            this.kiri = false;
            this.atas = false;
            this.bawah = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", 1, 50));
            g.drawString("GAME OVER!", 250, 250);
            g.setFont(new Font("arial", 1, 20));
            g.drawString("Tekan tombol spasi untuk me-restart permainan", 200, 290);
        }
    }
    
   }

   public void actionPerformed(ActionEvent arg0) {
      int i;
      if (this.kanan) {
         for(i = this.panjangUlar - 1; i >= 0; --i) {
            this.ularylength[i + 1] = this.ularylength[i];
         }

         for(i = this.panjangUlar; i >= 0; --i) {
            if (i == 0) {
               this.ularxlength[i] += 25;
            } else {
               this.ularxlength[i] = this.ularxlength[i - 1];
            }

            if (this.ularxlength[i] > 800) {
               this.ularxlength[i] = 25;
            }
         }

         this.repaint();
      }

      if (this.kiri) {
         for(i = this.panjangUlar - 1; i >= 0; --i) {
            this.ularylength[i + 1] = this.ularylength[i];
         }

         for(i = this.panjangUlar; i >= 0; --i) {
            if (i == 0) {
               this.ularxlength[i] -= 25;
            } else {
               this.ularxlength[i] = this.ularxlength[i - 1];
            }

            if (this.ularxlength[i] < 25) {
               this.ularxlength[i] = 800;
            }
         }

         this.repaint();
      }

      if (this.atas) {
         for(i = this.panjangUlar - 1; i >= 0; --i) {
            this.ularxlength[i + 1] = this.ularxlength[i];
         }

         for(i = this.panjangUlar; i >= 0; --i) {
            if (i == 0) {
               this.ularylength[i] -= 25;
            } else {
               this.ularylength[i] = this.ularylength[i - 1];
            }

            if (this.ularylength[i] < 75) {
               this.ularylength[i] = 575;
            }
         }

         this.repaint();
      }

      if (this.bawah) {
         for(i = this.panjangUlar - 1; i >= 0; --i) {
            this.ularxlength[i + 1] = this.ularxlength[i];
         }

         for(i = this.panjangUlar; i >= 0; --i) {
            if (i == 0) {
               this.ularylength[i] += 25;
            } else {
               this.ularylength[i] = this.ularylength[i - 1];
            }

            if (this.ularylength[i] > 575) {
               this.ularylength[i] = 75;
            }
         }

         this.repaint();
      }

   }

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == 39) {
         ++this.moves;
         if (!this.kiri) {
            this.kanan = true;
         } else {
            this.kanan = false;
            this.kiri = true;
         }

         this.atas = false;
         this.bawah = false;
      }

      if (e.getKeyCode() == 37) {
         ++this.moves;
         if (!this.kanan) {
            this.kiri = true;
         } else {
            this.kiri = false;
            this.kanan = true;
         }

         this.atas = false;
         this.bawah = false;
      }

      if (e.getKeyCode() == 38) {
         ++this.moves;
         if (!this.bawah) {
            this.atas = true;
         } else {
            this.atas = false;
            this.bawah = true;
         }

         this.kiri = false;
         this.kanan = false;
      }

      if (e.getKeyCode() == 40) {
         ++this.moves;
         if (!this.atas) {
            this.bawah = true;
         } else {
            this.bawah = false;
            this.atas = true;
         }

         this.kiri = false;
         this.kanan = false;
      }

      if (e.getKeyCode() == 32) {
         this.scores = 0;
         this.moves = 0;
         this.panjangUlar = 3;
         this.repaint();
      }

   }

   public void keyReleased(KeyEvent arg0) {
   }

   public void keyTyped(KeyEvent arg0) {
   }
}
