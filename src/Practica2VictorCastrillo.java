import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Practica2VictorCastrillo extends JFrame implements ActionListener {
    CardLayout tarjetas;
    JPanel panelTarjetas, panelNorte, panelSur, panelEste, panelOeste;

    JPanel[] frms = new JPanel[5];
    String[] txts = {"BIENVENIDA", "DATOS PERSONALES", "PAÍS Y PROVINCIA", "FINALIZAR", "RESULTADO"};
    JButton[] btts = new JButton[5];
    JButton bttNext, bttBack;

    JLabel titulo;

    public Practica2VictorCastrillo(){
        initPantalla();
        initPanels();

        repaint();
        setVisible(true);
    }


    public static void main(String[] args) {
        new Practica2VictorCastrillo();
    }

    private void initPanels(){
        panelNorte = new JPanel();
        panelNorte.setBackground(Color.darkGray.brighter());
        titulo = new JLabel("FORMULARIO");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 100));
        titulo.setForeground(Color.lightGray);
        panelNorte.add(titulo);
        add(panelNorte, BorderLayout.NORTH);

        panelEste = new JPanel();
        panelEste.setBackground(Color.darkGray.brighter());
        panelEste.setPreferredSize(new Dimension(200, 1000));
        panelEste.setLayout(new FlowLayout(FlowLayout.CENTER,10,100));

        for (int i = 0; i < btts.length; i++){
            btts[i] = new JButton(txts[i]);
            btts[i].setBackground(Color.lightGray);
            btts[i].setForeground(Color.white);
            btts[i].setPreferredSize(new Dimension(170, 50));
            if(i > 0){
                btts[i].setEnabled(false);
            }
            panelEste.add(btts[i]);
        }

        add(panelEste, BorderLayout.EAST);

        panelOeste = new JPanel();
        panelOeste.setBackground(Color.darkGray.brighter());
        add(panelOeste, BorderLayout.WEST);

        panelSur = new JPanel();
        panelSur.setBackground(Color.darkGray.brighter());
        add(panelSur, BorderLayout.SOUTH);

        initPanelForms();
    }

    private void initPanelForms(){
        tarjetas = new CardLayout();
        panelTarjetas = new JPanel();
        panelTarjetas.setLayout(tarjetas);

        for (int i = 0; i < frms.length; i++) {
            frms[i] = new JPanel();
            frms[i].setLayout(new BorderLayout());
            frms[i].setBackground(Color.lightGray.darker());
            formularios(frms[i], i);
            bttNext = new JButton("NEXT");
            bttNext.setPreferredSize(new Dimension(100, 50));
            frms[i].add(bttNext, BorderLayout.SOUTH);
            panelTarjetas.add(frms[i], txts[i]);
        }

        tarjetas.show(panelTarjetas, "BIENVENIDA");
        add(panelTarjetas,BorderLayout.CENTER);
    }

    private void formularios(JPanel f, int i){

        JPanel panelN = new JPanel();
        panelN.setBackground(Color.lightGray.darker());
        f.add(panelN, BorderLayout.NORTH);

        JPanel panelE = new JPanel();
        panelE.setBackground(Color.lightGray.darker());
        f.add(panelE, BorderLayout.EAST);

        JPanel panelW = new JPanel();
        panelW.setBackground(Color.lightGray.darker());
        f.add(panelW, BorderLayout.WEST);

        JPanel panelS = new JPanel();
        panelS.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        panelS.setBackground(Color.lightGray.darker());
        bttNext = new JButton("NEXT");
        bttNext.setPreferredSize(new Dimension(100, 50));
        panelS.add(bttNext);
        f.add(panelS, BorderLayout.SOUTH);


        panelTarjetas.add(f, txts[i]);
    }

    private void initPantalla() {
        setLayout(new BorderLayout());
        setTitle("Práctica 2  ||  Víctor Castrillo");
        setSize(1500, 1025);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

            // x 1500, y 1025

            //GradientPaint gp = new GradientPaint(panelTarjetas.getX(), panelTarjetas.getY() + 27, new Color(62, 95, 138), 1300, 1000,  new Color(9, 17, 31));
            /*GradientPaint gp = new GradientPaint(0, 0, new Color(62, 95, 138), 1500, 1025,  new Color(9, 17, 31));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, 1500, 1025);*/



            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.lightGray);
            g2d.drawArc(50, 60, 100, 100, 45, -275);

            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.white);
            g2d.drawArc(50, 60, 100, 100, 45, -275);


            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.white);
            g2d.drawLine(100, 120, 125, 85);
            g2d.drawLine(100, 120, 75, 85);

            g2d.setColor(Color.lightGray);
            g2d.drawLine(100, 130, 125, 85);
            g2d.drawLine(100, 130, 75, 85);

            g2d.setColor(Color.white);
            g2d.drawLine(100, 140, 125, 85);
            g2d.drawLine(100, 140, 75, 85);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}