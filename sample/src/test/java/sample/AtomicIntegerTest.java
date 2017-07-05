package sample;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	public static void main(String[] args) {
		AtomicInteger atom = new AtomicInteger();
		atom.incrementAndGet();
		System.out.println(atom);
		System.out.println(atom.incrementAndGet());
		System.out.println(atom.incrementAndGet());

		System.out.println(atom.incrementAndGet());

		System.out.println(atom.incrementAndGet());

	}
}
