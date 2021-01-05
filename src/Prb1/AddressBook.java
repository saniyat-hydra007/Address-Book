package Prb1;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class AddressBook extends JFrame{
    JPanel pText, pBody, pfooter;
    JButton info,InfoAdder,search;
    JLabel jl,jl2,jl3,jl4,jl5,jl6;
    JTextArea jta,jta2;
    JTextField jtf,jtf1, jtf2,src;
    Container contentpane;
    String found,name, cgpa, number;


public AddressBook() throws FileNotFoundException {
        super("Book");
        contentpane = getContentPane();
        contentpane.setLayout(new BorderLayout());

        pText = new JPanel();
        pBody = new JPanel();
        pfooter = new JPanel();

        jta = new JTextArea(20,25);
        jta2=new JTextArea(10,25);

        jtf=new JTextField(15);
        jtf1 = new JTextField(15);
        jtf2 = new JTextField(15);
        src=new JTextField(15);


// Set BorderLayout for the panel, add label
        jl5= new JLabel("Info");
        pBody.add(jl5);
        pBody.add(jta, BorderLayout.CENTER);

        jl6=new JLabel("Searching Info");
        pBody.add(jl6);
        pBody.add(jta2,BorderLayout.CENTER);

        jl = new JLabel("Address Book");

        info = new JButton("Show");
        InfoAdder=new JButton("add");
        search=new JButton("Search");

        pText.add(jl);
        pfooter.add(info);
        jl2=new JLabel("Name");
        pBody.add(jl2);
        pBody.add(jtf);
        jl3=new JLabel("CGPA");
        pBody.add(jl3);
        pBody.add(jtf1);
        jl4=new JLabel("Number");
        pBody.add(jl4);
        pBody.add(jtf2);


        pBody.add(InfoAdder);
        pfooter.add(src);
        pfooter.add(search);
        contentpane.add(pText,BorderLayout.NORTH);
        contentpane.add(pBody,BorderLayout.CENTER);
        contentpane.add(pfooter,BorderLayout.SOUTH);
        setSize(700, 500);
        setVisible(true);
//add new info
        InfoAdder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                name = jtf.getText().trim();
                String data = name;

                cgpa = jtf1.getText().trim();
                String data2 = cgpa;

                number = jtf2.getText().trim();
                String data3 = number;
                try{
                    FileWriter reader=new FileWriter("A.txt",true);
                    reader.write(data + " " + data2 + " " + data3 + "\n");
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        info.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//Read from file
                try{
                    FileInputStream in = new FileInputStream("A.txt");
                    BufferedReader iS = new BufferedReader(new InputStreamReader(in));
                    StringWriter sw = new StringWriter();
                    PrintWriter out = new PrintWriter(sw);
                    String il;

                    while ((il = iS.readLine()) != null){
                        out.println(il);
                    }
                    jta.setText(sw.getBuffer().toString());

                    out.flush();
                    in.close();
                    iS.close();
                    sw.close();
                    out.close();
                }catch (FileNotFoundException e4) {
                }
                catch(java.io.IOException ex){
                    System.out.println("Cannot read from file");
                }
            }
        });

//search
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                found=src.getText().trim();
                String data4= found;
                try {
                    FileInputStream f = new FileInputStream("A.txt");
                    Scanner reader2 = new Scanner(f);

                    String search = reader2.toString();

                    while (reader2.hasNextLine()){

                        String s = reader2.nextLine();
                        String str[] = s.split(" ");

                        if (data4.equals(str[0].trim())) {
                            jta2.setText(s);
                        }
                    }
                    reader2.close();
                    } catch(IOException exception){
                        exception.printStackTrace();
                    }
            }
            });

    }

    public static void main(String[] args) throws FileNotFoundException {
        new AddressBook();
    }
}