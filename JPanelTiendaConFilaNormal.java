import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class JPanelTiendaConFilaNormal extends JPanel implements Runnable {
    protected boolean running = false;

    protected Image buffer;
    protected Graphics2D bufferGraphics;

    protected TiendaConFilaNormal tienda;


    public JPanelTiendaConFilaNormal(TiendaConFilaNormal tienda) {
        super();
        this.tienda = tienda;
    }

    public void iniciar() {
        if (!running) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    tienda.iniciar();
                }
            };
            thread.start();

            running = true;
            buffer = createImage(getWidth(), getHeight());
            bufferGraphics = (Graphics2D) buffer.getGraphics();
            Thread thread2 = new Thread(this);
            thread2.start();
        }
    }

    public void detener() {
        running = false;
        tienda.detener();
    }

    protected void actualizarBuffer() {
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());
        bufferGraphics.setColor(getForeground());

        int anchoDeCaja = getWidth() / 6;
        int altoDeCaja = (getHeight() / tienda.getTienda().getNumeroDeCajas()) - 20;
        int anchoDeCliente = anchoDeCaja / 10;
        int altoDeCliente = altoDeCaja + 3;

        // se le cambia el tipo de letra para drawString
        bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(15f));
        bufferGraphics.drawString("Total de clientes: " + tienda.getNumeroDeClientes(), 10, getWidth() * 7/8);
        bufferGraphics.drawString("Total de clientes atendidos: " + tienda.getNumeroDeClientesDespachados(), 10, getWidth() * 7/8 + 20);
        bufferGraphics.drawString("Total de segundos en ejecución: " + tienda.getSegundosEnEjecución(), 10, getWidth() * 7/8 + 40);

        // por cada caja se dibuja un rectángulo rojo:
        for (int i = 0; i < tienda.getTienda().getNumeroDeCajas(); i++) {
            bufferGraphics.setColor(Color.RED);
            bufferGraphics.fillRect(getWidth() * 4 / 5, (altoDeCaja + 10) * i + 10, anchoDeCaja, altoDeCaja);
            // se le cambia el tipo de letra para drawString
            bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(15f));
            bufferGraphics.setColor(Color.WHITE);
            bufferGraphics.drawString(i + 1 + "", getWidth() * 4 / 5, (altoDeCaja + 10) * i + 15 + altoDeCaja / 2);

            // // por cada cliente se dibuja un rectángulo azul:
            for (int j = 0; j < tienda.getTienda().getCaja(i).getNumeroDeClientes(); j++) {
                bufferGraphics.setColor(Color.BLUE);
                if (j == 0) {
                    bufferGraphics.fillRect(getWidth() * 4 / 5 + 30, (altoDeCaja + 10) * i + 8, anchoDeCliente, altoDeCliente);
                } else {   
                    bufferGraphics.fillRect(getWidth() * 4 / 5 - j * 30, (altoDeCaja + 10) * i + 8, anchoDeCliente, altoDeCliente);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (buffer != null) {
            g.drawImage(buffer, 0, 0, null);
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                // this.add(new JLabel(tienda.toString()));

                Thread.sleep(100);
                actualizarBuffer();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
