
public class Test {
	static int a = 1;
	int b = 2;
	// final int c ;
	final static int d = 111;
	private final String str2;
	// public Test(int b, int c, String str2) {
	// super();
	// this.b = b;
	// this.c = c;
	// this.str2 = str2;
	// }

	{
		str2 = "2";
	}
	// public Test(){
	// c=1;
	// str2 ="2";
	// }

	public static void main(String[] args) throws InterruptedException {
		Test t = new Test();
		// System.out.println(a);
		final int ss = 22;
		Thread s = new Thread(() -> {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ss=222;
			System.out.println(t.b);
			System.out.println(ss);
		});
		s.start();
		// s.sleep(1000);
		// c = new Test();
		// ss=22222;
	}

}
