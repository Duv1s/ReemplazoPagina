package main.java.co.edu.uptc.reemplazoPaginaFIFO;

/**
 *
 * @author Duvis Gómez && Nelson Niño && Pedro Otálora
 */
public class Fifo {

    private int numberPages;
    private int numberFrames;
    int[] pages;
    int[][] matrix;
    int[] failures;
    int aux;

    public Fifo() {
        numberPages = 0;
        numberFrames = 0;
        pages = new int[0];
        matrix = new int[0][0];
        failures = new int[0];
        aux = 0;
    }

    public void setPages(int[] paginas) {
        this.pages = paginas;
    }

    public void setNumberPages(int cantidadPaginas) {
        this.numberPages = cantidadPaginas;
    }

    public void setNumberFrames(int cantidadFrames) {
        this.numberFrames = cantidadFrames;
    }

    public void iniciarxfallos() {
        for (int i = 0; i < numberPages; i++) {
            failures[i] = 0;
        }
    }

    public void restartCursor() {
        aux++;
        if (aux == numberFrames) {//Al llegar al final de los frames reinicia al primer frame
            aux = 0;
        }
    }

    public boolean searchForColumn(int currentPage) {
        boolean isExist = false;
        for (int j = 0; j < numberFrames; j++) {
            //recorre todos los frames de una pagina determinada
            if (pages[currentPage] == matrix[j][currentPage]) {
                //si el valor de la pagina actual existe en algun frame la bandera se le asigna verdadero
                isExist = true;
            }
        }
        return isExist;
    }

    public void modify(boolean isFound, int currentPage) {
        if (!isFound) {
            for (int aux = currentPage; aux < numberPages; aux++) {
                matrix[this.aux][aux] = pages[currentPage];
                failures[currentPage] = 1;
            }
            restartCursor();
        }
    }

    public void executeFifo() {
        matrix = new int[numberFrames][numberPages];
        failures = new int[numberPages];
        iniciarxfallos();
        for (int i = 0; i < numberPages; i++) {
            modify(searchForColumn(i), i);
        }
        showMatrix();
    }

    public void showMatrix() {
        int numberFailures = 0;
        for (int i = 0; i < numberFrames; i++) {
            for (int j = 0; j < numberPages; j++) {
                System.out.print("" + matrix[i][j]);
            }
            System.out.println();
        }

        for (int i = 0; i < numberPages; i++) {
            if (failures[i] == 1) {
                numberFailures++;
            }
            System.out.print("" + failures[i]);
        }
        System.out.println("\n\nFallos encontrados: " + numberFailures);
    }

}
