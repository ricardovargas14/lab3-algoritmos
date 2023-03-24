/**
 * En esta clase se ejecuta el programa, puedes
 * ver el funcionamiento de la tienda con ambos
 * tipos de filas, de forma simultánea.
 */
public class App {
    public static void main(String[] args) {
        // Se modifican los métodos de limpiarConsola() de las
        // clases TiendaConFilaNormal y TiendaConUnifila para
        // que no hagan nada.
        class TiendaConFilaNormal2 extends TiendaConFilaNormal {
            public TiendaConFilaNormal2(int numeroDeCajas) {
                super(numeroDeCajas);
            }
            @Override
            protected void limpiarConsola() {
                // no hace nada
            }

            // se modifica el método toString() para que diga
            // el tipo de fila que se está usando:
            @Override
            public String toString() {
                return "Tienda con fila normal\n" + super.toString();
            }
        }
        class TiendaConUnifila2 extends TiendaConUnifila {
            public TiendaConUnifila2(int numeroDeCajas) {
                super(numeroDeCajas);
            }
            @Override
            protected void limpiarConsola() {
                // no hace nada
            }

            // se modifica el método toString() para que diga
            // el tipo de fila que se está usando:
            @Override
            public String toString() {
                return "Tienda con unifila\n" + super.toString();
            }
        }

        // usando hilos, se ejecutan las dos simulaciones
        // de forma simultánea:
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                TiendaConFilaNormal tiendaConFilaNormal = new TiendaConFilaNormal2(2);
                tiendaConFilaNormal.iniciar();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                TiendaConUnifila2 tiendaConUnifila = new TiendaConUnifila2(2);
                tiendaConUnifila.iniciar();
            }
        });
        t1.start();
        t2.start();

    }
}
