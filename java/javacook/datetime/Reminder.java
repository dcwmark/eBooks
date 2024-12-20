import java.util.*;
/**
 * Beep every 5 minutes.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Reminder.java,v 1.2 2000/11/25 17:54:52 ian Exp $
 */
public class Reminder {
	public static void main(String[] argv) throws InterruptedException {
		//+
		while (true) {
			System.out.println(new Date() + "\007");
			Thread.sleep(5*60*1000);
		}
		//-
	}
}
