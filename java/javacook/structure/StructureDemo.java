import java.util.*;

/** Dummy source of Objects, for structure demos.
 * @author Ian Darwin
 * @version $Id: StructureDemo.java,v 1.2 1999/08/07 23:48:05 ian Exp $
 */
public class StructureDemo {

	/** The max number of Objects to return */
	int MAX;

	/** Construct a StructureDemo */
	StructureDemo(int m) {
		MAX = m;
	}

	int n;


	/* Dummy method to return a sequence of 21 Calendar references,
	 * so the array should be sized >= 21.
	 */
	public Object getDate() {
		if (n++ > MAX)
			return null;
		return Calendar.getInstance();
	}
}
