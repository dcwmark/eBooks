import junit.framework.*;
/** some junit test cases for EqualsDemo
 * writing a full set is left as "an exercise for the reader".
 * Run as: $ java junit.textui.TestRunner EqualsDemoTest
 * @version $Id: EqualsDemoTest.java,v 1.1 2001/02/17 18:42:15 ian Exp $
 */
public class EqualsDemoTest extends TestCase {

	/** an object being tested */
	EqualsDemo d1;
	/** another object being tested */
	EqualsDemo d2;

	/** init() method */
	public void setUp() {
		d1 = new EqualsDemo();
		d2 = new EqualsDemo();
	}

	/** constructor plumbing for junit */
	public EqualsDemoTest(String name) {
		super(name);
	}

	public void testSymmetry() { 
		assert(d1.equals(d1));
	}

	public void testSymmetric() {
		assert(d1.equals(d2) && d2.equals(d1));
	}

	public void testCaution() {
		assert(!d1.equals(null));
	}
}
