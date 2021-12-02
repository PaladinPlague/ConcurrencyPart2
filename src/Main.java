
public class Main {

    /*
    * Michael's first test function.
    */
    private static void printThread(Thread t) {
        if (t == null) return;
        System.out.println("\t Thread: " + t.getName( ) +
                    "\n\t Identifier: "+t.getId()+
                    "\n\t Is Alive: "+ t.isAlive( )+
                    "\n\t Priority: " + t.getPriority( ) +
                    (t.isDaemon( )?"\n\t Daemon":"") +
                    "\n");
    }



    /*
     * the following functions are copied from:
     *                                   https://www.oreilly.com/library/view/java-examples-in/0596006209/ch04s03.html
     * replace this function with our own function.
     */

    /** Display info about a thread group and its threads and groups */
    private static void printThreadGroup(ThreadGroup g) {
        if (g == null) return;
        int num_threads = g.activeCount( );
        int num_groups = g.activeGroupCount( );
        Thread[] threads = new Thread[num_threads];
        ThreadGroup[] groups = new ThreadGroup[num_groups];

        g.enumerate(threads, false);
        g.enumerate(groups, false);

        System.out.println("Thread Group: " + g.getName());

        for(int i = 0; i < num_threads; i++)
            printThread(threads[i]);
        for(int i = 0; i < num_groups; i++)
            printThreadGroup(groups[i]);
    }



    /** Find the root thread group and list it recursively */
    public static void listAllThreads() {
        ThreadGroup current_thread_group;
        ThreadGroup root_thread_group;
        ThreadGroup parent;

        // Get the current thread group
        current_thread_group = Thread.currentThread( ).getThreadGroup( );

        // Now go find the root thread group
        root_thread_group = current_thread_group;
        parent = root_thread_group.getParent( );
        while(parent != null) {
            root_thread_group = parent;
            parent = parent.getParent( );
        }

        // And list it, recursively
        printThreadGroup(root_thread_group);
    }


    public static void main(String[  ] args) {
        listAllThreads();
    }


}
