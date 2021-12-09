import java.util.Scanner;

public class ThreadMonitor {

    /*
    * ThreadMonitor.java
    * Firstly created by Nuoxu Li @ 03 Dec 2021
    *
    * Algorithms follow Note 8.3
    *
    * Notes to Suleman, Scott:
    *   this program is just print out the required information of each thread.
    *   for Part A/Task 2/2.2 (second 10 marker) you need to make use of getAllThreadGroups() function,
    *   that will return you an Array of all the Thread group currently in JVM. after that is upto u to do what ever.
    * Note to Mark:
    *   For GUI you may need to add some new methods to make return a strings for u to put in GUI.
     */

    Scanner sc = new Scanner(System.in);

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

    public void searchThread () {
        //Sets up a new scanner for this method
        //Asks the user for the name of a thread
        System.out.print("Enter the thread name: ");
        String name = sc.nextLine();
        //Closes the scanner after we're done

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
                        //If it exists, prints the thread
                        System.out.println("Thread found! ");
                        printThread(Ts[x]);
                        //Note on the below: we can jump out if thread names are unique
                        //Otherwise we need to keep looping
                        //Since we've found the thread we can jump out of the method
                        return;
                    }
                }
            }
        }
        //If it doesn't exist, tells the user
        System.out.println("There is no thread with this name");
    }

    public void filterGroup() {
        //Sets up a new scanner for this method
        //Asks the user for the name of a thread group
        System.out.print("Enter the group name: ");
        String name = sc.nextLine();

        //Uses the same methods as in run() to iterate through all threads
        ThreadGroup[] groups = getAllTreadGroup();
        for (ThreadGroup group : groups) {
            //If that group exists, prints only the threads within in
            if (name.equals(group.getName())) {
                int num_threads = group.activeCount();
                Thread[] Ts = new Thread[num_threads];
                group.enumerate(Ts, false);
                for (int x = 0; x < num_threads; x++) {
                    //Print out the details of the current Thread
                    printThread(Ts[x]);
                }
                //Since all threads were then printed we can now jump out of the method
                return;
            }
        }
        System.out.println("No such thread group exists");
    }

    public void funcRun(){

        //Search for a thread via name
        System.out.println();
        //Asks the user if they want to search using a scanner to read lines
        System.out.print("Search for a thread? [Y/N]: ");
        //If they do, searches for a thread with a name later defined
        if (sc.nextLine().equals("Y")) {
            searchThread();
        }

        //Filter by a thread group
        System.out.println();
        //Asks the user if they want to filter the thread list by group
        System.out.print("Filter by thread group? [Y/N]: ");
        if (sc.nextLine().equals("Y")) {
            filterGroup();
        }

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
                Thread.sleep((10*1000));
                System.out.println("------------------------------------REFRESHING----------------------------------------");
                monitor.tmRun();
            }catch (InterruptedException ie){}
        }
    }
}

class functionThread implements Runnable{

    ThreadMonitor monitor = new ThreadMonitor();
    @Override
    public void run() {

        while (true){
            System.out.println("------------------------------------Question Time----------------------------------------");
            monitor.funcRun();
        }
    }
}