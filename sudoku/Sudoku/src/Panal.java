
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.Desktop;
import java.net.URI;

@SuppressWarnings("serial")
public class Panal extends javax.swing.JPanel {

    Sudoku game;
    private Timer timer;
    private JButton nbtn = new JButton("new game");
    private static JTextField[][] boxes;
    private JPasswordField pass = new JPasswordField("bvcoeit");
    private JLabel label = new JLabel("      Timer :00 : 00 : 00");
    private JLabel passsLabel = new JLabel("            your password :");
    private JPanel[][] paneles;
    private JPanel center, bPanel;
    @SuppressWarnings("unused")
	private JButton nBtn, cBtn, eBtn, hardBtn, midBtn, easyBtn, slove, about,sBtn,pauseBtn,tutBtn,resumeBtn;
    private int[][] temp = new int[9][9];
    private int[][] grid = new int[9][9];
    private int counter = 0;

    public JTextField newtextfield() {
        JTextField j = new JTextField("");
        j.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        j.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        j.setHorizontalAlignment(JTextField.CENTER);
        /*-------------------mouse lisner----------------*/
        j.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.decode("#f6ea80")));
                    ((JTextField) e.getSource()).setBackground(Color.decode("#f6ea80"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    ((JTextField) e.getSource()).setBackground(Color.white);
                }
            }
        });
        /*------------------------------------------------*/

        j.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setForeground(Color.decode("#0c4"));
                } else {
                    ((JTextField) e.getSource()).setForeground(Color.black);
                }
            }
        });
        return j;
    }
    
    public Panal() {
        initComponents();
        /*------------------------main panel  -------------------------------------*/
        center = new JPanel();  //main panel
        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
        center.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(center);  //add main panel to frame 

        boxes = new JTextField[9][9];
        paneles = new JPanel[3][3];
        passsLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        passsLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        passsLabel.setForeground(Color.black);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneles[i][j] = new JPanel();
                paneles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                paneles[i][j].setLayout(new GridLayout(3, 3));
                center.add(paneles[i][j]);
            }
        }
        /*------------------------text fields in boxes-------------------------------------*/

        for (int n = 0; n < 9; n++) {
            for (int i = 0; i < 9; i++) {
                boxes[n][i] = newtextfield();
                int fm = (n + 1) / 3;
                if ((n + 1) % 3 > 0) {
                    fm++;
                }
                int cm = (i + 1) / 3;
                if ((i + 1) % 3 > 0) {
                    cm++;
                }
                paneles[fm - 1][cm - 1].add(boxes[n][i]);   //add box to panel 
            }
        }
        /*------------------------panel for buttons -------------------------------------*/

        bPanel = new JPanel();
        bPanel.setBackground(Color.decode("#AABFFF"));
        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        bPanel.setLayout(new GridLayout(5, 3, 0, 5));

        /*------------------------panel for new game button -------------------------------------*/
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                label.setText(TimeFormat(counter));
               
                counter++;

            }
        };

        /*------------------------panel for new game button -------------------------------------*/
        nBtn = new JButton("New Game");
        nbtn.setSize(50, 50);
        timer = new Timer(1000, action);
        nBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                counter = 0;
                timer.start();
                restgame();
                Sudoku.newGame();

            }
        });

        /*------------------------panel for check game button -------------------------------------*/
        cBtn = new JButton("Check Game +30s");

        cBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!boxes[i][j].isEditable()) {
                            continue;
                        } else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
                            boxes[i][j].setBackground(Color.decode("#C0DCD9"));
                        } else if (boxes[i][j].getText().isEmpty()) {
                            boxes[i][j].setBackground(Color.WHITE);
                            continue;
                        } else {
                            boxes[i][j].setBackground(Color.red);
                        }
                    }
                }
                 counter += 30;
            }
        });
        /*------------------------panel for new Exit button -------------------------------------*/
        eBtn = new JButton("Exit");

        eBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*------------------------panel for new Hard button -------------------------------------*/
        easyBtn = new JButton("Hard");

        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(4);
                Sudoku.newGame();
            }
        });
        /*------------------------panel for Medium button -------------------------------------*/
        midBtn = new JButton("Medium");

        midBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                Sudoku.setlevel(3);
                Sudoku.newGame();

            }
        });
        /*------------------------panel for Easy button -------------------------------------*/
        hardBtn = new JButton("Easy");

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                Sudoku.setlevel(2);
                Sudoku.newGame();
            }
        });
        /*------------------------panel for Solve button -------------------------------------*/
        slove = new JButton("Solve Sudoku ");

        slove.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                if (pass.getText().equals("bvcoeit")) {
                    timer.stop();
                    counter = 0;
                    label.setText(TimeFormat(counter));
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            boxes[i][j].setText(String.valueOf(grid[i][j]));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(center, "your password incorrect");
                }
            }
        });
        /*------------------------panel for About about button -------------------------------------*/
        about = new JButton("About");

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(center, "Created by : BVCOE IT");
            }
        });
        /*------------------------panel for new about button -------------------------------------*/
         pass.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) { 
                    ((JPasswordField) e.getSource()).setText("");
            }
        });
         
         /*-------------------------------Submit button added---------------------------------*/
         
         
         char [] win = {'Y','O','U','W' ,'I' ,'N'};
         
         sBtn = new JButton("Submit");

         sBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 
            	 
                 for (int i = 0; i < 9; i++) {
                     for (int j = 0; j < 9; j++) {
                         if (!boxes[i][j].isEditable()) {
                             continue;
                         } else if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
                        	 int k = 3;
                        	 int w = 0;
                        	 for(int l = 3; l <= 5; l++) {
                        		 boxes[k][l].setBackground(Color.green);
                        		 boxes[k][l].setText(String.valueOf(win[w]));
                        		 w++;
                        	 }
                        	 k = 5;
                        	 for(int m = 3; m <= 5; m++) {
                        		 boxes[k][m].setBackground(Color.green);
                        		 boxes[k][m].setText(String.valueOf(win[w]));
                        		 w++;
                        	 }
                         } else if (boxes[i][j].getText().isEmpty()) {
                             boxes[i][j].setBackground(Color.WHITE);
                             continue;
                         } else {
                             boxes[i][j].setBackground(Color.red);
                         }
                     }
                 }
            	 
             }
         });
         
         /*-------------------------Pause Button--------------------------------*/
         
         pauseBtn = new JButton("Pause");

         pauseBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 timer.stop();
            	 
                             	 
             }
         });
         
         /*--------------------------Resume------------------------------*/
         
         resumeBtn = new JButton("Resume");

         resumeBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 timer.start();
            	 
                             	 
             }
         });
         
         
         /*-----------------------------------Tutorial Button--------------------------*/
         
         tutBtn = new JButton("Tutorial");

         tutBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	  	 
            	 try {
            		  Desktop desktop = Desktop.getDesktop();
            		  URI oURL = new URI("https://www.youtube.com/watch?v=kvU9_MVAiE0");
            		  desktop.browse(oURL);
            		} catch (Exception E) {
            		  E.printStackTrace();
            		}
            	 
             }
         });
         
         
         
         
         

        /*------------------------add button panel and buttons to frame and panel -------------------------------------*/
        bPanel.add(hardBtn);   //add new game button to 
        bPanel.add(midBtn);
        bPanel.add(easyBtn);
        bPanel.add(nBtn);   //add new game button to 
        bPanel.add(cBtn);
        bPanel.add(label);
        bPanel.add(passsLabel);
        bPanel.add(pass);
        bPanel.add(slove);
        bPanel.add(pauseBtn);
        bPanel.add(resumeBtn);
        bPanel.add(tutBtn);
        bPanel.add(sBtn);
        bPanel.add(about);
        bPanel.add(eBtn);
        add(bPanel, "South");   //add button panel to frame 

    }

    public void setarray(int[][] grid, int[][] temp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.temp[i][j] = temp[i][j];
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public void setTextLable() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.temp[i][j] != 0) {
                    boxes[i][j].setText(String.valueOf(this.temp[i][j]));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.decode("#C0DCC0"));
                } else {
                    boxes[i][j].setText("");
                }
            }
        }
    }

    public static void restgame() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boxes[i][j].setForeground(Color.black);
                boxes[i][j].setEditable(true);
                boxes[i][j].setBackground(Color.WHITE);
            }
        }
    }

    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count - hours * 3600) / 60;
        int seconds = count - minutes * 60;

        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

  
    private void initComponents() {

        setLayout(null);
    }
}
