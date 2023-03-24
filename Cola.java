import java.util.ArrayList;

/**
 * Esta clase modela la estructura de datos 'Cola' en Java.
 */
public class Cola<T> {
    private ArrayList<T> elementos;

    public Cola() {
        elementos = new ArrayList<T>();
    }

    public int getTamaño() {
        return elementos.size();
    }

    public void añadir(T elemento) {
        elementos.add(elemento);
    }

    public T eliminar() {
        return elementos.remove(0);
    }

    public T consultar() {
        return elementos.get(0);
    }

    public boolean estaVacía() {
        return elementos.isEmpty();
    }

    public void vaciar() {
        elementos.clear();
    }

    public String toString() {
        return elementos.toString();
    }
}
