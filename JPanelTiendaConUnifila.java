import java.awt.Color;

public class JPanelTiendaConUnifila extends JPanelTiendaConFilaNormal {
    public JPanelTiendaConUnifila(TiendaConUnifila tienda) {
        super(tienda);
    }

    @Override
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
        bufferGraphics.drawString("Total de clientes: " + tienda.getNumeroDeClientes(), 10, getWidth() * 7 / 8);
        bufferGraphics.drawString("Total de clientes atendidos: " + tienda.getNumeroDeClientesDespachados(), 10,
                getWidth() * 7 / 8 + 20);
        bufferGraphics.drawString("Total de segundos en ejecución: " + tienda.getSegundosEnEjecución(), 10,
                getWidth() * 7 / 8 + 40);

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
                    bufferGraphics.fillRect(getWidth() * 4 / 5 + 30, (altoDeCaja + 10) * i + 8, anchoDeCliente,
                            altoDeCliente);
                } else {
                    bufferGraphics.fillRect(getWidth() * 4 / 5 - j * 30, (altoDeCaja + 10) * i + 8, anchoDeCliente,
                            altoDeCliente);
                }
            }
        }

        // por cada cliente en la fila se dibuja un rectángulo azul:
        for (int i = 0; i < ((TiendaConUnifila) tienda).getFila().getTamaño(); i++) {
            bufferGraphics.setColor(Color.BLUE);
            bufferGraphics.fillRect(getWidth() * 5 / 8 - i * 30, altoDeCaja + 10, anchoDeCliente, altoDeCliente);
        }
    }

}
