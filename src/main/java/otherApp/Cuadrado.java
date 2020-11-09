package otherApp;

import java.util.ArrayList;

class Cuadrado {
    int x, y, height, width;
    ArrayList<Transaction> transacciones;
    int numero;
    java.awt.Color color;

    Cuadrado(int x, int y, int size, int numero) {
        this.x = x;
        this.y = y;
        this.numero = numero;
        this.transacciones = new ArrayList<>();

        if (this.x + size > 100) {
            this.width = 100 - this.x;
        } else {
            this.width = size;
        }

        if (this.y + size > 100) {
            this.height = 100 - this.y;
        } else {
            this.height = size;
        }
    }

    public boolean contieneTransaccion(Transaction transaction) {
        return (transaction.x >= this.x && transaction.x <= this.x + this.width) &&
                (transaction.y >= this.y && transaction.y <= this.y + this.height);
    }

    public void agregarTransaccion(Transaction transaccion) {
        this.transacciones.add(transaccion);
    }

    public Double totalValueUsd() {
        Double total = 0.0;
        for(Transaction transaction:transacciones){
            total += transaction.value_usd;
        }
        return total;
    }

    public Double totalFeeUsd() {
        Double total = 0.0;
        for(Transaction transaction:transacciones){
            total += transaction.fee_usd;
        }
        return total;
    }

    static ArrayList<Cuadrado> generarCuadrados(Integer size) {
        ArrayList<Cuadrado> cuadrados = new ArrayList<>();
        int numero = 1;
        for (int x = 0; x < 100; x += size) {
            for (int y = 0; y < 100; y += size) {
                cuadrados.add(new Cuadrado(x, y, size, numero));
                numero ++;
            }
        }
        return cuadrados;
    }

    static void asignarTransacciones(ArrayList<Cuadrado> cuadrados, ArrayList<Transaction> transacciones) {
        for (Cuadrado cuadrado : cuadrados) {
            for (Transaction transaction : transacciones) {
                if (cuadrado.contieneTransaccion(transaction)) {
                    cuadrado.agregarTransaccion(transaction);
                }
            }
        }
    }

    static void asignarColores(ArrayList<Cuadrado> cuadrados, ArrayList<Color> colors) {
        for (Cuadrado cuadrado : cuadrados) {
            for (Color color : colors) {
                if (cuadrado.transacciones.size() >= color.startValue && cuadrado.transacciones.size() <= color.endValue) {
                    cuadrado.color = color.value;
                }
            }
        }
    }
}