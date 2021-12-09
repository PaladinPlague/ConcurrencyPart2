public class DummyGroups {

    public DummyGroups(){
        ThreadGroup a = new ThreadGroup("G(A)");
        ThreadGroup b = new ThreadGroup("G(B)");
        ThreadGroup c = new ThreadGroup("G(C)");
        ThreadGroup d = new ThreadGroup("G(D)");
        ThreadGroup ae = new ThreadGroup(a,"G(A(E))");

        (new Thread(a, new DummyThreads())).start();
        (new Thread(a, new DummyThreads())).start();
        (new Thread(a, new DummyThreads())).start();
        (new Thread(ae, new DummyThreads())).start();
        (new Thread(b, new DummyThreads())).start();
        (new Thread(b, new DummyThreads())).start();
        (new Thread(c, new DummyThreads())).start();
        (new Thread(c, new DummyThreads())).start();
        (new Thread(d, new DummyThreads())).start();
        (new Thread(ae, new DummyThreads())).start();
        //set up a list of dummy thread which do Nothing

    }

    static class DummyThreads implements Runnable{
        @Override
        public void run() {
            while (true){
                try{
                    Thread.sleep(1000);
                    for(int i = 0; i<1000000; i++);
                }catch (InterruptedException ie){}
            }
        }
    }


}

