package br.com.jaison.ThreadHandlerException;

import java.util.ArrayList;
import java.util.List;

public class Taskh1 {

	public static void main(String[] args) {
		List<Thread> list = new ArrayList<>();
		Thread task = new Thread(new Taskh2(13000), "task1");

		Thread task2 = new Thread(new Taskh2(3000), "task2");
		task.run();
		task2.run();
		list.add(task);
		list.add(task2);

		try {
			for (Thread thread : list) {

				thread.join();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Finalizou Principal");

	}

}
