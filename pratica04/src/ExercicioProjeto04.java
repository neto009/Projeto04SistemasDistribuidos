import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExercicioProjeto04 {
    private static List<String> lista = new ArrayList<>();
    private static List<String> currentList = new ArrayList<>(Arrays.asList("Ana Silva", "Bruno Ferreira", "Carlos Machado", "Daniel Santos", "Ester Pereira", "Felipe Almeida", "Gabriel de Paula", "Henrique Souza", "Isis Carvalho", "Joana Aparecida"));

    public static void main(String[] args) throws InterruptedException {
        lista = Collections.synchronizedList(lista);
        MeuRunnable meuRunnable = new MeuRunnable();

        Thread t0 = new Thread(meuRunnable);
        Thread t1 = new Thread(meuRunnable);
        t0.start();
        t1.start();

        Thread.sleep(500);
        System.out.println(lista);
    }

    public static class MeuRunnable implements Runnable {
        private int currentA = 0;
        private int currentB = 0;
        private int currentPosi = 0;

        @Override
        public void run() {
            for(int i = 0; i < currentList.size(); i++) {
                if (Thread.currentThread().getName().equals("Thread-0") && currentA < 5) {
                    lista.add(currentList.get(currentPosi));
                    currentA++;
                    currentPosi++;
                    System.out.println(Thread.currentThread().getName());
                } else if (Thread.currentThread().getName().equals("Thread-1") && currentB < 5) {
                    lista.add(currentList.get(currentPosi));
                    currentB++;
                    currentPosi++;
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
    }
}
