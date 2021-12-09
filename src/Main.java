public class Main {



    /*
     * Main.java
     * Firstly created by Jackson Blair @ Nov 18, 2021 4:11pm UTC
     *
     */

    public static void main(String[  ] args) throws InterruptedException {

        //Building multiply dummy Thread Groups each fills with some dummy threads
        //just check if the JVM Monitor Namely TM can pick it up.
        DummyGroups dg = new DummyGroups();

        startRefreshing();
        startFunctions();




    }
    public static void startRefreshing(){
        Object refreshThread = new refreshThread();
        (new Thread((Runnable) refreshThread)).start();
    }

    public static void startFunctions(){
        Object functionThread = new functionThread();
        (new Thread((Runnable) functionThread)).start();
    }


}
