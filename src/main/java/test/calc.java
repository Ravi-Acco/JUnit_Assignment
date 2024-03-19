package test;

public class calc implements Cal {	
	
	@Override
	public int add(int a, int b) {
		return a+b;
	}
	@Override
	public int sub(int a, int b) {
		return a-b;
	}
	@Override
	public int mul(int a, int b) {
		return a*b;
	}
	@Override
	public float div(int a, int b) {
		return a/b;
	}
	
}
