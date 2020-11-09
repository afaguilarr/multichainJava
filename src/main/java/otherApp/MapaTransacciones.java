package otherApp;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class MapaTransacciones extends JFrame {
    public JPanel content;
    public ArrayList<Cuadrado> cuadrados;
    public ArrayList<Transaction> transacciones;
    public ArrayList<Color> colors;


    public MapaTransacciones(String initialTime, String finalTime, int size, ArrayList<Color> colors) {
        this.cuadrados = Cuadrado.generarCuadrados(size);
        this.transacciones = OracleDatabase.getTransactionsByTime(initialTime, finalTime);
        this.colors = colors;
        Cuadrado.asignarTransacciones(this.cuadrados, this.transacciones);
        Cuadrado.asignarColores(this.cuadrados, this.colors);
        initComponents();
    }

    public ArrayList<Transaction> getTransaccionesByDate(int index) {
        for (Cuadrado cuadrado : this.cuadrados) {
            if (cuadrado.numero == index) {
                Collections.sort(cuadrado.transacciones, new Comparator<Transaction>() {
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.date.compareTo(t2.date);
                    }
                });
                return cuadrado.transacciones;
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<Transaction> getTransaccionesByValueUsd(int index) {
        for (Cuadrado cuadrado : this.cuadrados) {
            if (cuadrado.numero == index) {
                Collections.sort(cuadrado.transacciones, new Comparator<Transaction>() {
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.value_usd.compareTo(t2.value_usd);
                    }
                });
                return cuadrado.transacciones;
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<Transaction> getTransaccionesByFeeUsd(int index) {
        for (Cuadrado cuadrado : this.cuadrados) {
            if (cuadrado.numero == index) {
                Collections.sort(cuadrado.transacciones, new Comparator<Transaction>() {
                    public int compare(Transaction t1, Transaction t2) {
                        return t1.fee_usd.compareTo(t2.fee_usd);
                    }
                });
                return cuadrado.transacciones;
            }
        }
        return new ArrayList<>();
    }

    private void initComponents() {
        content = new Content(this.cuadrados);
        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.setContentPane(content);
        pack();
    }

    static class Content extends JPanel {
        ArrayList<Cuadrado> cuadrados;

        Content(ArrayList<Cuadrado> cuadrados) {
            this.cuadrados = cuadrados;
            setPreferredSize(new Dimension(1000, 1000));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int factor = 9;

            g.drawRect(50, 50, 100 * factor, 100 * factor);

            for (Cuadrado cuadrado : cuadrados) {

                String labelCuadrado = "Numero: " + cuadrado.numero;
                String transacciones = "Transacciones: " + cuadrado.transacciones.size();
                String valueUsd = "Total value_usd: " + cuadrado.totalValueUsd();
                String feeUsd = "Total fee_usd: " + cuadrado.totalFeeUsd();

                g.setColor(cuadrado.color);
                g.fillRect(cuadrado.x * factor + 50, cuadrado.y * factor + 50, cuadrado.width * factor, cuadrado.height * factor); // tiene offset

                g.setColor(java.awt.Color.black);
                g.drawRect(cuadrado.x * factor + 50, cuadrado.y * factor + 50, cuadrado.width * factor, cuadrado.height * factor); // tiene offset

                g.drawString(labelCuadrado, cuadrado.x * factor + 50 + 5, cuadrado.y * factor + 50 + 20);
                g.drawString(transacciones, cuadrado.x * factor + 50 + 5, cuadrado.y * factor + 50 + 40);
                g.drawString(valueUsd, cuadrado.x * factor + 50 + 5, cuadrado.y * factor + 50 + 60);
                g.drawString(feeUsd, cuadrado.x * factor + 50 + 5, cuadrado.y * factor + 50 + 80);

                if (cuadrado.x == 0) {
                    g.drawString(Integer.toString(cuadrado.y), cuadrado.x + 50 - 20, cuadrado.y * factor + 50);
                }
                if (cuadrado.y == 0) {
                    g.drawString(Integer.toString(cuadrado.x), cuadrado.x * factor + 50, cuadrado.y + 50 - 20);
                }
            }

        }
    }
}
