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

    private static ThreadGroup[] ThreadGroups;

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



    public void run(){

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

   public void runWithGivenTGS(ThreadGroup[] TGS){

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

   public ThreadGroup[] returnThreadGroup(){
        ThreadGroup[] returning = getAllTreadGroup();
        return returning;
   }

}
