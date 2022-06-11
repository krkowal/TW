package lab5;

class Producer extends Thread {
    private Buffer buf;

    public Producer(Buffer buffer) {
        this.buf = buffer;
    }

    public void run() {
        int[] a = new int[(int) (Math.random() * 10 + 1)];
        for (int i = 1; i < a.length; i++) a[i] = (int) (Math.random() * 10 + 1);
        while (true) {
            buf.put(a);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}