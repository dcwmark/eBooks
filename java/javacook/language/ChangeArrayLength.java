/**
 * Can you change the .length of an array?
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: ChangeArrayLength.java,v 1.3 2000/11/25 17:54:09 ian Exp $
 */
public class ChangeArrayLength {
	public static void main(String[] argv) {
		//+
		int[] a = new int[4];
		System.out.println(a.length);
		a.length = 5;
		//-
	}
}
