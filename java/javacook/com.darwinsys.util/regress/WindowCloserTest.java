import com.darwinsys.util.*;

import java.awt.*;
import java.awt.event.*;

/* Show an example of closing a Window.
 * @author Ian Darwin
 * @version $Id: WindowCloserTest.java,v 1.7 2001/05/28 03:19:30 ian Exp $
 */
public class WindowCloserTest {

	/* Main method */
	public static void main(String[] argv) {
		Frame f = new Frame("Close Me");
		f.add(new Label("Try Titlebar Close", Label.CENTER));
		f.setSize(100, 100);
		f.setVisible(true);
		f.addWindowListener(new WindowCloser(f, true));
	}
}
