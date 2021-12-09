import java.util.Scanner;

public class ThreadMonitor {

    /*
    * ThreadMonitor.java
    * Algorithms follow Note 8.3
    *
  */


    public static ThreadGroup FindRootThreadGroup(){
        //Get the Thread group we are currently in.
        ThreadGroup current = Thread.currentThread().getThreadGroup();
        //If Parent ThreadGroup not yet Null we are not yet at the System ThreadGroup.
        while(current.getParent()!= null){
            //Find the Parent ThreadGroup and set current to be their parent.
            current = current.getParent();
        }
        return current;
    }

    public static ThreadGroup[] getAllTreadGroup(){
        //Get the root thread group using the previous method
        ThreadGroup root = FindRootThreadGroup();

        //Get an estimate of the number of groups in this thread group
        int num_Groups = root.activeGroupCount();
        //Specify the array size to store the thread groups
        ThreadGroup[] subThreadGroups = new ThreadGroup[num_Groups];
        //Fill the groups array with the ThreadGroups belonging to the System (root) group
        root.enumerate(subThreadGroups);

        //Introduce a new array to store all thread groups
        ThreadGroup[] allThreadGroups = new ThreadGroup[num_Groups+1];

        //Set the first group to the root
        allThreadGroups[0]=root;

        //Then copy the remaining groups (1:n) to the array
        System.arraycopy(subThreadGroups, 0, allThreadGroups, 1, num_Groups);

        //Return the array
        return allThreadGroups;
    }

    public static void printThread(Thread thread) {

        //since enumerate is recursive
        //we need to terminate it when then is no more thread and let it move on to the next thread
        if (thread == null) return;

        //Print out required information such as: name, id, State, priority, is daemon?
        System.out.println("\t Name: " + thread.getName( ) +
                ", id: "+thread.getId()+
                ", state: "+ thread.getState()+
                ", priority: " + thread.getPriority( ) +
                ", is daemon?: "+ thread.isDaemon( ));
    }

    public static String returnThreadInfor(Thread thread){
        //since enumerate is recursive
        //we need to terminate it when then is no more thread and let it move on to the next thread
        if (thread == null) return "";
        //Print out required information such as: name, id, State, priority, is daemon?
        return "Name: " + thread.getName() +
                ", id: " + thread.getId() +
                ", state: " + thread.getState() +
                ", priority: " + thread.getPriority() +
                ", is daemon?: " + thread.isDaemon() + "\n";
    }

    public String searchThread (String threadName) {

        //Searches for that specific thread
        //Uses the same methods as in run() to iterate through all threads
        ThreadGroup[] TGS;
        TGS = getAllTreadGroup();
        for (ThreadGroup tg : TGS) {
            int num_threads = tg.activeCount();
            Thread[] Ts = new Thread[num_threads];
            tg.enumerate(Ts,false);
            for (int x = 0; x < num_threads; x++) {
                //If the thread name is equal to the string the user is looking for, it returns that thread
                if (Ts[x] != null) {
                    if (Ts[x].getName().equals(threadName)) {
                        //If it exists, prints the thread
                        System.out.println("Thread found! ");
                        printThread(Ts[x]);
                        //Note on the below: we can jump out if thread names are unique
                        //Otherwise we need to keep looping
                        //Since we've found the thread we can jump out of the method
                        return returnThreadInfor(Ts[x]);
                    }
                }
            }
        }
        //If it doesn't exist, tells the user
        String error = "There is no thread with this name";
        System.out.println(error);
        return error;
    }

    //Stopping the Thread
    public void stopThread (String name) {



        //Searches for that specific thread
        //Uses the same methods as in run() to iterate through all threads
        ThreadGroup[] TGS;
        TGS = getAllTreadGroup();
        for (ThreadGroup tg : TGS) {
            int num_threads = tg.activeCount();
            Thread[] Ts = new Thread[num_threads];
            tg.enumerate(Ts,false);
            for (int x = 0; x < num_threads; x++) {
                //If the thread name is equal to the string the user is looking for, it returns that thread
                if (Ts[x] != null) {
                    if (Ts[x].getName().equals(name)) {
                        //If it exists, we just interrupt the thread
                        Ts[x].interrupt();
                        System.out.println("Stopping thread(s) successful");
                        return;
                    }
                }
            }
        }
        //If it doesn't exist, tells the user
        System.out.println("There is no thread with this name(s),");
    }


    public String filterGroup(String groupName) {
        System.out.println("Trying to Filter...");
        StringBuilder sb =  new StringBuilder();
        //Uses the same methods as in run() to iterate through all threads
        ThreadGroup[] groups = getAllTreadGroup();
        for (ThreadGroup group : groups) {
            //If that group exists, prints only the threads within in
            if (groupName.equals(group.getName())) {
                int num_threads = group.activeCount();
                Thread[] Ts = new Thread[num_threads];
                group.enumerate(Ts, false);
                for (int x = 0; x < num_threads; x++) {
                    //Print out the details of the current Thread
                    printThread(Ts[x]);
                    sb.append(returnThreadInfor(Ts[x]));
                }
                //Since all threads were then printed we can now jump out of the method
                return sb.toString();
            }
        }
        String error = "No such thread group exists";
        System.out.println(error);
        return error;

    }

    public void tmRun(){

        //Calls the previous method
        ThreadGroup[] TGS;
        TGS = getAllTreadGroup();

        //Iterates over the array that holds all thread
        //groups
        for (ThreadGroup tg : TGS) {
            //print the ThreadGroup Name
            System.out.println(tg.getName());

            //Print the required information
            //iterate over all the Thread in the current ThreadGroup
            int num_threads = tg.activeCount();
            Thread[] Ts = new Thread[num_threads];
            tg.enumerate(Ts,false);
            for (int x = 0; x < num_threads; x++) {
                //Print out the details of the current Thread
                printThread(Ts[x]);

            }
        }
    }
}
class refreshThread implements Runnable{

    ThreadMonitor monitor = new ThreadMonitor();
    @Override
    public void run() {
        System.out.println("------------------------------------STARTING----------------------------------------");
        monitor.tmRun();
        while (true){
            try{
                Thread.sleep((20000));
                System.out.println("------------------------------------REFRESHING----------------------------------------");

                monitor.tmRun();
            }catch (InterruptedException ie){}
        }
    }
}
