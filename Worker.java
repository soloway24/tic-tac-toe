package lection2.tictak;

public class Worker extends Thread {

    private int id;
    private Data data;

    public Worker(int id, Data d) {
        this.id = id;
        data = d;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            synchronized (data) {
                try {
                    while (id != data.getState()) {
                        data.wait();
                    }

                    if (id == 1) {
                        data.Tic();
                    } else if (id == 2) {
                        data.Tac();
                    } else {
                        data.Toe();
                    }

                    data.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
