package br.com.jaison.ThreadTask;

public class Tarefa implements Runnable {

	private long millis;
	private int id;

	public Tarefa(long millis, int id) {
		super();
		this.millis = millis;
		this.id = id;
	}

	public void run() {
		System.out.println("Iniciou id: " + this.id + "  Thread: " + Thread.currentThread().getName());
		try {
			long count = this.millis / 6;
			while (this.millis > 0) {
				System.out.println("Processando Tarefa id: " + this.id + "  Thread: " + Thread.currentThread().getName());
				Thread.sleep(count);
				this.millis -= count;
			}

			System.out.println("Finalizou task " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Kill task " + Thread.currentThread().getName());
			// e.printStackTrace();
		}
	}

}
