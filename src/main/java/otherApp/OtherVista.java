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
import java.util.HashMap;
import java.util.Objects;

class OtherVista {
    public static void vista() {
        final JFrame framePrincipal = new JFrame("Menu principal");
        final JFrame frameComparacion = new JFrame("Comparación");
        final JFrame framePlot = new JFrame("Plot");

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
                    firstMiner.setText("");
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

        framePrincipal.setSize(1000, 1000);
        frameComparacion.setSize(1000, 1000);
        framePlot.setSize(600,600);

        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameComparacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        framePrincipal.setLayout(null);
        frameComparacion.setLayout(null);
        framePlot.setLayout(null);

        framePrincipal.setVisible(true);
    }

    static JPanel plot2DPanel(ArrayList<Double> firstMinerValues,
                              ArrayList<Double> secondMinerValues, String firstMiner,
                              String secondMiner){

//        firstMinerValues = new ArrayList<Double>(){{
//            add(12.2); add(90.3); add(9.45); add(12.2); add(12.2); add(9.45);
//        }};
//        secondMinerValues = new ArrayList<Double>(){{
//            add(30.2); add(97.3); add(22.45); add(22.45); add(22.45); add(30.2);
//        }};

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

        AxesChartSeries firstSeries = chart.addSeries("Miner: " + firstMiner, arrayMinersValues, arrayMinersValues);
        AxesChartSeries secondSeries = chart.addSeries("Miner: " + secondMiner, secondArrayMinersValues, secondArrayMinersValues);

        firstSeries.setCustomToolTips(true);
        firstSeries.setToolTips(firstTooltips);

        secondSeries.setCustomToolTips(true);
        secondSeries.setToolTips(secondTooltips);

        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        return chartPanel;
    }

}
