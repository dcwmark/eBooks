import java.io.*;
import javax.ejb.*;
import javax.naming.*;
import jabadot.*;
import javax.rmi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** A GUI program to exercise the "User" EJB.
 */
public class EJBUsers {

	/** A "global" EJBHome reference */
	private static jabadot.UserHome uHome;

	public static void main(String[] args) throws Exception {

		// Setup EJB
		InitialContext ctx = new InitialContext();
		Object o = ctx.lookup("jabadot/User");
		uHome = (jabadot.UserHome)PortableRemoteObject.narrow(o,
			jabadot.UserHome.class);

		// Layout GUI
		JFrame jf = new JFrame("JabaDot User Administrator");
		Container cp = jf.getContentPane();
		cp.setLayout(new FlowLayout());
		JButton b;
		cp.add(b = new JButton("Add ian"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				addIan();

			}
		});

		cp.add(b = new JButton("List ian"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				listIan();
		}
	});
			cp.add(b = new JButton("List ALL"));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					listAll();
			}
	});
		cp.add(b = new JButton("Remove ian"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				removeIan();
				}
		});
		cp.add(b = new JButton("EXIT"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				System.exit(0);
			}
		});

		jf.pack();
		jf.setVisible(true);
	}

	private static void addIan() {
		try {
		jabadot.UserInterface ian = uHome.create("ian", "abc",
			"Ian Darwin", "ian@server.ltree",
			"Toronto", "ON", "Canada",
			true, true);
			System.out.println("DONE");
		} catch (Exception ex) {
			diag(ex);
		}
	}
	private static void listIan() {
		try {
		jabadot.UserInterface ian = uHome.findByPrimaryKey("ian");
		System.out.println(ian.getName() + "-->" + ian.getFullName());
		System.out.println("DONE");
	} catch (Exception ex) {
			diag(ex);
		}
	}
	private static void listAll() {
		System.out.println("Starting List:");
		try {
			Collection all = uHome.findAllUsers();
			Iterator it = all.iterator();
			while (it.hasNext()) {
				jabadot.UserInterface aUser =
					(jabadot.UserInterface)it.next();
				System.out.println(aUser.getName() + "-->" + aUser.getFullName());
			}
			System.out.println("End of list.");
	} catch (Exception ex) {
			diag(ex);
		}
	}
	private static void removeIan() {
		try {
		jabadot.UserInterface ian2 = uHome.findByPrimaryKey("ian");
		ian2.remove();
		System.out.println("DONE");
	} catch (Exception ex) {
			diag(ex);
		}
	}
	private static void diag(Exception ex) {
		System.out.println("NOPE! " + ex);
	}
}
