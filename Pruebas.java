public class Pruebas {
    public static void main(String[] args) {
        Cola<Integer> cola = new Cola<Integer>();

        cola.añadir(1);
        cola.añadir(2);
        cola.añadir(3);
        cola.añadir(4);

        System.out.println("Se imprime la cola:");
        System.out.println(cola);
        System.out.println("Se imprime el tamaño de la cola:");
        System.out.println(cola.getTamaño());
        System.out.println("Se imprime el primer elemento de la cola:");
        System.out.println(cola.consultar());
        System.out.println("Se elimina el primer elemento de la cola:");
        cola.eliminar();
        System.out.println("Se imprime la cola:");
        System.out.println(cola);
        System.out.println("Se imprime el tamaño de la cola:");
        System.out.println(cola.getTamaño());
        System.out.println("Se imprime el primer elemento de la cola:");
        System.out.println(cola.consultar());
    }
}
