import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Practica2VictorCastrillo extends JFrame implements ActionListener {
    CardLayout tarjetas;
    JPanel panelTarjetas, panelNorte, panelSur, panelEste, panelOeste;

    String[] esp = new String[10];
    String[] eeuu = new String[10];

    JList<String> españa, estadosUnidos;

    String pais = "";
    String provincia = "";

    JButton bttEsp, bttEeuu;

    JPanel[] frms = new JPanel[5];
    String[] txts = {"BIENVENIDA", "DATOS PERSONALES", "PAÍS Y PROVINCIA", "FINALIZAR", "RESULTADO"};
    JButton[] btts = new JButton[5];
    JButton bttNext, bttBack;

    JLabel txtNombre, txtEmail, txtContraseña;
    JTextField nombre, email, contraseña;
    int pos = 0;

    JLabel titulo, tit, inst;

    public Practica2VictorCastrillo() throws IOException {
        initPantalla();
        initPanels();
        recogerFicheros();

        bttBack.setEnabled(false);

        setVisible(true);
    }


    public static void main(String[] args) throws IOException {
        new Practica2VictorCastrillo();
    }

    private void initPanels() {
        panelNorte = new JPanel();
        panelNorte.setBackground(new Color(9, 17, 31, 250));
        titulo = new JLabel("FORMULARIO");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 100));
        titulo.setForeground(Color.lightGray);
        panelNorte.add(titulo);
        add(panelNorte, BorderLayout.NORTH);

        panelEste = new JPanel();
        panelEste.setBackground(new Color(9, 17, 31, 250));
        panelEste.setPreferredSize(new Dimension(200, 1000));
        panelEste.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));

        for (int i = 0; i < btts.length; i++) {
            btts[i] = new JButton(txts[i]);
            btts[i].setBackground(new Color(9, 17, 31));
            btts[i].setForeground(Color.white);
            btts[i].setPreferredSize(new Dimension(170, 50));
            btts[i].addActionListener(this);
            if (i > 0) {
                btts[i].setEnabled(false);
            }
            panelEste.add(btts[i]);
        }

        add(panelEste, BorderLayout.EAST);

        panelOeste = new JPanel();
        panelOeste.setBackground(new Color(9, 17, 31, 250));
        add(panelOeste, BorderLayout.WEST);


        bttBack = new JButton("ATRÁS");
        bttBack.setBackground(new Color(9, 17, 31));
        bttBack.setForeground(Color.white);
        bttBack.setPreferredSize(new Dimension(100, 50));
        bttBack.addActionListener(this);

        bttNext = new JButton("SIGUIENTE");
        bttNext.setBackground(new Color(9, 17, 31));
        bttNext.setForeground(Color.white);
        bttNext.setPreferredSize(new Dimension(100, 50));
        bttNext.addActionListener(this);

        panelSur = new JPanel();
        panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT, 500, 10));
        panelSur.setBackground(new Color(9, 17, 31, 250));
        panelSur.add(bttBack);
        panelSur.add(bttNext);
        add(panelSur, BorderLayout.SOUTH);

        initPanelForms();
    }

    private void initPanelForms() {
        tarjetas = new CardLayout();
        panelTarjetas = new JPanel(tarjetas);

        for (int i = 0; i < frms.length; i++) {
            frms[i] = new JPanel();
            frms[i].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            frms[i].setBackground(new Color(9, 17, 31));
            formularios(frms[i], i);
            panelTarjetas.add(frms[i], txts[i]);
        }

        add(panelTarjetas, BorderLayout.CENTER);
        tarjetas.show(panelTarjetas, "BIENVENIDA");
    }

    private void formularios(JPanel f, int i) {

        if (i == 0) {
            tit = new JLabel(txts[i]);
            tit.setPreferredSize(new Dimension(1150, 80));
            tit.setHorizontalAlignment(SwingConstants.CENTER);
            tit.setFont(new Font("Tahoma", Font.BOLD, 50));
            tit.setForeground(Color.white);
            f.add(tit);

            inst = new JLabel("Esta es mi aplicación de registro, aquí las instrucciones:");
            inst.setPreferredSize(new Dimension(1150, 50));
            inst.setFont(new Font("Tahoma", Font.BOLD, 15));
            inst.setForeground(Color.white);
            f.add(inst);

            JTextArea txt = new JTextArea();
            txt.setText("\n\n1. En la pestaña de datos personales, encontrarás: \n\n      -Un campo 'nombre'.\n" +
                    "       -Un campo 'email', en el que debes introducir un E-mail válido.\n" +
                    "       -Un campo 'contraseña', que debe contener entre 8 y 16 caracteres," +
                    " al menos un dígito, al menos una mayúscula,\n         al menos una minúscula y al menos un caracter que no sea letra ni número.\n\n\n" +
                    "2. En la pestaña de país y provincia, encontrarás: \n\n      -Un campo en el que elegir entre España y EEUU.\n" +
                    "       -Un campo en el que elegir provincia u estado del país seleccionado.\n\n\n" +
                    "3. En la pestaña finalizar, entontrarás: \n\n      -El resultado final de todos los datos previamente introducidos.\n" +
                    "       -Una caja de confirmación que permite volcar los datos a un archivo de texto después de seleccionar la ruta.\n\n\n" +
                    "4. En la pestaña de resultado, encontrarás:\n\n        -El resultado de la grabación del fichero.\n" +
                    "       -Un botón para salir de la aplicación.");
            txt.setForeground(Color.white);
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(700, 500));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            inst = new JLabel("Gracias por su atención.");
            inst.setPreferredSize(new Dimension(900, 50));
            inst.setHorizontalAlignment(SwingConstants.RIGHT);
            inst.setFont(new Font("Tahoma", Font.BOLD, 15));
            inst.setForeground(Color.white);
            f.add(inst);
        }

        if(i == 1){
            tit = new JLabel(txts[i]);
            tit.setPreferredSize(new Dimension(1150, 80));
            tit.setHorizontalAlignment(SwingConstants.CENTER);
            tit.setFont(new Font("Tahoma", Font.BOLD, 50));
            tit.setForeground(Color.white);
            f.add(tit);

            JTextArea txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtNombre = new JLabel("Introduce tu nombre:");
            txtNombre.setPreferredSize(new Dimension(200, 50));
            txtNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtNombre.setForeground(Color.white);
            f.add(txtNombre);

            nombre = new JTextField();
            nombre.setPreferredSize(new Dimension(300, 50));
            nombre.setBackground(new Color(9, 17, 31));
            nombre.setForeground(Color.white);
            nombre.setBorder(BorderFactory.createCompoundBorder(
                    nombre.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(nombre);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtEmail = new JLabel("Introduce tu email:");
            txtEmail.setPreferredSize(new Dimension(200, 50));
            txtEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtEmail.setForeground(Color.white);
            f.add(txtEmail);

            email = new JTextField();
            email.setPreferredSize(new Dimension(300, 50));
            email.setBackground(new Color(9, 17, 31));
            email.setForeground(Color.white);
            email.setBorder(BorderFactory.createCompoundBorder(
                    email.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(email);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtContraseña = new JLabel("Introduce tu contraseña:");
            txtContraseña.setPreferredSize(new Dimension(200, 50));
            txtContraseña.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtContraseña.setForeground(Color.white);
            f.add(txtContraseña);

            contraseña = new JPasswordField();
            contraseña.setPreferredSize(new Dimension(300, 50));
            contraseña.setBackground(new Color(9, 17, 31));
            contraseña.setForeground(Color.white);
            contraseña.setBorder(BorderFactory.createCompoundBorder(
                    contraseña.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(contraseña);
        }

        if (i == 2){
            tit = new JLabel(txts[i]);
            tit.setPreferredSize(new Dimension(1150, 80));
            tit.setHorizontalAlignment(SwingConstants.CENTER);
            tit.setFont(new Font("Tahoma", Font.BOLD, 50));
            tit.setForeground(Color.white);
            f.add(tit);

            JTextArea txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            bttEsp = new JButton("ESPAÑA");
            bttEsp.setBackground(new Color(9, 17, 31));
            bttEsp.setForeground(Color.white);
            bttEsp.setPreferredSize(new Dimension(400, 50));
            bttEsp.addActionListener(this);
            f.add(bttEsp);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(100, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            bttEeuu = new JButton("EEUU");
            bttEeuu.setBackground(new Color(9, 17, 31));
            bttEeuu.setForeground(Color.white);
            bttEeuu.setPreferredSize(new Dimension(477, 50));
            bttEeuu.addActionListener(this);
            f.add(bttEeuu);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            españa = new JList<>(esp);
            españa.setPreferredSize(new Dimension(100, 300));
            españa.setBackground(new Color(9, 17, 31));
            españa.setForeground(Color.white);
            españa.setFont(new Font("Tahoma", Font.BOLD, 17));
            españa.setAlignmentX(Component.CENTER_ALIGNMENT);
            españa.setSelectionBackground(Color.white);
            españa.setSelectionForeground(new Color(9, 17, 31));
            españa.setVisible(false);
            f.add(españa);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(575, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            estadosUnidos = new JList<>(eeuu);
            estadosUnidos.setPreferredSize(new Dimension(100, 300));
            estadosUnidos.setBackground(new Color(9, 17, 31));
            estadosUnidos.setForeground(Color.white);
            estadosUnidos.setFont(new Font("Tahoma", Font.BOLD, 17));
            estadosUnidos.setAlignmentX(Component.CENTER_ALIGNMENT);
            estadosUnidos.setSelectionBackground(Color.white);
            estadosUnidos.setSelectionForeground(new Color(9, 17, 31));
            estadosUnidos.setVisible(false);
            f.add(estadosUnidos);

        }

        if (i == 3){
            tit = new JLabel(txts[i]);
            tit.setPreferredSize(new Dimension(1150, 80));
            tit.setHorizontalAlignment(SwingConstants.CENTER);
            tit.setFont(new Font("Tahoma", Font.BOLD, 50));
            tit.setForeground(Color.white);
            f.add(tit);

            JTextArea txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);


        }

        panelTarjetas.add(f, txts[i]);
    }

    private void recogerFicheros() throws IOException {
        File f = new File("Paises/españa.txt");
        File f2 = new File("Paises/eeuu.txt");
        if (f.exists() && f2.exists()){
            BufferedReader fsal = new BufferedReader(new FileReader(f));
            BufferedReader fsal2 = new BufferedReader(new FileReader(f2));

            for (int i = 0; i < 10; i++) {
                String p = fsal.readLine();
                if (!(p == null)){
                    esp[i] = p;
                }
                String p2 = fsal2.readLine();
                if (!(p2 == null)){
                    eeuu[i] = p2;
                }
            }
        }
    }

    private void initPantalla() {
        setLayout(new BorderLayout());
        setTitle("Práctica 2  ||  Víctor Castrillo");
        setSize(1500, 1025);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // x 1500, y 1025

        //GradientPaint gp = new GradientPaint(panelTarjetas.getX(), panelTarjetas.getY() + 27, new Color(62, 95, 138), 1300, 1000,  new Color(9, 17, 31));
        /*GradientPaint gp = new GradientPaint(0, 0, new Color(62, 95, 138), 1500, 1025, new Color(9, 17, 31));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 1500, 1025);*/


        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.lightGray);
        g2d.drawArc(50, 40, 100, 100, 45, -275);

        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.white);
        g2d.drawArc(50, 40, 100, 100, 45, -275);


        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.white);
        g2d.drawLine(100, 100, 125, 65);
        g2d.drawLine(100, 100, 75, 65);

        g2d.setColor(Color.lightGray);
        g2d.drawLine(100, 110, 125, 65);
        g2d.drawLine(100, 110, 75, 65);

        g2d.setColor(Color.white);
        g2d.drawLine(100, 120, 125, 65);
        g2d.drawLine(100, 120, 75, 65);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(1250, 55, 200, 75);

        GradientPaint gp = new GradientPaint(1250, 55, Color.red, 1350, 55, Color.yellow);
        g2d.setPaint(gp);
        g2d.fillRect(1250, 55, 100, 75);

        gp = new GradientPaint(1350, 55, Color.yellow, 1450, 55, Color.red);
        g2d.setPaint(gp);
        g2d.fillRect(1350, 55, 100, 75);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bttNext)) {
            tarjetas.next(panelTarjetas);
            bttBack.setEnabled(!frms[0].isShowing());
            bttNext.setEnabled(!frms[4].isShowing());
            this.pos += 1;
            btts[pos].setEnabled(true);
        }
        if (e.getSource().equals(bttBack)) {
            tarjetas.previous(panelTarjetas);
            bttBack.setEnabled(!frms[0].isShowing());
            bttNext.setEnabled(!frms[4].isShowing());
            this.pos -= 1;
        }

        if (e.getSource().equals(bttEsp)){
            this.pais = "España";
            estadosUnidos.setVisible(false);
            estadosUnidos.clearSelection();
            españa.setVisible(true);
        }

        if (e.getSource().equals(bttEeuu)){
            this.pais = "EEUU";
            españa.setVisible(false);
            españa.clearSelection();
            estadosUnidos.setVisible(true);
        }

        for (int i = 0; i < btts.length; i++) {
            if (e.getSource().equals(btts[i])) {
                tarjetas.show(panelTarjetas, btts[i].getText());
                this.pos = i;
            }
        }

        bttBack.setEnabled(!(pos == 0));
        bttNext.setEnabled(!(pos == 4));
    }
}