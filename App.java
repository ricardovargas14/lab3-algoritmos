import java.util.Random;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.iniciar();
    }

    private Tienda tienda;
    private int numeroDeClientes = 0;
    private int numeroDeClientesDespachados = 0;
    private int segundosEnEjecución = 0;

    private App() {
        tienda = new Tienda(10);
    }

    // Inicia la simulación
    private void iniciar() {
        Random random = new Random();
        // llegan clientes de forma aleatoria a la tienda y
        // se añaden clientes de forma aleatoria a las cajas:
        while (true) {
            // Se limpia la consola y se imprime el estado actual
            // de la tienda:
            limpiarConsola();
            System.out.println(this);
            
            // cada segundo se genera un numero aleatorio,
            // si es menor que 0.5, se añade un cliente a la
            // caja con menos clientes:
            
            pausar(1);
            segundosEnEjecución++;
            
            if (random.nextDouble() < 0.5) {
                añadirClienteACajaConMenosClientes();
            }
            
            // Se limpia la consola y se imprime el estado actual
            // de la tienda:
            limpiarConsola();
            System.out.println(this);
            
            pausar(1);
            segundosEnEjecución++;
            
            // Se genera un numero aleatorio,
            // si es menor que 0.5, sale un cliente de una caja
            // aleatoria:
            if (random.nextDouble() < 0.5) {
                eliminarClienteDeCajaAleatoria();
            }
        }
    }

    // Añade un cliente a la caja que recibe como parámetro:
    private void añadirClienteACaja(int numeroDeCaja) {
        // El cliente es de tipo Integer, su valor son números
        // sucesivos empezando por 1.
        tienda.añadirClienteACaja(numeroDeCaja, ++numeroDeClientes);

        System.out.println("A la caja " + numeroDeCaja + " le ha llegado un cliente");
    }

    // Añade un cliente a la caja con menos clientes:
    private void añadirClienteACajaConMenosClientes() {
        int numeroDeCajaConMenosClientes = 0;
        int numeroDeClientesDeCajaConMenosClientes = (tienda.getCaja(0).getNumeroDeClientes());

        for (int i = 1; i < tienda.getNumeroDeCajas(); i++) {
            int numeroDeClientesDeCajaActual = tienda.getCaja(i).getNumeroDeClientes();
            if (numeroDeClientesDeCajaActual < numeroDeClientesDeCajaConMenosClientes) {
                numeroDeCajaConMenosClientes = i;
                numeroDeClientesDeCajaConMenosClientes = numeroDeClientesDeCajaActual;
            }
        }

        añadirClienteACaja(numeroDeCajaConMenosClientes);

    }

    // Sale un cliente de la caja que recibe como parámetro:
    private void eliminarClienteDeCaja(int numeroDeCaja) {
        tienda.getCaja(numeroDeCaja).eliminarCliente();
        System.out.println("Ha salido un cliente de la caja " + numeroDeCaja);
        numeroDeClientesDespachados++;
    }

    // Sale un cliente de una caja aleatoria:
    private void eliminarClienteDeCajaAleatoria() {
        Random random = new Random();
        int numeroDeCaja = random.nextInt(tienda.getNumeroDeCajas());
        // Si la caja está vacía, se sale del método:
        if (tienda.getCaja(numeroDeCaja).estaVacía()) {
            return;
        }
        eliminarClienteDeCaja(numeroDeCaja);
    }

    // Pausa la ejecución del programa durante el tiempo que recibe como parámetro:
    private void pausar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // limpia la consola:
    private void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // imprime el estado actual de la tienda:
    @Override
    public String toString() {
        String resultado = "";
        resultado += tienda.toString() + "\n";
        resultado += "Total de clientes: " + numeroDeClientes + "\n";
        resultado += "Clientes despachados: " + numeroDeClientesDespachados + "\n";
        resultado += "Tiempo en ejecución: " + segundosEnEjecución;
        return resultado;
    }
}
