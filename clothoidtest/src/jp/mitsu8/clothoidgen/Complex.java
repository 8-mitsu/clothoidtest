package jp.mitsu8.clothoidgen;

import javafx.geometry.Point2D;

public final class Complex {
	
	public final double re;
	
	public final double im;
	
	public Complex() {
		this(0.0, 0.0);
	}
	
	public Complex(Number re) {
		this(re.doubleValue(), 0.0);
	}
	
	public Complex(Number re, Number im) {
		this(re.doubleValue(), im.doubleValue());
	}
	
	public Complex(double re) {
		this(re, 0.0);
	}
	
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	public final double getReal() {
		return re;
	}
	
	public final double getImaginary() {
		return im;
	}
	

	
	public Complex add(double x) {
		return new Complex(re+x, im);
	}
	
	public Complex add(Number x) {
		return add(x.doubleValue());
	}
	
	public Complex add(double x, double y) {
		return new Complex(re+x, im+y);
	}
	
	public Complex add(Number x, Number y) {
		return add(x.doubleValue(), y.doubleValue());
	}
	
	public Complex add(Complex z) {
		return add(z.re, z.im);
	}

	
	public Complex sub(double x) {
		return new Complex(re-x, im);
	}
	
	public Complex sub(Number x) {
		return sub(x.doubleValue());
	}
	
	public Complex sub(double x, double y) {
		return new Complex(re-x, im-y);
	}
	
	public Complex sub(Number x, Number y) {
		return sub(x.doubleValue(), y.doubleValue());
	}
	
	public Complex sub(Complex z) {
		return sub(z.re, z.im);
	}
	
	
	public Complex mul(double x) {
		return new Complex(re*x, im*x);
	}
	
	public Complex mul(Number x) {
		return mul(x.doubleValue());
	}
	
	public Complex mul(double x, double y) {
		return new Complex(re*x - im*y, re*y+im*x);
	}
	
	public Complex mul(Number x, Number y) {
		return mul(x.doubleValue(), y.doubleValue());
	}
	
	public Complex mul(Complex z) {
		return mul(z.re, z.im);
	}
	
	
	public Complex div(double x) {
		return new Complex(re/x, im/x);
	}
	
	public Complex div(Number x) {
		return div(x.doubleValue());
	}
	
	public Complex div(double x, double y) {
		return new Complex(re*x + im*y, im*x - re*y).div(x*x + y*y);
	}
	
	public Complex div(Number x, Number y) {
		return div(x.doubleValue(), y.doubleValue());
	}
	
	public Complex div(Complex z) {
		return div(z.re, z.im);
	}
	
	
	
	public Complex pow(double x) {
		return pow(x, 0);
	}
	
	public Complex pow(Number x) {
		return pow(x.doubleValue(), 0);
	}
	
	public Complex pow(double x, double y) {
		double a = x * Math.log(abs(x,y)) - y * Math.atan2(y, x);
		double b = y * Math.log(abs(x,y)) + x * Math.atan2(y, x);
		return exp(a, b);
	}
	
	public Complex pow(Number x, Number y) {
		return pow(x.doubleValue(), y.doubleValue());
	}
	
	public Complex pow(Complex z) {
		return pow(z.re, z.im);
	}
	
	
	
	public Complex conjugate() {
		return new Complex(re, -im);
	}
	
	public double abs() {
		return Math.sqrt(re*re + im*im);
	}
	
	public double arg() {
		return Math.atan2(im, re);
	}
	
	public Complex sign() {
		return div(abs());
	}
	
	
	
	public boolean isReal() {
		return im == 0.0;
	}
	
	public boolean isPureImaginary() {
		return re == 0.0;
	}
	
	public boolean isZero() {
		return re == 0.0 && im == 0.0;
	}
	
	public boolean isFinite() {
		return Double.isFinite(re) && Double.isFinite(im);
	}
	
	public boolean isNaN() {
		return Double.isNaN(re) || Double.isNaN(im);
	}
	
	public Point2D toPoint2D() {
		return new Point2D(re, im);
	}
	
	
	public static final Complex ZERO = new Complex(0);
	public static final Complex ONE  = new Complex(1);
	public static final Complex PI   = new Complex(Math.PI);
	public static final Complex E    = new Complex(Math.E);
	public static final Complex I    = new Complex(0, 1);
	
	
	
	public static Complex conjugate(Number x, Number y) {
		return new Complex(x.doubleValue(), -y.doubleValue());
	}
	
	public static double abs(Number x) {
		return Math.abs(x.doubleValue());
	}
	
	public static double abs(Number x, Number y) {
		double a = x.doubleValue();
		double b = y.doubleValue();
		return Math.sqrt(a*a + b+b);
	}
	
	public static double abs(Complex z) {
		return abs(z.re, z.im);
	}
	
	
	
	
	
	public static Complex exp(Number x) {
		return new Complex(Math.exp(x.doubleValue()));
	}
	
	public static Complex exp(Number x, Number y) {
		return expi(y.doubleValue()).mul(Math.exp(x.doubleValue()));
	}
	
	public static Complex exp(Complex z) {
		return exp(z.re, z.im);
	}
	
	public static Complex expi(Number y) {
		return new Complex(Math.cos(y.doubleValue()), Math.sin(y.doubleValue()));
	}
	
	
	
}
