public class DummyGroups {
// Creating based on Slides and for Testing purposes

    public DummyGroups(){
        ThreadGroup a = new ThreadGroup("G-A");
        ThreadGroup b = new ThreadGroup("G-B");
        ThreadGroup c = new ThreadGroup("G-C");
        ThreadGroup d = new ThreadGroup("G-D");
        ThreadGroup ae = new ThreadGroup(a,"G-A-E");

        (new Thread(a, new DummyThreads())).start();
        (new Thread(a, new DummyThreads())).start();
        (new Thread(a, new DummyThreads())).start();
        (new Thread(b, new DummyThreads())).start();
        (new Thread(b, new DummyThreads())).start();
        (new Thread(c, new DummyThreads())).start();
        (new Thread(c, new DummyThreads())).start();
        (new Thread(d, new DummyThreads())).start();
        (new Thread(ae, new DummyThreads())).start();
    }


}
class DummyThreads implements Runnable{
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ie) {

                Thread.currentThread().interrupt();
                break;

            }
        }
    }
}