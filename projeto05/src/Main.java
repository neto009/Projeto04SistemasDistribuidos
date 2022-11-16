import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static BlockingQueue<String> lista = new LinkedBlockingQueue<>();
    private static List<String> currentList = Collections.synchronizedList(Arrays.asList("Ana Silva", "Bruno Ferreira",
            "Carlos Machado", "Daniel Santos", "Ester Pereira", "Felipe Almeida",
            "Gabriel de Paula", "Henrique Souza", "Isis Carvalho", "Joana Aparecida"));

    public static void main(String[] args) {
        MeuRunnableQueue meuRunnableQueue = new MeuRunnableQueue();

        Thread queue = new Thread(meuRunnableQueue);

        queue.start();
    }



    public static class MeuRunnableQueue implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < currentList.size(); i++) {
                lista.add(currentList.get(i));
                System.out.println(lista);
            }
            MeuRunnable meuRunnable = new MeuRunnable();
            Thread t0 = new Thread(meuRunnable);
            Thread t1 = new Thread(meuRunnable);
            t0.start();
            t1.start();
        }
    }

    public static class MeuRunnable implements Runnable {
        private int currentA = 0;
        private int currentB = 0;

        @Override
        public void run() {
            try {
                for(int i = 0; i < currentList.size(); i++) {
                    if (Thread.currentThread().getName().equals("Thread-1") && currentA < 5) {
                        lista.remove();
                        System.out.println(lista);
                        currentA++;
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(5000);
                    } else if (Thread.currentThread().getName().equals("Thread-2") && currentB < 5) {
                        lista.remove();
                        System.out.println(lista);
                        currentB++;
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(5000);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
