package jabadot;

import java.applet.*;
import java.awt.*;
import javax.swing.*;

/** Interim fake ChatRoom client */
public class ChatRoom extends JApplet {

	public void init() {
		setLayout(new BorderLayout());
		add(new JLabel("Sorry, the ChatRoom applet\n" +
			"source code got corrupted one day...\n" +
			"It will be back someday."));
	}
}
