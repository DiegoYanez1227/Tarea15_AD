package rectangulo;

public class Rectangulo {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private static int maximo=100;
	private static int minimo=0;
	
	
	
	public Rectangulo() {
	}
	
	// TODO añadir un if comprobando que x1 x2 y1 y2 sean todos mayores a minimo y menores que maximo. En El constructor y en todos los metodos SET
	public Rectangulo(int x1, int y1, int x2, int y2) {
	
		if(x2>x1 && y2>y1) {
			//TODO If() --> este if es el que tenemos que rellenar
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
			
		}else {
			
			System.err.println("Error al instancia el rectangulo");
		}
		
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(x2>x1) {
			this.x1 = x1;
		}else {
			System.err.println("Error al cambiar el dato de la coordenada x1");
		}
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(x2>x1) {
			this.x2 = x2;
		}else {
			System.err.println("Error al cambiar el dato de la coordenada x2");
		}
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(y2>y1) {
			this.y1 = y1;
		}else {
			System.err.println("Error al cambiar el dato de la coordenada y1");
		}
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(y2>y1) {
			this.y2 = y2;
		}else {
			System.err.println("Error al cambiar el dato de la coordenada y2");
		}
	}
	
	public void setX1Y1(int x1, int y1) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(x2>x1 && y2>y1) {		
			this.x1 = x1;
			this.y1 = y1;
		}else {
			
			System.err.println("Error al establecer x1 e y1");
		}
	}
	
	public void setX2Y2(int x2, int y2) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(x2>x1 && y2>y1) {		
			this.x2 = x2;
			this.y2 = y2;
		}else {
			System.err.println("Error al establecer x2 e y2");
		}
	}
	
	public void setAll(int x1, int y1, int x2, int y2) {
		//TODO If() --> este if es el que tenemos que rellenar
		if(x2>x1 && y2>y1) {		
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}else {
			System.err.println("Error al establecer todos los datos del rectangulo");
		}
	}

	@Override
	public String toString() {
		return "Rectangulo [x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2 + "]";
	}
	
	
	public void calcularArea() {
		System.out.println("El area es: "+ calcularAltura(y1, y2)*calcularBase(x1, x2));
	}
	
	public void calcularPerimetro() {
		int alturas=calcularAltura(y1, y2)*2;
		int lados= calcularBase(x1, x2)*2;
		int total= lados+ alturas;
		System.out.println("El perimetro es: "+total);
	}
	
	public int calcularBase(int x1, int x2) {
		int base= x2-x1;
		return base;
	}
	
	public int calcularAltura(int y1, int y2) {
		int altura =y2-y1;
		return altura;
	}
	
	
}
