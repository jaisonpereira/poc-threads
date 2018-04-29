package br.com.jaison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import br.com.jaison.Threads.TarefaBuscaTextual;

/**
 * @author jpereira
 *
 *         THREADS - poc
 *
 *         java.lang.Thread - start( ) - sleep(..)
 *
 *         java.lang.Runnable
 *
 *         - run( ), interrupeted();
 *
 *         -java.lang.Object - wait() -notify()
 *
 *         synchronized
 *
 *         ---- UTILIZAR JCONSOLE ------
 * 
 *
 *
 */
public class Principal {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("thread: dentro de 1 segundo vai inicializar");
		Thread.sleep(1000);
		new Principal().iniciaBuscaArquivo();

	}

	private void iniciaBuscaArquivo() {
		String nome = "Jon";

		// collecao sincronizada
		List<String> lista = Collections.synchronizedList(new ArrayList<String>());
		// vector ja é sincronizado
		Vector<String> vectorStringList = new Vector<>();

		/**
		 * E possivel pausar uma thread e com == so funciona em cima de um bloco
		 * synchronized
		 * 
		 * this.wait();
		 * 
		 * e notificar todas a threads com
		 * 
		 * this.notifyAll();
		 * 
		 * setar thread como daemon(true) ou seja ela so é executada se tiver threads
		 * aguardando thread.setDaemon(true)
		 */

		Thread tarefaAssinaturas1 = new Thread(new TarefaBuscaTextual("assinaturas1.txt", nome), "Thread ass 1");
		Thread tarefaAssinaturas2 = new Thread(new TarefaBuscaTextual("assinaturas2.txt", nome), "T ass 2");
		Thread tarefaAutores = new Thread(new TarefaBuscaTextual("autores.txt", nome), "T aut");

		tarefaAssinaturas1.start();
		tarefaAssinaturas2.start();
		tarefaAutores.start();

	}

	private void simulaProcessa() throws InterruptedException {
		boolean finish = false;
		List<Integer> listExecutados = new ArrayList<>();
		synchronized (listExecutados) {

			for (int i = 0; i < 1000; i++) {
				System.out.println("test" + i);
			}
			this.notify();
			finish = true;

		}
		// notifica termino da execucao , porem so funciona em cima de um bloco
		// syncronized

		/**
		 * tem que previnir problemas em que a execucao acima tenta notificar algo que
		 * nem esta esperando ainda
		 */
		synchronized (listExecutados) {
			System.out.println("aguardando notificacao");
			// se nao terminou ela espera
			// optar pelo while caso alguma operacao concorrente passe por esse ponto , ex
			// 3- n threads
			while (!finish) {
				this.wait();
			}

			// so vai executar daqui pra ca se receber notificao
			System.out.println("Executou");
		}
	}

}
