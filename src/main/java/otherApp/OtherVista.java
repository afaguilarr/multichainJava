package otherApp;

import App.MultiChainAPI;
import App.OracleDatabase;
import App.Usuario;
import multichain.command.MultichainException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.Objects;

class OtherVista {
    static Usuario usuarioEnSesion = null;

    public static void vista() throws MultichainException {
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

//        JButton goToComparacion = new JButton("Graficar comparación creciente value_usd");
//        goToComparacion.setBounds(250, 500, 500, 50);
//        goToComparacion.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                goToComparacion.setVisible(true);
//                goToComparacion.setVisible(false);
//                goToComparacion.add(volverPrincipal);
//            }
//        });
//
//
//
//        JButton graficar = new JButton("Graficar");
//        graficar.setBounds(550, 650, 200, 50);
//        graficar.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    OracleDatabase.insertUsuario(nombreUsuarioRegistro.getText(), contrasenaRegistro.getText());
//                    nombreUsuarioRegistro.setText("");
//                    contrasenaRegistro.setText("");
//                    frameRegistro.setVisible(false);
//                    framePrincipal.setVisible(true);
//                    JOptionPane.showMessageDialog(framePrincipal, "El usuario fue registrado exitosamente.");
//                } catch (InvalidParameterException invalidParameterException){
//                    JOptionPane.showMessageDialog(framePrincipal, "El nombre de usuario ingresado ya existe.");
//                } catch (Exception exception) {
//                    JOptionPane.showMessageDialog(framePrincipal, "El registro falló D:");
//                }
//            }
//        });
//
//        JLabel labelNombreUsuarioPago = new JLabel("Ingrese el nombre del usuario al que se enviaran los activos:");
//        labelNombreUsuarioPago.setBounds(350, 245, 300, 30);
//        JTextField nombreUsuarioPago = new JTextField();
//        nombreUsuarioPago.setBounds(330, 280, 300, 40);
//
//        JLabel labelCantidadText = new JLabel("Ingrese el nombre del usuario al que se enviaran los activos:");
//        labelCantidadText.setBounds(350, 445, 300, 30);
//        JTextField cantidadText = new JTextField();
//        cantidadText.setBounds(330, 480, 300, 40);
//
//        JButton pagar = new JButton("Pagar");
//        pagar.setBounds(550, 650, 200, 50);
//        pagar.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String direccionText = Objects.requireNonNull(
//                            OracleDatabase.getUsuarioByNombreUsuario(nombreUsuarioPago.getText())).direccion;
//                    MultiChainAPI.sendAssetFrom(usuarioEnSesion.direccion, direccionText,
//                            cantidadText.getText());
//                    nombreUsuarioPago.setText("");
//                    cantidadText.setText("");
//                    JOptionPane.showMessageDialog(framePrincipal, "El pago fue efectuado correctamente.");
//                } catch (NullPointerException nullPointerException) {
//                    JOptionPane.showMessageDialog(framePrincipal, "El usuario no existe.");
//                } catch (Exception exception){
//                    JOptionPane.showMessageDialog(framePrincipal, "El pago falló D:");
//                }
//            }
//        });
//
//        framePrincipal.add(goToRegistro);
//
//        framePrincipal.setSize(1000, 1000);
//
//
//        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        framePrincipal.setLayout(null);
//
//
//        framePrincipal.setVisible(true);

    }
}
