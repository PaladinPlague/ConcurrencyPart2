public class Main {



    /*
     * Main.java
     * Firstly created by Jackson Blair @ Nov 18, 2021 4:11pm UTC
     *
     */

    public static void main(String[  ] args){

        //Building multiply dummy Thread Groups each fills with some dummy threads
        //just check if the JVM Monitor Namely TM can pick it up.
        DummyGroups dg = new DummyGroups();

        startRefreshing();
        startFunctions();




    }
    public static void startRefreshing(){
        Runnable refreshThread = new refreshThread();
        (new Thread(refreshThread)).start();
    }

    public static void startFunctions(){
        Runnable functionThread = new functionThread();
        (new Thread(functionThread)).start();
    }


}
