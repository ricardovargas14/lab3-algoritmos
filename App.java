import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class App extends JFrame {
    private TiendaConFilaNormal tiendaConFilaNormal;
    private TiendaConUnifila tiendaConUnifila;

    // se crean los paneles para las tiendas:
    private JPanelTiendaConFilaNormal jPanelTiendaConFilaNormal;
    private JPanelTiendaConUnifila jPanelTiendaConUnifila;

    private JButton jButton;

    private Thread threadTiendaConFilaNormal;
    private Thread threadTiendaConUnifila;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super("App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        inicializar();
        setVisible(true);
    }

    private void listener(ActionEvent e) {
        if (jButton.getText().equals("Iniciar")) {
            threadTiendaConFilaNormal.start();
            threadTiendaConUnifila.start();

            jButton.setText("Detener");
        } else if (jButton.getText().equals("Detener")) {
            threadTiendaConFilaNormal.interrupt();
            threadTiendaConUnifila.interrupt();

            jButton.setText("Reiniciar");
        } else if (jButton.getText().equals("Reiniciar")) {
            reiniciar();

            jButton.setText("Iniciar");
        }
    }

    private void crearTiendas() {
        tiendaConFilaNormal = new TiendaConFilaNormal(12);
        tiendaConUnifila = new TiendaConUnifila(12);
    }

    private void crearPaneles() {
        jPanelTiendaConFilaNormal = new JPanelTiendaConFilaNormal(tiendaConFilaNormal);
        jPanelTiendaConUnifila = new JPanelTiendaConUnifila(tiendaConUnifila);

        jPanelTiendaConFilaNormal.setBorder(BorderFactory.createTitledBorder("Tienda con fila normal"));
        jPanelTiendaConUnifila.setBorder(BorderFactory.createTitledBorder("Tienda con una fila"));

        // se agregan los paneles a la ventana:
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 0));
        jPanel.add(jPanelTiendaConFilaNormal);
        jPanel.add(jPanelTiendaConUnifila);
        this.add(jPanel, BorderLayout.CENTER);
    }

    private void crearThreads() {
        threadTiendaConFilaNormal = new Thread() {
            @Override
            public void run() {
                jPanelTiendaConFilaNormal.iniciar();
                revalidate();
                repaint();
            }
            @Override
            public void interrupt() {
                jPanelTiendaConFilaNormal.detener();
                revalidate();
                repaint();
            }
        };

        threadTiendaConUnifila = new Thread() {
            @Override
            public void run() {
                jPanelTiendaConUnifila.iniciar();
                revalidate();
                repaint();
            }
            @Override
            public void interrupt() {
                jPanelTiendaConUnifila.detener();
                revalidate();
                repaint();
            }
        };
    }

    private void inicializar() {
        // se crea el botón para iniciar la simulación:
        jButton = new JButton("Iniciar");
        jButton.addActionListener(this::listener);

        this.add(jButton, BorderLayout.SOUTH);

        // se crean las tiendas:
        crearTiendas();

        // se crean los paneles para las tiendas:
        crearPaneles();

        // se crean los hilos para las tiendas:
        crearThreads();
    }



    private void reiniciar() {
        // se crean las tiendas:
        crearTiendas();

        // se crean los paneles para las tiendas:
        crearPaneles();

        // se crean los hilos para las tiendas:
        crearThreads();
    }
}
