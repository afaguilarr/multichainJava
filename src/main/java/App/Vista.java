package App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.Objects;

class Vista {
    static Usuario usuarioEnSesion = null;

    public static void vista() {
        final JFrame framePrincipal = new JFrame("Menu principal");
        final JFrame frameRegistro = new JFrame("Registro");
        final JFrame frameLogin = new JFrame("Login");
        final JFrame frameSesion = new JFrame("Bienvenido");
        final JFrame frameSaldo = new JFrame("Saldo");
        final JFrame framePago = new JFrame("Pagos");

        JButton volverPrincipal = new JButton("< Volver");
        volverPrincipal.setBounds(250, 750, 500, 50);
        volverPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameRegistro.setVisible(false);
                frameLogin.setVisible(false);
                framePrincipal.setVisible(true);
            }
        });

        JButton volverSesion = new JButton("< Volver");
        volverSesion.setBounds(250, 750, 500, 50);
        volverSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameSaldo.setVisible(false);
                framePago.setVisible(false);
                frameSesion.setVisible(true);
            }
        });

        JButton goToRegistro = new JButton("Registrarse");
        goToRegistro.setBounds(250, 350, 500, 50);
        goToRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameRegistro.setVisible(true);
                framePrincipal.setVisible(false);
            }
        });

        JButton goToLogin = new JButton("Login");
        goToLogin.setBounds(250, 750, 500, 50);
        goToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameLogin.setVisible(true);
                framePrincipal.setVisible(false);
            }
        });

        JButton goToSaldo = new JButton("Consultar saldo");
        goToSaldo.setBounds(250, 350, 500, 50);
        goToSaldo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameSaldo.setVisible(true);
                frameSesion.setVisible(false);
            }
        });

        JButton goToPago = new JButton("Pagar");
        goToPago.setBounds(250, 550, 500, 50);
        goToPago.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                framePago.setVisible(true);
                frameSesion.setVisible(false);
            }
        });

        JButton cerrarSesion = new JButton("Cerrar sesión");
        cerrarSesion.setBounds(250, 750, 500, 50);
        cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usuarioEnSesion.direccion = null;
                frameSesion.setVisible(false);
                framePrincipal.setVisible(true);
            }
        });

        JLabel labelNombreUsuarioRegistro = new JLabel("Ingrese el nombre de usuario:");
        labelNombreUsuarioRegistro.setBounds(620, 245, 300, 30);
        JTextField nombreUsuarioRegistro = new JTextField();
        nombreUsuarioRegistro.setBounds(600, 280, 300, 40);

        JLabel labelContrasenaRegistro = new JLabel("Ingrese la contraseña del usuario:");
        labelContrasenaRegistro.setBounds(620, 445, 300, 30);
        JTextField contrasenaRegistro = new JTextField();
        contrasenaRegistro.setBounds(600, 480, 300, 40);

        JButton registrarse = new JButton("Registrarse");
        registrarse.setBounds(550, 750, 500, 50);
        registrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    OracleDatabase.insertUsuario(nombreUsuarioRegistro.getText(), contrasenaRegistro.getText());
                    nombreUsuarioRegistro.setText("");
                    contrasenaRegistro.setText("");
                    frameRegistro.setVisible(false);
                    framePrincipal.setVisible(true);
                    JOptionPane.showMessageDialog(framePrincipal, "El usuario fue registrado exitosamente.");
                } catch (InvalidParameterException invalidParameterException){
                    JOptionPane.showMessageDialog(framePrincipal, "El nombre de usuario ingresado ya existe.");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(framePrincipal, "El registro falló D:");
                }
            }
        });

        JLabel labelNombreUsuarioLogin = new JLabel("Ingrese el nombre de usuario:");
        labelNombreUsuarioLogin.setBounds(620, 245, 300, 30);
        JTextField nombreUsuarioLogin = new JTextField();
        nombreUsuarioLogin.setBounds(600, 280, 300, 40);

        JLabel labelContrasenaLogin = new JLabel("Ingrese la contraseña del usuario:");
        labelContrasenaLogin.setBounds(620, 445, 300, 30);
        JTextField contrasenaLogin = new JTextField();
        contrasenaLogin.setBounds(600, 480, 300, 40);

        JButton login = new JButton("Login");
        login.setBounds(550, 750, 500, 50);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    usuarioEnSesion = OracleDatabase.getUsuario(nombreUsuarioLogin.getText(), contrasenaLogin.getText());
                    if (usuarioEnSesion == null) {
                        JOptionPane.showMessageDialog(framePrincipal, "Credenciales incorrectas.");
                    } else{
                        nombreUsuarioLogin.setText("");
                        contrasenaLogin.setText("");
                        frameLogin.setVisible(false);
                        frameSesion.setVisible(true);
                        JOptionPane.showMessageDialog(framePrincipal, "Loggeado correctamente.");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(framePrincipal, "El login falló D:");
                }
            }
        });

        JLabel labelNombreUsuarioPago = new JLabel("Ingrese el nombre del usuario al que se enviaran los activos:");
        labelNombreUsuarioPago.setBounds(620, 245, 300, 30);
        JTextField nombreUsuarioPago = new JTextField();
        nombreUsuarioPago.setBounds(600, 280, 300, 40);

        JLabel labelCantidadText = new JLabel("Ingrese el nombre del usuario al que se enviaran los activos:");
        labelCantidadText.setBounds(620, 445, 300, 30);
        JTextField cantidadText = new JTextField();
        cantidadText.setBounds(600, 480, 300, 40);

        JButton pagar = new JButton("Pagar");
        pagar.setBounds(550, 750, 500, 50);
        pagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String direccionText = Objects.requireNonNull(
                            OracleDatabase.getUsuarioByNombreUsuario(nombreUsuarioPago.getText())).direccion;
                    MultiChainAPI.sendAssetFrom(usuarioEnSesion.direccion, direccionText,
                            cantidadText.getText());
                    nombreUsuarioPago.setText("");
                    cantidadText.setText("");
                    JOptionPane.showMessageDialog(framePrincipal, "El pago fue efectuado correctamente.");
                } catch (NullPointerException nullPointerException) {
                    JOptionPane.showMessageDialog(framePrincipal, "El usuario no existe.");
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(framePrincipal, "El pago falló D:");
                }
            }
        });

        framePrincipal.add(goToRegistro);
        framePrincipal.add(goToLogin);

        frameRegistro.add(labelNombreUsuarioRegistro);
        frameRegistro.add(nombreUsuarioRegistro);
        frameRegistro.add(labelContrasenaRegistro);
        frameRegistro.add(contrasenaRegistro);
        frameRegistro.add(volverPrincipal);
        frameRegistro.add(registrarse);

        frameLogin.add(labelNombreUsuarioLogin);
        frameLogin.add(nombreUsuarioLogin);
        frameLogin.add(labelContrasenaLogin);
        frameLogin.add(contrasenaLogin);
        frameLogin.add(volverPrincipal);
        frameLogin.add(login);

        frameSesion.add(goToSaldo);
        frameSesion.add(goToPago);

        frameSaldo.add(volverSesion);

        framePago.add(labelNombreUsuarioPago);
        framePago.add(nombreUsuarioPago);
        framePago.add(labelCantidadText);
        framePago.add(cantidadText);
        framePago.add(volverSesion);
        framePago.add(pagar);

        framePrincipal.setSize(1000, 1000);
        frameRegistro.setSize(1000, 1000);
        frameLogin.setSize(1000, 1000);
        frameSesion.setSize(1000, 1000);
        frameSaldo.setSize(1000, 1000);
        framePago.setSize(1000, 1000);

        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSaldo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePago.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        framePrincipal.setLayout(null);
        frameRegistro.setLayout(null);
        frameLogin.setLayout(null);
        frameSesion.setLayout(null);
        frameSaldo.setLayout(null);
        framePago.setLayout(null);

        framePrincipal.setVisible(true);
        frameRegistro.setVisible(false);
        frameLogin.setVisible(false);
        frameSesion.setVisible(false);
        frameSaldo.setVisible(false);
        framePago.setVisible(false);
    }
}
