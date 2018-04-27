package br.com.jaison.ThreadTask;

import java.util.concurrent.TimeUnit;

public class Tarefa implements Runnable {

	private long segundos;
	private int id;

	/**
	 *
	 * @param segundos
	 * @param id
	 */
	public Tarefa(long segundos, int id) {
		super();
		this.segundos = segundos;
		this.id = id;
	}

	public void run() {
		System.out.println("Iniciou id: " + this.id + "  Thread: " + Thread.currentThread().getName());
		try {
			long count = this.segundos / 6;
			while (this.segundos > 0) {
				System.out.println("Processando Tarefa id: " + this.id + "  Thread: " + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(count);
				this.segundos -= count;
			}

			System.out.println("Finalizou task " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Kill task " + Thread.currentThread().getName());
			// e.printStackTrace();
		}
	}

}
