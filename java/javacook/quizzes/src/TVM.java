import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

/** TestEdit View Menu for application */
public class TVM extends TV {
	MenuBar mb;
	Menu fm, em, vm, om, hm;	// File, Edit, View, Options, Help
	CheckboxMenuItem cb;	// Option that can be on or off.

	/** Construct the object including its GUI */
	public TVM(JFrame f, TD mod) {
		super(f, mod);

		// Set up the Menu hierarchy
		MenuItem mi;
		mb = new MenuBar();
		frm.setMenuBar(mb);		// Frame implements MenuContainer

		// The File Menu...
		fm = new Menu("File");
			fm.add(mi = new MenuItem("Open...", new MenuShortcut('O')));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					theTD.loadFile(null);
				}
			});
			fm.add(mi = new MenuItem("New..."));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					theTD.doNew();
				}
			});
			fm.add(mi = new MenuItem("Save", new MenuShortcut('S')));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					theTD.saveFile();
				}
			});
			fm.add(mi = new MenuItem("Save As..."));
			mi.setEnabled(false);
			fm.add(mi = new MenuItem("Close", new MenuShortcut('W')));
			mi.setEnabled(false);
			fm.addSeparator();
			fm.add(mi = new MenuItem("Export to RTF..."));
			mi.setEnabled(false);
			fm.add(mi = new MenuItem("Export to Sylvan Prometric..."));
			mi.setEnabled(false);
			fm.add(mi = new MenuItem("Export to HTML..."));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					theTD.saveHTML("test.html");
				}
			});
			fm.addSeparator();
			fm.add(mi = new MenuItem("Print draft", new MenuShortcut('P')));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					theTD.doPrint();
				}
			});
			fm.add(mi = new MenuItem("Print Student copy"));
			mi.setEnabled(false);
			fm.addSeparator();
			fm.add(mi = new MenuItem("Exit"));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					theTD.exit(0);
				}
			});
		mb.add(fm);

		// The Edit Menu...
		em = new Menu("Edit");
			em.add(mi = new MenuItem("Find...", new MenuShortcut('F')));
			em.addSeparator();
			em.add(mi = new MenuItem("Copy Entire Question"));
			mi.setEnabled(false);
			em.add(mi = new MenuItem("Cut Entire Question"));
			mi.setEnabled(false);
			em.add(mi = new MenuItem("Paste Question"));
			mi.setEnabled(false);
			em.addSeparator();
			em.add(mi = new MenuItem("Preferences"));
			mi.setEnabled(false);
		mb.add(em);

		// The View menu...
		vm = new Menu("View");
			vm.add(mi = new MenuItem("Exam Information..."));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TD.mainPane.show("Exam Info");
				}
			});
			vm.add(mi = new MenuItem("Statistics..."));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					theTD.doStats();	// calls us back at showStats();
				}
			});
			vm.addSeparator();
			vm.add(mi = new MenuItem("Windows Look and Feel"));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent wlfe) {
					try {
						UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						SwingUtilities.updateComponentTreeUI(frm);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
							"setLookAndFeel didn't work: " + e,
							"UI Failure",
							JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			vm.add(mi = new MenuItem("Java Look and Feel"));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent jlfe) {
					try {
						UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.metal.MetalLookAndFeel");
						SwingUtilities.updateComponentTreeUI(frm);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
							"setLookAndFeel didn't work: " + e,
							"UI Failure",
							JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		mb.add(vm);

		// The Options Menu...
		om = new Menu("Options");
			cb = new CheckboxMenuItem("AutoSave");
			cb.setState(true);
			om.add(cb);
		mb.add(om);

		// The Help Menu...
		hm = new Menu("Help");
			hm.add(mi = new MenuItem("About"));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frm, 
		"TestEdit - an Exam Question Editor\n" +
		"Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
		"Information available from http://www.darwinsys.com/testedit"
					);
				}
			});
			hm.add(mi = new MenuItem("Topics"));
			mi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					Window jh = new MyHelp();
					centre(jh);
					jh.setVisible(true);
				}
			});
		mb.setHelpMenu(hm);
		mb.add(hm);
	}
}
