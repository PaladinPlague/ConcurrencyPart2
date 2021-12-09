import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.TimerTask;

public class Main {

    /*
     * Main.java
     * Firstly created by Jackson Blair @ Nov 18, 2021 4:11pm UTC
     */

    public static void main(String[] args){

        //Building multiply dummy Thread Groups each fills with some dummy threads
        //just check if the JVM Monitor Namely TM can pick it up.
        DummyGroups dg = new DummyGroups();

        ThreadMonitor monitor = new ThreadMonitor();

        JFrame frame = new JFrame("Concurrency GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();


        frame.setSize(1080, 540);

        JButton startBtn = new JButton("Start Thread");

        JTextField console = new JTextField("Check Console for output! :)");



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

        panel1.add(startBtn);
        panel1.add(console);

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


        frame.getContentPane().add(panel1,"Center");
        frame.getContentPane().add(panel2,"North");


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

    }

    public static void startRefreshing(){
        Runnable refreshThread = new refreshThread();
        (new Thread(refreshThread)).start();
    }








}
