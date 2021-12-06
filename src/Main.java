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

        //Create an instance of ThreadMonitor
        ThreadMonitor TM = new ThreadMonitor();
        //Run the Thread Monitor TM

        TM.run();
        ThreadGroup[] group = TM.returnThreadGroup();
        while(true) {
            Thread.sleep(10000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("-------------------------------REFRESHING-------------------------------------------");
            TM.runWithGivenTGS(group);
        }
    }


}
