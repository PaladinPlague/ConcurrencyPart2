import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class Main {

    /*
     * Main.java
     * This class mainly consists the Bonus GUI part +
     */

    public static void main(String[] args){

        //Building multiply dummy Thread Groups each fills with some dummy threads
        //just check if the JVM Monitor Namely TM can pick it up.
        DummyGroups dg = new DummyGroups();

        ThreadMonitor monitor = new ThreadMonitor();

        JFrame frame = new JFrame("Thread Monitoring GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel startPanel = new JPanel();
        JPanel infoPanel = new JPanel();


        frame.setSize(1920, 400);
        frame.setResizable(true);

        JButton startBtn = new JButton("Start Thread");


        JLabel filterLabel = new JLabel("Filter: ");
        JTextField filter = new JTextField(20);
        JButton filterSubmitButton = new JButton("Submit");
        JTextArea filterReturn = new JTextArea(10,10);
        filterReturn.setEditable(false);

        JLabel searchLabel = new JLabel("Search: ");
        JTextField search = new JTextField(20);
        JButton searchSubmitButton = new JButton("Submit");
        JTextArea searchReturn = new JTextArea(10,10);
        searchReturn.setEditable(false);
        JLabel stopLabel = new JLabel("Stop Thread/s: ");
        JTextField stopText = new JTextField(20);
        JButton submitButton3 = new JButton("Submit");


        JLabel info = new JLabel("<html><body><h2>INFORMATION:</h2><p> - To start please press Start Thread, this will allow you see the thread information inside the console</p> <p> - To stop a Thread, you need to enter the name of the Thread and check console for result</p>  <p> - To stop multiple Threads, you need to enter the list of thread names separated with comma with no space in between.</p> </body></html>");

        infoPanel.add(info);

        panel1.add(startBtn);


        panel1.add(stopLabel);
        panel1.add(stopText);
        panel1.add(submitButton3);


        panel2.add(filterLabel);
        panel2.add(filter);
        panel2.add(filterSubmitButton);
        panel2.add(filterReturn);
        filterReturn.setText("Results will be displayed here!");

        panel2.add(searchLabel);
        panel2.add(search);
        panel2.add(searchSubmitButton);
        panel2.add(searchReturn);
        searchReturn.setText("Results will be displayed here!");

        frame.getContentPane().add(startPanel,BorderLayout.CENTER);
        frame.getContentPane().add(infoPanel,BorderLayout.NORTH);
        frame.getContentPane().add(panel1,BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.PAGE_END);


        frame.setVisible(true);

        startBtn.addActionListener(e -> {
            startRefreshing();
        });



        filterSubmitButton.addActionListener(e -> {
            if(filter.getText()!=null){
                filterReturn.setText(monitor.filterGroup(filter.getText()));

            }

        });

        searchSubmitButton.addActionListener(e -> {
            if(search.getText()!=null){
                searchReturn.setText(monitor.searchThread(search.getText()));
            }
        });

        submitButton3.addActionListener(e -> {
            if(!Objects.equals(stopText.getText(), "")){
                String[] threadList = stopText.getText().split(",");
                for (String s : threadList) {
                    System.out.println("Trying to stop thread(s):");
                    System.out.println(s);
                    monitor.stopThread(s);
                }



            }
        });


    }

    public static void startRefreshing(){
        Runnable refreshThread = new refreshThread();
        (new Thread(refreshThread)).start();
    }


}
