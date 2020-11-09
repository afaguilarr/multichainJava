package otherApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class OtherVista {
    public static void vista() {
        final JFrame framePrincipal = new JFrame("Menu principal");
        final JFrame frameComparacion = new JFrame("Comparación");

        JButton volverPrincipal = new JButton("< Volver");
        volverPrincipal.setBounds(250, 650, 200, 50);
        volverPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameComparacion.setVisible(false);
                framePrincipal.setVisible(true);
            }
        });

        JButton goToComparacion = new JButton("Graficar comparación creciente value_usd");
        goToComparacion.setBounds(250, 500, 500, 50);
        goToComparacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToComparacion.setVisible(true);
                goToComparacion.setVisible(false);
                goToComparacion.add(volverPrincipal);
            }
        });

        JLabel labelFirstMiner = new JLabel("Ingrese el primer miner:");
        labelFirstMiner.setBounds(150, 245, 300, 30);
        JTextField firstMiner = new JTextField();
        firstMiner.setBounds(130, 280, 300, 40);

        JLabel labelSecondMiner = new JLabel("Ingrese el segundo miner:");
        labelSecondMiner.setBounds(150, 445, 300, 30);
        JTextField secondMiner = new JTextField();
        secondMiner.setBounds(130, 480, 300, 40);

        JLabel labelInitialTime = new JLabel("Ingrese la hora inicial:");
        labelInitialTime.setBounds(550, 245, 300, 30);
        JTextField initialTime = new JTextField();
        initialTime.setBounds(530, 280, 300, 40);

        JLabel labelFinalTime = new JLabel("Ingrese la hora final:");
        labelFinalTime.setBounds(550, 445, 300, 30);
        JTextField finalTime = new JTextField();
        finalTime.setBounds(530, 480, 300, 40);

        JButton graficar = new JButton("Graficar");
        graficar.setBounds(550, 650, 200, 50);
        graficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Double> firstMinerValues = OracleDatabase.getMinerValueUSDList(firstMiner.getText(),
                            initialTime.getText(), finalTime.getText());
                    ArrayList<Double> secondMinerValues = OracleDatabase.getMinerValueUSDList(secondMiner.getText(),
                            initialTime.getText(), finalTime.getText());
                    firstMiner.setText("");
                    secondMiner.setText("");
                    initialTime.setText("");
                    finalTime.setText("");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(framePrincipal, "Hubo un error");
                }
            }
        });

        framePrincipal.add(goToComparacion);
        frameComparacion.add(labelFirstMiner);
        frameComparacion.add(firstMiner);
        frameComparacion.add(labelSecondMiner);
        frameComparacion.add(secondMiner);
        frameComparacion.add(graficar);

        framePrincipal.setSize(1000, 1000);
        frameComparacion.setSize(1000, 1000);

        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameComparacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        framePrincipal.setLayout(null);
        frameComparacion.setLayout(null);

        framePrincipal.setVisible(true);
    }
}
