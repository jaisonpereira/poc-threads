package br.com.jaison;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.jaison.ThreadTask.Tarefa;

/**
 * @author Jaison Pereira 27 de abr de 2018
 *
 *         Trabalhando com java.util.Concurrent
 *
 *         Trabalhando com ExecutorService -Pool de threads
 *
 *         -CachedThreadPool caso a thread nao seja utilizada por 60 segundos
 *         ele elimina ela do
 *
 *         -FixedThreadPool numero de threads fixo
 *
 *         volatile - acessa memoria principal ao inves de cache do Objeto
 *
 *         AtomicBoolean encapsulamento de um booleano
 *
 *
 *
 */
public class Principal {
	/**
	 * ignora cache e acessa memoria principal
	 */
	volatile boolean isRunning = false;

	/**
	 * as mesmas caracteristicas de um volatile booleano
	 *
	 * @param args
	 */

	private AtomicBoolean estaRodando = new AtomicBoolean(true);

	public static void main(String[] args) {

		Principal main = new Principal();
		try {
			main.testePoolThread();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void testePoolThread() throws InterruptedException {
		// Thread task1 = new Thread(new Tarefa(1000), "Tarefa1");
		// Thread task2 = new Thread(new Tarefa(1000), "Tarefa2");

		ExecutorService pool = Executors.newFixedThreadPool(2);
		// caso a thread nao seja utilizada por 60 segundos ele elimina ela do
		// pool com cached thread
		// ExecutorService threadPool2 = Executors.newCachedThreadPool();

		pool.execute(new Tarefa(30, 1));
		pool.execute(new Tarefa(30, 2));

		/**
		 * necessario chamar shutdownow antes de terminated caso contrario , is
		 * terminated nunca vai retornar true
		 *
		 */
		/**
		 * Interrompe as threads necessario o run estar envolvido em um try -
		 * catch
		 *
		 * pool.shutdownNow();
		 *
		 */

		System.out.println("Agendou comando  shutdown para quando as tarefas terminarem");
		pool.shutdown();

		/**
		 * Ele so ira fazer o shutdown quando nao tiver mas nenhuma tarefa
		 * rodando
		 */
		// while (!pool.isTerminated()) {
		// System.out.println("Aguardando threads terminarem");
		// }

		try {
			/**
			 * return false se ele matou alguem
			 */
			System.out.println(pool.awaitTermination(40, TimeUnit.SECONDS));
			pool.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (pool.isShutdown()) {
			System.out.println("Pool Shutdown executor");
		}
		Thread.sleep(15000);
	}

	public void testTimeOut() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		final Future handler = executor.submit(new Callable() {
			public Object call() throws Exception {
				System.out.println("executou call");
				return null;
			}

		});
		executor.schedule(new Runnable() {
			public void run() {
				handler.cancel(Principal.this.isRunning);
			}
		}, 10000, TimeUnit.MILLISECONDS);
	}

}
