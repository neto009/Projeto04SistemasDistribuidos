import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static List<String> lista = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        lista = Collections.synchronizedList(lista);
        MeuRunnable meuRunnable = new MeuRunnable();
        Thread t0 = new Thread(meuRunnable);
        Thread t1 = new Thread(meuRunnable);
        Thread t2 = new Thread(meuRunnable);
        t0.start();
        t1.start();
        t2.start();

        Thread.sleep(500);
        System.out.println(lista);
    }

    public static class MeuRunnable implements Runnable {
        @Override
        public void run() {
                lista.add("Olá mundo!");
            String name = Thread.currentThread().getName();
            System.out.println(name);
        }
    }
}
