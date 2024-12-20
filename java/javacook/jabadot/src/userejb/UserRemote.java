package jabadot;

import javax.ejb.*;
import java.rmi.*;

/** The "User" EJB "remote interface".
 * Most of this bean's methods are the CMP set/get methods.
 */
public interface UserRemote extends EJBObject {
	public abstract boolean checkPassword(String userInput)
		throws RemoteException;
}
