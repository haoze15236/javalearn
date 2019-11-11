package com.javase;
/**
 * 
 * @author zee
 * 
 */
 class Circle {
    private double radius;
	/**
	 * 
	 * @param r 圆的半径
	 */
	public Circle(double r){
		this.radius =r;
	}
	
	public Circle(int i){
		this.radius = Math.pow(i, 2);
	}
	/**
	 * 
	 * @return 圆的面积
	 */
	public double getArea(){
		return Math.PI * Math.pow(this.radius, 2);
	}
	/**
	 * 
	 * @return 圆的周长
	 */
	public double getPermeter(){
		return 2*Math.PI*this.radius;
	}
	
	public void show(){
		System.out.println("圆的半径为："+this.radius);
		System.out.println("圆的面积为："+getArea());
		System.out.println("圆的周长为："+getPermeter());
		System.out.printf("圆的周长为：%s\n", getPermeter());
	}
}
 class Cylinder extends Circle{
	 private double hight;

	 /*
	  * 子类构造方法第一行必须调用任意一个父类构造方法
	  */
	 public Cylinder(double hight,double radius){
		 super(radius);
		 this.hight = hight;
	 }
	 
	 double getVolume(){
		 return getArea()*this.hight;
	 }
	 
	 void showVolume(){
		 System.out.println("圆柱体体积为："+ getVolume());
	 }
 }
 

 class TestCircle{
	 public static void main(String[] args){
		Circle c = new Circle(3.0);
		c.show();
		Cylinder cd = new Cylinder(10,3);
		cd.showVolume();
	 }
 }

