import java.util.Random;

/**
 * Esta clase representa una tienda de supermercado,
 * pero con una fila única para todos los clientes.
 * 
 * Cada puede tener hasta 2 clientes. Hasta que se
 * atienda a un cliente, no se puede formar uno nuevo.
 * 
 * La fila única se encuentra antes de las cajas, ahi
 * se forman todos los clientes a esperar a que se
 * atiendan los que ya están en las cajas.
 */
public class TiendaConUnifila extends TiendaConFilaNormal {
    public static void main(String[] args) {
        TiendaConUnifila app = new TiendaConUnifila(1);
        app.iniciar();
    }

    private Cola<Integer> fila;
    private final int MÁXIMO_DE_CLIENTES_EN_COLA = 2;

    public TiendaConUnifila(int numeroDeCajas) {
        super(numeroDeCajas);
        fila = new Cola<Integer>();
    }

    // añade un cliente a la fila principal:
    private void añadirClienteALaFila() {
        fila.añadir(++numeroDeClientes);
    }

    // añade un cliente al número de caja indicado,
    // primero lo saca de la fila principal:
    @Override
    protected void añadirClienteACaja(int numeroDeCaja) {
        if (fila.estaVacía()) {
            System.out.println("No hay clientes en la fila.");
        } else {
            super.tienda.añadirClienteACaja(numeroDeCaja, fila.eliminar());
        }
    }

    @Override
    protected void añadirClienteACajaConMenosClientes() {
        // añade un cliente que se encuentra en la fila principal
        // a la caja con menos clientes. El máximo de clientes en 
        // una caja es 2.

        // si la fila principal está vacía, no se hace nada:
        if (fila.estaVacía()) {
            System.out.println("No hay clientes en la fila.");
            return;
        }

        // se busca la caja con menos clientes:
        int cajaConMenosClientes = 0;
        int clientesEnCajaConMenosClientes = tienda.getCaja(0).getNumeroDeClientes();

        for (int i = 1; i < tienda.getNumeroDeCajas(); i++) {
            int clientesEnCaja = tienda.getCaja(i).getNumeroDeClientes();
            if (clientesEnCaja < clientesEnCajaConMenosClientes) {
                cajaConMenosClientes = i;
                clientesEnCajaConMenosClientes = clientesEnCaja;
            }
        }

        // si la caja tiene el máximo de clientes, no se hace nada:
        if (clientesEnCajaConMenosClientes == MÁXIMO_DE_CLIENTES_EN_COLA) {
            System.out.println("Todas las cajas están llenas.");
            return;
        }

        // se añade el cliente a la caja:
        añadirClienteACaja(cajaConMenosClientes);
    }

    @Override
    protected void iniciar() {
        Random random = new Random();
        // llegan clientes de forma aleatoria a la tienda y
        // se añaden clientes de forma aleatoria a las cajas:
        while (true) {
            // Se limpia la consola y se imprime el estado actual
            // de la tienda:
            limpiarConsola();
            System.out.println(this);

            // cada segundo, se genera un numero aleatorio y si es
            // menor que 0.5, se añade un cliente a la fila principal:
            pausar(1);
            if (random.nextDouble() < 0.5) {
                añadirClienteALaFila();
            }
            
            // cada segundo se genera un numero aleatorio,
            // si es menor que 0.5, se añade un cliente a la
            // caja con menos clientes:
            
            pausar(1);
            
            if (random.nextDouble() < 0.5) {
                añadirClienteACajaConMenosClientes();
            }
            
            // Se limpia la consola y se imprime el estado actual
            // de la tienda:
            limpiarConsola();
            System.out.println(this);
            
            pausar(1);
            
            // Se genera un numero aleatorio,
            // si es menor que 0.5, sale un cliente de una caja
            // aleatoria:
            if (random.nextDouble() < 0.5) {
                eliminarClienteDeCajaAleatoria();
            }
        }
    }

    @Override
    public String toString() {
        String s = "Fila principal: " + fila + "\n";
        return s + super.toString();
    }
    
    
}
