package otherApp;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.series.AxesChartSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

class OtherVista {
    public static void vista() {
        final JFrame framePrincipal = new JFrame("Menu principal");
        final JFrame frameComparacion = new JFrame("Comparación");
        final JFrame frameTransacciones = new JFrame("Mapa Transacciones");

        JButton volverPrincipal2 = new JButton("< Volver");
        volverPrincipal2.setBounds(250, 650, 200, 50);
        volverPrincipal2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameTransacciones.setVisible(false);
                framePrincipal.setVisible(true);
            }
        });

        JButton goToTransacciones = new JButton("Ver mapa por nro. de transacciones");
        goToTransacciones.setBounds(250, 200, 500, 50);
        goToTransacciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameTransacciones.setVisible(true);
                framePrincipal.setVisible(false);
                frameComparacion.add(volverPrincipal2);
            }
        });

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
                frameComparacion.setVisible(true);
                framePrincipal.setVisible(false);
                frameComparacion.add(volverPrincipal);
            }
        });

        JLabel labelColorScale = new JLabel("Configurar escala para nro. de transacciones:");
        labelColorScale.setBounds(550, 245, 300, 30);
        JTextArea colorScale = new JTextArea();
        colorScale.setBounds(530, 280, 300, 200);

        JLabel labelInitialTime2 = new JLabel("Hora y minuto inicial (formato hh24:mi):");
        labelInitialTime2.setBounds(150, 145, 300, 30);
        JTextField initialTime2 = new JTextField();
        initialTime2.setBounds(130, 180, 300, 40);

        JLabel labelFinalTime2 = new JLabel("Hora y minuto final (formato hh24:mi):");
        labelFinalTime2.setBounds(150, 245, 300, 30);
        JTextField finalTime2 = new JTextField();
        finalTime2.setBounds(130, 280, 300, 40);

        JLabel labelCuadriculaSize = new JLabel("Tamaño lado cuadricula:");
        labelCuadriculaSize.setBounds(150, 345, 300, 30);
        JTextField cuadriculaSize = new JTextField();
        cuadriculaSize.setBounds(130, 380, 300, 40);

        JButton graficarTransacciones = new JButton("Ver mapa por nro. de transacciones");
        graficarTransacciones.setBounds(470, 500, 400, 50);
        graficarTransacciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Color> colors = new ArrayList<>();
                    String[] lineas = colorScale.getText().split("\n");
                    int index = 1;

                    for(String linea: lineas){
                        String[] valores = linea.split(",");
                        try{
                            colors.add(new Color(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]), index));
                        }catch(NumberFormatException | IndexOutOfBoundsException exception){
                            colors.add(new Color(Integer.parseInt(valores[0]), Integer.MAX_VALUE, index));
                        }
                        index ++;
                    }

                    new MapaTransacciones(initialTime2.getText(), finalTime2.getText(), Integer.parseInt(cuadriculaSize.getText()), colors).setVisible(true);


//                    colorScale.setText("");
//                    cuadriculaSize.setText("");
//                    initialTime2.setText("");
//                    finalTime2.setText("");

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(framePrincipal, "Hubo un error " + exception.getMessage());
                }
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

        JLabel labelInitialTime = new JLabel("Ingrese la hora inicial (formato hh24:mi):");
        labelInitialTime.setBounds(550, 245, 300, 30);
        JTextField initialTime = new JTextField();
        initialTime.setBounds(530, 280, 300, 40);

        JLabel labelFinalTime = new JLabel("Ingrese la hora final (formato hh24:mi):");
        labelFinalTime.setBounds(550, 445, 300, 30);
        JTextField finalTime = new JTextField();
        finalTime.setBounds(530, 480, 300, 40);

        JButton graficar = new JButton("Graficar");
        graficar.setBounds(550, 650, 200, 50);
        graficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    final JFrame framePlot = new JFrame("Plot");
                    framePlot.setSize(600,600);
                    framePlot.setLayout(null);
                    ArrayList<Double> firstMinerValues = OracleDatabase.getMinerValueUSDList(firstMiner.getText(),
                            initialTime.getText(), finalTime.getText());
                    ArrayList<Double> secondMinerValues = OracleDatabase.getMinerValueUSDList(secondMiner.getText(),
                            initialTime.getText(), finalTime.getText());
                    framePlot.setLayout(new BorderLayout());
                    framePlot.add(plot2DPanel(Objects.requireNonNull(
                            firstMinerValues), secondMinerValues,
                            firstMiner.getText(), secondMiner.getText()),
                            BorderLayout.CENTER);
                    framePlot.pack();
                    framePlot.setVisible(true);
                    secondMiner.setText("");
                    initialTime.setText("");
                    finalTime.setText("");

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(framePrincipal, "Hubo un error " + exception.getMessage());
                }
            }
        });

        framePrincipal.add(goToComparacion);
        frameComparacion.add(labelFirstMiner);
        frameComparacion.add(firstMiner);
        frameComparacion.add(labelSecondMiner);
        frameComparacion.add(secondMiner);
        frameComparacion.add(labelInitialTime);
        frameComparacion.add(initialTime);
        frameComparacion.add(labelFinalTime);
        frameComparacion.add(finalTime);
        frameComparacion.add(graficar);

        frameTransacciones.add(colorScale);
        frameTransacciones.add(labelColorScale);
        frameTransacciones.add(initialTime2);
        frameTransacciones.add(labelInitialTime2);
        frameTransacciones.add(finalTime2);
        frameTransacciones.add(labelFinalTime2);
        frameTransacciones.add(cuadriculaSize);
        frameTransacciones.add(labelCuadriculaSize);
        frameTransacciones.add(graficarTransacciones);
        framePrincipal.add(goToTransacciones);

        framePrincipal.setSize(1000, 1000);
        frameComparacion.setSize(1000, 1000);
        frameTransacciones.setSize(1000, 1000);

        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameComparacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTransacciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        framePrincipal.setLayout(null);
        frameComparacion.setLayout(null);
        frameTransacciones.setLayout(null);

        framePrincipal.setVisible(true);
    }

    static JPanel plot2DPanel(ArrayList<Double> firstMinerValues,
                              ArrayList<Double> secondMinerValues, String firstMiner,
                              String secondMiner){

        ArrayList<String> resultListString = new ArrayList<String>();
        for (int i = 0; i < firstMinerValues.size(); i++){
            int contador = 1;
            for (int j = i + 1; j < firstMinerValues.size(); j++){
                if (firstMinerValues.get(i).equals(firstMinerValues.get(j))){
                    firstMinerValues.remove(j);
                    contador++;
                    j--;
                }
            }
            if (contador == 1){
                resultListString.add(Double.toString(firstMinerValues.get(i)));
            } else {
                resultListString.add("(" + firstMinerValues.get(i) + "," + contador + ")");
            }
        }

        double[] arrayMinersValues = new double[firstMinerValues.size()];
        String[] firstTooltips = new String[firstMinerValues.size()];

        int i = 0;
        for (Double number:firstMinerValues){
            arrayMinersValues[i] = number;
            i++;
        }

        int ii = 0;
        for (String string:resultListString){
            firstTooltips[ii] = string;
            ii++;
        }

        ArrayList<String> secondResultListString = new ArrayList<String>();
        for (int j = 0; j < secondMinerValues.size(); j++){
            int contador = 1;
            for (int k = j + 1; k < secondMinerValues.size(); k++){
                if (secondMinerValues.get(j).equals(secondMinerValues.get(k))){
                    secondMinerValues.remove(k);
                    contador++;
                    k--;
                }
            }
            if (contador == 1){
                secondResultListString.add(Double.toString(secondMinerValues.get(j)));
            } else {
                secondResultListString.add("(" + secondMinerValues.get(j) + "," + contador + ")");
            }
        }

        double[] secondArrayMinersValues = new double[secondMinerValues.size()];
        String[] secondTooltips = new String[secondMinerValues.size()];

        int j = 0;
        for (Double number:secondMinerValues){
            secondArrayMinersValues[j] = number;
            j++;
        }

        int jj = 0;
        for (String string:secondResultListString){
            secondTooltips[jj] = string;
            jj++;
        }

        final XYChart chart = new XYChartBuilder().width(800).height(800).title("Comparación")
                .xAxisTitle("Value_usd").yAxisTitle("Value_usd").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipsAlwaysVisible(true);

        if (arrayMinersValues.length > 0){
            AxesChartSeries firstSeries = chart.addSeries("Miner: " + firstMiner, arrayMinersValues, arrayMinersValues);
            firstSeries.setCustomToolTips(true);
            firstSeries.setToolTips(firstTooltips);
        }

        if (secondArrayMinersValues.length > 0){
            AxesChartSeries secondSeries = chart.addSeries("Miner: " + secondMiner, secondArrayMinersValues, secondArrayMinersValues);
            secondSeries.setCustomToolTips(true);
            secondSeries.setToolTips(secondTooltips);
        }

        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        return chartPanel;
    }

}
