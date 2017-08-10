package personal.xzr.practice.basic;

import java.lang.reflect.Method;

public class MethodTest {

	public void test1(int i){
		
	}
	public void test1(Integer i){
		
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException{
		
		Method method= MethodTest.class.getMethod("test1", int.class);
		 Class<?>[] clazzArray =method.getParameterTypes();
		 for(Class z: clazzArray){
			 System.out.println(z.getName());
		 }
	}
}
