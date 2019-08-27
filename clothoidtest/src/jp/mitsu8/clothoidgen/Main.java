package jp.mitsu8.clothoidgen;

import java.util.function.Function;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final double scale = 512;
		final double width = 768, height = 768;
		final double a = -8, b = 8;
		final int n = 256;
		
		SVGPath arcSpline = new SVGPath();
		arcSpline.setContent(generateArcSpline(a, b, n));
		arcSpline.getTransforms().add(new Scale(scale, scale));
		arcSpline.setFill(null);
		arcSpline.setStroke(Color.BLACK);
		arcSpline.setStrokeWidth(1/scale);
		
		SVGPath lineString = new SVGPath();
		lineString.setContent(generateLineString(a, b, n));
		lineString.getTransforms().add(new Scale(scale, scale));
		lineString.setFill(null);
		lineString.setStroke(Color.BLUE);
		lineString.setStrokeWidth(1/scale);
		
		
		double limit = Math.sqrt(Math.PI) / 2;
		Circle limPoint = new Circle(limit*scale, limit*scale, 2, Color.RED);
		
		Pane pane = new Pane(lineString, arcSpline, limPoint);
		pane.setPrefSize(width, height);
		
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}
	
	public String generateArcSpline(double a, double b, int n) {
		StringBuilder sb = new StringBuilder("M 0 0");
		
		double dt = (b-a) / n;
/*		double q = dt*dt/-12.0;
		double t0 = a;
		double theta0 = t0*t0/2 + q;
		for (int k = 0; k < n; k++) {
			double t1 = a + (k+1)*dt;
			double theta1 = t1*t1/2.0 + q;
			double kappa = (t0+t1)/2.0;
			double dx = ( sin(theta1) - sin(theta0)) / kappa;
			double dy = (-cos(theta1) + cos(theta0)) / kappa;
			boolean largeArcFlag = theta1-theta0 >= Math.PI;
			sb.append(String.format(" a %1$s %1$s 0 %2$d 1 %3$s %4$s",
					1/kappa, largeArcFlag ? 1 : 0, dx, dy));
			
			t0 = t1;
			theta0 = theta1;
		}
*/		
//		Complex offset = clothoid(new Complex(a));
		Complex offset = Complex.ZERO;
		for (int k = 1; k <= n; k++) {
			double t = a + k*dt;
			Complex p = clothoid(new Complex(t)).sub(offset);
			double kappa = a + (k-0.5)*dt;
			boolean largeArcFlag = kappa*dt >= Math.PI;
			sb.append(String.format(" A %1$s %1$s 0 %2$d 1 %3$s %4$s",
					1/kappa, largeArcFlag ? 1 : 0, p.re, p.im));
		}
		return sb.toString();
	}
	
	public String generateLineString(double a, double b, int n) {
		StringBuilder sb = new StringBuilder("M 0 0");
		
		double dt = (b-a)/n;
//		Complex offset = clothoid(new Complex(a));
		Complex offset = Complex.ZERO;
		for (int k = 1; k <= n; k++) {
			Complex p = clothoid(new Complex(a + dt*k));
			Complex dp = p.sub(offset);
			sb.append(String.format(" L %s %s", dp.re, dp.im));
		}
		return sb.toString();
	}
	
	public static Complex clothoid(Complex x) {
		final int N = 256;
		Function<Integer, Complex> ak = k -> new Complex(1.0 / (2*k+1));
		Function<Integer, Complex> pk = k -> k == 0 ? x : x.mul(x).mul(Complex.I).div(2*k);
		Complex prev = new Complex(0.0);
		for (int k = N; k >= 0; k--) {
			prev = pk.apply(k).mul(ak.apply(k).add(prev));
		}
		return prev;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
