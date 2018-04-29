package br.com.jaison.Threads;

import java.io.File;
import java.util.Scanner;

public class TarefaBuscaTextual implements Runnable {

	private String nomeArquivo;
	private String nome;

	public TarefaBuscaTextual(String nomeArquivo, String nome) {
		this.nomeArquivo = nomeArquivo;
		this.nome = nome;
	}

	@Override
	public void run() {
		try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
			int numberLine = 1;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.toLowerCase().contains(nome.trim().toLowerCase())) {
					System.out.println("Thread : " + Thread.currentThread().getName());
					System.out.println(nomeArquivo + " - Linha: " + numberLine + " nome " + line);
				}
				numberLine++;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

}
