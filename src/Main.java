import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    /*
     * Main.java
     * Firstly created by Jackson Blair @ Nov 18, 2021 4:11pm UTC
     *
     */

    public static void main(String[] args){

        JFrame frame = new JFrame("Concurrency GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        frame.setSize(750, 150);
        JLabel filterLabel = new JLabel("Filter: ");
        JButton button1 = new JButton("Start Thread");
        JTextField filter = new JTextField(20);
        JButton submitButton1 = new JButton("Submit");
        JLabel searchLabel = new JLabel("Search: ");
        JTextField search = new JTextField(20);
        JButton submitButton2 = new JButton("Submit");
        JTextField console = new JTextField(20);
        ThreadMonitor checking = new ThreadMonitor();

        panel1.add(button1);
        panel2.add(filterLabel);
        panel2.add(filter);
        panel2.add(submitButton1);
        panel2.add(searchLabel);
        panel2.add(search);
        panel2.add(submitButton2);


        frame.getContentPane().add(BorderLayout.SOUTH, panel1);
        frame.getContentPane().add(BorderLayout.BEFORE_LINE_BEGINS, panel2);
        frame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main starting = new Main();
                starting.start();
            }

        });
        submitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(search.getText() != ""){

                    checking.searchThread(search.getText());


                }
            }
        });
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(filter.getText() != ""){

                    checking.filterGroup(filter.getText());


                }
            }
        });






    }

    public void start(){
        //Building multiply dummy Thread Groups each fills with some dummy threads
        //just check if the JVM Monitor Namely TM can pick it up.
        DummyGroups dg = new DummyGroups();

        startRefreshing();
        //startFunctions();
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
