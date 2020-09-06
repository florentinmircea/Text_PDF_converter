package com.itextpdf.pdf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App extends JFrame implements ActionListener {
    JMenuBar mb;//menu bar
    JMenu f, edit;//file tab,edit tab
    JMenuItem s, o, e, c, n;//save,open,exit,convert,new file
    JMenuItem cut, copy, paste, u, gt, fi, re, td;//undo,go to,find,replace,time and date
    JTextArea ta;//text area
    JScrollPane scroll;//scroll bar for text area
    Processing p = new Processing();//processing the operations

    App() {
        mb = new JMenuBar();
        f = new JMenu("File");
        edit = new JMenu("Edit");

        s = new JMenuItem("Save");
        o = new JMenuItem("Open...");
        e = new JMenuItem("Exit");
        c = new JMenuItem("Convert");
        n = new JMenuItem("New");

        s.addActionListener(this);
        o.addActionListener(this);
        e.addActionListener(this);
        c.addActionListener(this);
        n.addActionListener(this);

        u = new JMenuItem("Undo");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        gt = new JMenuItem("Go To...");
        fi = new JMenuItem("Find...");
        re = new JMenuItem("Replace...");
        td = new JMenuItem("Time/Date");

        u.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        gt.addActionListener(this);
        fi.addActionListener(this);
        re.addActionListener(this);
        td.addActionListener(this);

        mb.add(f);
        mb.add(edit);
        f.add(n);
        f.add(o);
        f.add(s);
        f.add(c);
        f.add(e);
        edit.add(u);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(fi);
        edit.add(re);
        edit.add(gt);
        edit.add(td);

        setJMenuBar(mb);

        ta = new JTextArea();
        ta.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    FileDialog d = new FileDialog(App.this, "Where to save your document", FileDialog.SAVE);
                    d.setVisible(true);
                    try {
                        p.save(d.getDirectory() + d.getFile(), ta);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    FileDialog d = new FileDialog(App.this, "Location of the file to be opened", FileDialog.LOAD);
                    d.setVisible(true);
                    System.out.println(d.getDirectory() + d.getFile());
                    try {
                        p.open(d.getDirectory() + d.getFile(), ta);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                if ((e.getKeyCode() == KeyEvent.VK_N) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    FileDialog d = new FileDialog(App.this, "Where to save your document", FileDialog.SAVE);
                    d.setVisible(true);
                    try {
                        p.save(d.getDirectory() + d.getFile(), ta);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    p.clear(ta);
                }

                if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && ((e.getModifiers() & KeyEvent.ALT_MASK) != 0)) {
                    FileDialog d = new FileDialog(App.this, "Where to save your pdf", FileDialog.SAVE);
                    d.setVisible(true);
                    try {
                        p.convert(d.getDirectory() + d.getFile(), ta);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        scroll = new JScrollPane(ta,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll);


        setTitle("Text to PDF converter");
        setLocationRelativeTo(null);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        String t = ((JMenuItem) e.getSource()).getLabel();
        //System.out.println(e.getSource());
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

        if (t.equals("Cut")) {
            ta.cut();
        }

        if (t.equals("Copy")) {
            ta.copy();
        }

        if (t.equals("Paste")) {
            ta.paste();
        }

        //trebuie implemetata functionalitatea e doar un form gol
        if (t.equals("Find...")) {
            JFrame find = new JFrame("Find");
            find.setSize(300, 150);
            find.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            find.setLocationRelativeTo(null);
            find.setVisible(true);

        }

        //trebuie implemetata functionalitatea e doar un form gol
        if (t.equals("Replace...")) {
            JFrame replace = new JFrame("Replace");
            replace.setSize(300, 150);
            replace.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            replace.setLocationRelativeTo(null);
            replace.setVisible(true);

        }

        //trebuie implemetata functionalitatea e doar un form gol
        if (t.equals("Go To...")) {
            JFrame go = new JFrame("Go to line");
            go.setSize(300, 150);
            go.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            go.setLocationRelativeTo(null);
            go.setVisible(true);

        }

        if (t.equals("Time/Date")) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
            ta.append(formatter.format(date));

        }



    }

    public static void main(String[] args) {
        new App();
    }
}
