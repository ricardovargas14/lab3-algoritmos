/**
 * Esta clase representa una caja de un supermercado.
 * Tiene una cola de clientes, un número de caja y un
 * booleano que indica si la caja está abierta o no.
 */
public class Caja {
    private Cola<Object> cola;
    private int numeroDeCaja;
    private boolean estaAbierta;

    public Caja(int numeroDeCaja) {
        cola = new Cola<Object>();
        this.numeroDeCaja = numeroDeCaja;
        estaAbierta = false;
    }

    public void abrir() {
        estaAbierta = true;
    }

    public void cerrar() {
        estaAbierta = false;
    }

    public boolean estaAbierta() {
        return estaAbierta;
    }

    public void añadirCliente(Object cliente) {
        cola.añadir(cliente);
    }

    public Object eliminarCliente() {
        return cola.eliminar();
    }

    public Object consultarCliente() {
        return cola.consultar();
    }

    public boolean estaVacía() {
        return cola.estaVacía();
    }

    public String toString() {
        return "Caja " + numeroDeCaja + ": " + cola;
    }

    public int getNumeroDeClientes() {
        return cola.getTamaño();
    }


}
