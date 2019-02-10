package main.java.co.edu.uptc;


import javax.swing.JOptionPane;
import main.java.co.edu.uptc.reemplazoPaginaFIFO.Fifo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numberPages,numberFrames;
		int []pages;
		
		numberPages=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese cantidad de Páginas"));
		numberFrames=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese cantidad de marcos de página"));
		
		pages= new int[numberPages];
		
		for(int c=0;c<numberPages;c++){
			pages[c]=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese valor de paginas ["+(c+1)+"]"));
		}

		System.out.println("FIFO\n");
                Fifo fifo=new Fifo();
		fifo.setNumberFrames(numberFrames);
		fifo.setNumberPages(numberPages);
		fifo.setPages(pages);
		fifo.executeFifo();
		
//		2

	}

}
