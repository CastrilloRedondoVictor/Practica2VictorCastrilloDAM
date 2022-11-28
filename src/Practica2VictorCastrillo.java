import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Practica2VictorCastrillo extends JFrame implements ActionListener {
    CardLayout tarjetas;
    JPanel panelTarjetas, panelNorte, panelSur, panelEste, panelOeste;

    String[] esp = new String[10];
    String[] eeuu = new String[10];

    JList<String> españa, estadosUnidos;

    String pais = "";
    String provincia = "";
    String nom = "";
    String em = "";
    String cont = "";

    JButton bttEsp, bttEeuu;

    JPanel[] frms = new JPanel[5];
    String[] txts = {"BIENVENIDA", "DATOS PERSONALES", "PAÍS Y PROVINCIA", "FINALIZAR", "RESULTADO"};
    JButton[] btts = new JButton[5];
    JButton bttNext, bttBack;

    JLabel txtNombre, txtEmail, txtContraseña, txtRes;
    JTextField nombre, email, contraseña, resNom, resEm, resCont, resPa, resPro;
    int pos = 0;

    JLabel titulo, tit, inst;

    JButton elegirFichero;

    JLabel txtFinal;

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
            nombre.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    warn();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    warn();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    warn();
                }

                public void warn(){
                    nom = nombre.getText();
                    resNom.setText(nom);
                }
            });
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
            email.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    warn();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    warn();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    warn();
                }

                public void warn(){
                    em = email.getText();
                    resEm.setText(em);
                }
            });
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
            contraseña.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    warn();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    warn();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    warn();
                }

                public void warn(){
                    cont = contraseña.getText();
                    resCont.setText(cont);
                }
            });
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
            españa.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    provincia = españa.getSelectedValue();
                    resPro.setText(provincia);
                }
            });
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
            estadosUnidos.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    provincia = estadosUnidos.getSelectedValue();
                    resPro.setText(provincia);
                }
            });
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

            txtRes = new JLabel("Nombre:");
            txtRes.setPreferredSize(new Dimension(200, 50));
            txtRes.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtRes.setForeground(Color.white);
            f.add(txtRes);

            resNom = new JTextField();
            resNom.setPreferredSize(new Dimension(300, 50));
            resNom.setBackground(new Color(9, 17, 31));
            resNom.setForeground(Color.white);
            resNom.setText(nom);
            resNom.setEditable(false);
            resNom.setBorder(BorderFactory.createCompoundBorder(
                    resNom.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(resNom);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 25));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtRes = new JLabel("Email:");
            txtRes.setPreferredSize(new Dimension(200, 50));
            txtRes.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtRes.setForeground(Color.white);
            f.add(txtRes);

            resEm = new JTextField();
            resEm.setPreferredSize(new Dimension(300, 50));
            resEm.setBackground(new Color(9, 17, 31));
            resEm.setForeground(Color.white);
            resEm.setText(em);
            resEm.setEditable(false);
            resEm.setBorder(BorderFactory.createCompoundBorder(
                    resEm.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(resEm);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 25));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtRes = new JLabel("Contraseña:");
            txtRes.setPreferredSize(new Dimension(200, 50));
            txtRes.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtRes.setForeground(Color.white);
            f.add(txtRes);

            resCont = new JPasswordField();
            resCont.setPreferredSize(new Dimension(300, 50));
            resCont.setBackground(new Color(9, 17, 31));
            resCont.setForeground(Color.white);
            resCont.setText(cont);
            resCont.setEditable(false);
            resCont.setBorder(BorderFactory.createCompoundBorder(
                    resCont.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(resCont);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 25));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtRes = new JLabel("País:");
            txtRes.setPreferredSize(new Dimension(200, 50));
            txtRes.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtRes.setForeground(Color.white);
            f.add(txtRes);

            resPa = new JTextField();
            resPa.setPreferredSize(new Dimension(300, 50));
            resPa.setBackground(new Color(9, 17, 31));
            resPa.setForeground(Color.white);
            resPa.setText(pais);
            resPa.setEditable(false);
            resPa.setBorder(BorderFactory.createCompoundBorder(
                    resPa.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(resPa);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 25));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtRes = new JLabel("Provincia / Estado:");
            txtRes.setPreferredSize(new Dimension(200, 50));
            txtRes.setFont(new Font("Tahoma", Font.BOLD, 15));
            txtRes.setForeground(Color.white);
            f.add(txtRes);

            resPro = new JTextField();
            resPro.setPreferredSize(new Dimension(300, 50));
            resPro.setBackground(new Color(9, 17, 31));
            resPro.setForeground(Color.white);
            resPro.setText(provincia);
            resPro.setEditable(false);
            resPro.setBorder(BorderFactory.createCompoundBorder(
                    resPro.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            f.add(resPro);

            txt = new JTextArea();
            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 50));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            elegirFichero = new JButton("ELEGIR FICHERO");
            elegirFichero.setBackground(new Color(9, 17, 31));
            elegirFichero.setForeground(Color.white);
            elegirFichero.setPreferredSize(new Dimension(477, 50));
            elegirFichero.setEnabled(false);
            elegirFichero.addActionListener(this);
            f.add(elegirFichero);


        }

        if (i == 4){
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

            txt.setBackground(new Color(9, 17, 31, 0));
            txt.setPreferredSize(new Dimension(1150, 150));
            txt.setLineWrap(true);
            txt.setEditable(false);
            f.add(txt);

            txtFinal = new JLabel("TE HAS REGISTRADO CON ÉXITO");
            txtFinal.setPreferredSize(new Dimension(1150, 50));
            txtFinal.setHorizontalAlignment(SwingConstants.CENTER);
            txtFinal.setFont(new Font("Tahoma", Font.BOLD, 50));
            txtFinal.setVisible(false);
            txtFinal.setForeground(Color.white);
            f.add(txtFinal);
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

    private boolean comprobarNombre(){
        Pattern pat = Pattern.compile("^[a-zA-Z]+$");
        Matcher mat = pat.matcher(resNom.getText().trim());
        if (mat.matches()){
            resNom.setBorder(new LineBorder(Color.white));
            resNom.setBorder(BorderFactory.createCompoundBorder(
                    resNom.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            return true;
        }
        resNom.setBorder(new LineBorder(Color.red));
        return false;
    }

    private boolean comprobarEmail(){
        Pattern pat = Pattern.compile("^[A-Za-z0-9]+@[a-zA-Z]+.[a-zA-Z]+$");
        Matcher mat = pat.matcher(resEm.getText().trim());
        if (mat.matches()){
            resEm.setBorder(new LineBorder(Color.white));
            resEm.setBorder(BorderFactory.createCompoundBorder(
                    resEm.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            return true;
        }
        resEm.setBorder(new LineBorder(Color.red));
        return false;
    }

    private boolean comprobarContraseña() {
        Pattern pat = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&+=])(?=\\S+$).{8,16}$");
        Matcher mat = pat.matcher(resCont.getText().trim());
        if (mat.matches()) {
            resCont.setBorder(new LineBorder(Color.white));
            resCont.setBorder(BorderFactory.createCompoundBorder(
                    resCont.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            return true;
        }
        resCont.setBorder(new LineBorder(Color.red));
        return false;
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
            resPa.setText(pais);
            estadosUnidos.setVisible(false);
            estadosUnidos.clearSelection();
            españa.setVisible(true);
        }

        if (e.getSource().equals(bttEeuu)){
            this.pais = "EEUU";
            resPa.setText(pais);
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

        if (e.getSource().equals(elegirFichero)){
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(fileChooser);
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                File f = new File(ruta);
                FileWriter fil = new FileWriter(f);
                fil.write("NOMBRE: " + resNom.getText() + "\n");
                fil.write("EMAIL: " + resEm.getText() + "\n");
                fil.write("CONTRASEÑA: " + resCont.getText() + "\n");
                fil.write("PAÍS: " + resPa.getText() + "\n");
                fil.write("PROVINCIA / ESTADO: " + resPro.getText() + "\n");
                fil.close();
                txtFinal.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

        bttBack.setEnabled(!(pos == 0));
        bttNext.setEnabled(!(pos == 4));


        elegirFichero.setEnabled(comprobarNombre() && comprobarEmail() && comprobarContraseña() &&
                (resPa.getText().trim().length() > 0) && (resPro.getText().trim().length() > 0));
    }
}