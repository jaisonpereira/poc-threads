package br.com.jaison.ThreadHandlerException;

public class Taskh2 implements Runnable {
	int time;

	public Taskh2(int time) {
		super();
		this.time = time;
	}

	@Override
	public void run() {
		System.out.println("Iniciou thread filha" + Thread.currentThread().getName());
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finalizou");
		throw new RuntimeException("Erro forcado");

	}

}
