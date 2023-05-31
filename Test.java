import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        // tienda1.iniciar();
        
        JFrame frame = new JFrame("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(1, 0));
        

        // frame.add(new JLabel("perro"));
        // frame.add(new JLabel("gato"));

        TiendaConFilaNormal tienda1 = new TiendaConFilaNormal(12);
        JPanelTiendaConFilaNormal panel = new JPanelTiendaConFilaNormal(tienda1);
        panel.setSize(500, 500);
        
        frame.add(panel);
        Thread thread = new Thread() {
            @Override
            public void run() {
                panel.iniciar();
                frame.revalidate();
                frame.repaint();
            }
        };
        thread.start();

        TiendaConUnifila tienda2 = new TiendaConUnifila(12);
        JPanelTiendaConUnifila panel2 = new JPanelTiendaConUnifila(tienda2);
        panel2.setSize(500, 500);
        frame.add(panel2);
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                panel2.iniciar();
                frame.revalidate();
                frame.repaint();

            }
        };
        thread2.start();
    }
}
