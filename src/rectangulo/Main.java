package rectangulo;

public class Main {

	public static void main(String[] args) {
		Rectangulo r1= new Rectangulo(7,9,2,3);
		Rectangulo r2= new Rectangulo(0,0,5,5);
		
		r1.calcularArea();
		r1.calcularPerimetro();
		
		System.out.println("\n");
		
		r2.calcularArea();
		r2.calcularPerimetro();
	}

}
