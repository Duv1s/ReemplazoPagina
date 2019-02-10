package paquete;

public class LRU {
	
	private int cantidadPaginas;
	private int cantidadFrames;
	int []paginas;
	int [][]matriz;
	int []fallos;
	int []distancia;

	public LRU(){
		System.out.println("LRU");
	}

	public void setPaginas(int []paginas) {
		this.paginas = paginas;
	}

	public void setCantidadPaginas(int cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public void setCantidadFrames(int cantidadFrames) {
		this.cantidadFrames = cantidadFrames;
	}
	
	public void iniciarxfallos(){
		for(int i=0;i<cantidadPaginas;i++){
			fallos[i]=0;
		}
	}
	
	private void iniciarMatriz(){
		for(int i=0;i<cantidadFrames;i++){
			for(int j=0;j<cantidadPaginas;j++){
				matriz[i][j]=-1;
			}
		}
	}
	
	private boolean buscar(int paginaActual){
		boolean encontrado=false;
		for(int i=0;i<cantidadFrames;i++){
			if(paginas[paginaActual]==matriz[i][paginaActual]){
				encontrado=true;
			}
		}
		return encontrado;
	}
	
	private void llenarFila(int paginaActual, int frame){
		for(int j=paginaActual;j<cantidadPaginas;j++){
			matriz[frame][j]=paginas[paginaActual];
		}
	}
	//este método me devolverá el frame que fue el mas antiguo en ser liberado
	private int MayorDistancia(int paginaActual){
		int mayorDist=0;
		for(int i=0;i<cantidadFrames;i++){
			for(int j=paginaActual;j>=0;j--){
				if(matriz[i][paginaActual]==paginas[j]){
					distancia[i]=paginaActual-j;
					break;
				}
			}
		}
		
		for(int i=0;i<cantidadFrames;i++){
			if(distancia[i]>distancia[mayorDist]){
				mayorDist=i;
			}
		}
		return mayorDist;
	}
	
	private void modificar(int paginaActual){
		boolean encontradoFrameLibre=false;
		int i;
		for(i=0;i<cantidadFrames;i++){
			if(matriz[i][paginaActual]==-1){
				encontradoFrameLibre=true;
				break;
			}
		}
		
		if(!encontradoFrameLibre){
			llenarFila(paginaActual, MayorDistancia(paginaActual));
		}else{
			llenarFila(paginaActual, (i));
		}
		
		fallos[paginaActual]=1;
		
	}
	
	public void lru(){
		matriz=new int [cantidadFrames][cantidadPaginas];
		fallos= new int [cantidadPaginas];
		distancia= new int[cantidadFrames];
		iniciarxfallos();
		iniciarMatriz();
		//Recorremos todas las paginas
		for(int j=0;j<cantidadPaginas;j++){
			if(!buscar(j)){
				modificar(j);
			}
		}
		mostrarMatriz();
	}
	
	public void mostrarMatriz(){
		int cantidadFallos=0;
		for(int i=0;i<cantidadFrames;i++){
			for(int j=0;j<cantidadPaginas;j++){
				if(matriz[i][j]==-1){
					System.out.print(" ");//para que no se muestre el -1 en la matriz
				}else
				System.out.print(""+matriz[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0;i<cantidadPaginas;i++){
			if(fallos[i]==1){
				cantidadFallos++;
			}
			System.out.print(""+fallos[i]);
		}
		System.out.println("\n\nFallos encontrados: "+cantidadFallos);
	}

}