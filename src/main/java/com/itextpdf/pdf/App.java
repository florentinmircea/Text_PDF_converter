package com.itextpdf.pdf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu f;
    JMenuItem s, o, e, c, n;
    JTextArea ta;
    JScrollPane scroll;
    Processing p = new Processing();

    App() {
        mb = new JMenuBar();
        f = new JMenu("File");

        s = new JMenuItem("Save");
        o = new JMenuItem("Open");
        e = new JMenuItem("Exit");
        c = new JMenuItem("Convert");
        n = new JMenuItem("New");

        s.addActionListener(this);
        o.addActionListener(this);
        e.addActionListener(this);
        c.addActionListener(this);
        n.addActionListener(this);

        mb.add(f);
        f.add(n);
        f.add(o);
        f.add(s);
        f.add(c);
        f.add(e);

        setJMenuBar(mb);

        ta = new JTextArea();


        scroll = new JScrollPane(ta,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll);


        setTitle("Text to PDF converter");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        String t = ((JMenuItem) e.getSource()).getLabel();
        if (t.equals("New")) {
            FileDialog d = new FileDialog(App.this, "Where to save your document", FileDialog.SAVE);
            d.setVisible(true);
            try {
                p.save(d.getDirectory() + d.getFile(), ta);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            p.clear(ta);
        }

        if (t.equals("Open")) {
            FileDialog d = new FileDialog(App.this, "Location of the file to be opened", FileDialog.LOAD);
            d.setVisible(true);
            System.out.println(d.getDirectory() + d.getFile());
            try {
                p.open(d.getDirectory() + d.getFile(), ta);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (t.equals("Save")) {
            FileDialog d = new FileDialog(App.this, "Where to save your document", FileDialog.SAVE);
            d.setVisible(true);
            try {
                p.save(d.getDirectory() + d.getFile(), ta);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (t.equals("Convert")) {
            FileDialog d = new FileDialog(App.this, "Where to save your pdf", FileDialog.SAVE);
            d.setVisible(true);
            try {
                p.convert(d.getDirectory() + d.getFile(), ta);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (t.equals("Exit")) {
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new App();
    }
}
