/**
 * Esta clase representa una tienda de supermercado.
 * 
 * Tiene un número de cajas y un array de cajas.
 * También tiene un método para abrir las cajas,
 * otro para cerrarlas y otro para añadir clientes
 * a una caja.
 */
public class Tienda {
    private int numeroDeCajas;
    private Caja[] cajas;

    public Tienda(int numeroDeCajas) {
        this.numeroDeCajas = numeroDeCajas;
        cajas = new Caja[numeroDeCajas];
        // Creamos las cajas:
        for (int i = 0; i < numeroDeCajas; i++) {
            cajas[i] = new Caja(i + 1);
        }
    }
    
    public void abrirCaja(int numeroDeCaja) {
        cajas[numeroDeCaja].abrir();
    }

    public void cerrarCaja(int numeroDeCaja) {
        cajas[numeroDeCaja].cerrar();
    }

    public void abrirTodasLasCajas() {
        for (int i = 0; i < numeroDeCajas; i++) {
            cajas[i].abrir();
        }
    }

    public void cerrarTodasLasCajas() {
        for (int i = 0; i < numeroDeCajas; i++) {
            cajas[i].cerrar();
        }
    }

    public void añadirClienteACaja(int numeroDeCaja, Object cliente) {
        cajas[numeroDeCaja].añadirCliente(cliente);
    }

    public Caja getCaja(int numeroDeCaja) {
        return cajas[numeroDeCaja];
    }

    public int getNumeroDeCajas() {
        return numeroDeCajas;
    }

    public String toString() {
        String resultado = "";

        for (int i = 0; i < numeroDeCajas; i++) {
            resultado += cajas[i];
            if (i != numeroDeCajas - 1) {
                resultado += "\n";
            }
        }
        return resultado;
    }
}
