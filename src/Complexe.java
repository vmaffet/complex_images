import java.awt.Point;
import java.awt.geom.Point2D;

public class Complexe {

	double Re, Im;
	
	public Complexe (Point2D.Double a) {
		Re= a.x;
		Im= a.y;
	}
	
	public Complexe (double r, double i) {
		Re= r;
		Im= i;
	}
	
	public Complexe addition (Complexe z) {
		return new Complexe(Re+z.Re, Im+z.Im);
	}
	
	public Complexe soustraction (Complexe z) {
		return new Complexe(Re-z.Re, Im-z.Im);
	}
	
	public Complexe multiplication (Complexe z) {
		return new Complexe (Re*z.Re-Im*z.Im, Re*z.Im+Im*z.Re);
	}
	
	public Complexe division (Complexe z) {
		return new Complexe ((Re*z.Re+Im*z.Im)/(z.Re*z.Re+z.Im*z.Im), (Re*z.Im+Im*z.Re)/(z.Re*z.Re+z.Im*z.Im));
	}
	
	public Complexe racine () {
		return new Complexe(Math.sqrt((Re+Math.sqrt(Re*Re+Im*Im))/2), Math.sqrt((Math.sqrt(Re*Re+Im*Im)-Re)/2));
	}
	
	public Complexe puissance (Complexe z, int n) {
		if (n == 0) {
			return new Complexe (1, 0);
		}
		Complexe c= z;
		for (int i= 1; i<n; i++) {
			z= z.multiplication(c);
		}
		return z;
	}
	
	public Complexe julia (Complexe z) {
		return z.addition(puissance(this, 2));
	}
	
	public Complexe exp () {
		return new Complexe(Math.exp(Re)*Math.cos(Im), Math.exp(Re)*Math.sin(Im));
	}
	
	public Complexe cos () {
		return multiplication(new Complexe(0, 1)).exp().addition(multiplication(new Complexe(0, -1)).exp()).division(new Complexe(2, 0));
	}
	
	public Complexe sin () {
		return multiplication(new Complexe(0, 1)).exp().soustraction(multiplication(new Complexe(0, -1)).exp()).division(new Complexe(0, 2));
	}
	
	public Complexe tan () {
		return sin().division(cos());
	}
	
	public Point toPoint () {
		return new Point ((int)Re, (int)Im);
	}
	
	public Complexe f0 () {
		return this;
	}
	
	public Complexe f1 () {
		return puissance(division(new Complexe(30, 0)), 2);
	}
	
	public Complexe f2 () {
		return puissance(this, 2);
	}
	
	public Complexe f3 () {
		Complexe c= new Complexe(1, 0);
		return c.division(division(new Complexe(60000, 0)));
	}
	
	public Complexe f4 () {
		return multiplication(new Complexe(0.5, 0.2));
	}
	
	public Complexe f5 () {
		return multiplication(new Complexe(500, 0)).racine();
	}
	
	public Complexe f6 () {
		return puissance(division(new Complexe(50, 0)), 3);
	}
	
	public Complexe f7 () {
		return puissance(division(new Complexe(70, 0)), 4);
	}
	
	public Complexe f8 () {
		return division(new Complexe(80, 0)).exp();
	}
	
	public Complexe f9 () {
		return division(new Complexe(50, 0)).cos();
	}
	
	public Complexe f10 () {
		return division(new Complexe(280, 0)).tan();
	}
	
	public Complexe f11 () {
		return f3().division(new Complexe(50, 0)).exp();
	}
	
	public Complexe f12 (int n, Complexe c) {
		Complexe z= division(new Complexe(300, 0));
		for (int i= 0; i<n; i++) {
			z= z.julia(c);
		}
		return z;
	}
	
}
