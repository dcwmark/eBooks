package jabadot;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;

/** The EJB Home Interface for the User Account EJB.
 * @version $Id: UserHome.java,v 1.2 2001/12/25 20:43:20 ian Exp $
 */
public interface UserHome extends EJBHome {

	/** Create a User when you only know their name */
	public UserRemote create(String nick)
		throws RemoteException, CreateException;

	/** Create a User with all String parameters. */
	public UserRemote create(String nick, String pw,
		String name, String email,
		String city, String pr, String co)
		throws RemoteException, CreateException;

	/** Create a User with all String and boolean parameters. */
	public UserRemote create(String nick, String pw,
		String name, String email,
		String city, String pr, String co,
		boolean edit, boolean adm)
		throws RemoteException, CreateException;

	/** Find an EJB by name.
	 * The deployment process will generate this method.
	 */
	public UserRemote findByPrimaryKey (String id)
			throws RemoteException, FinderException;

	/** findAllUsers -- method generated by deployment.
	 * In EJB1, relied on a trick(?) that no "where" clause is generated
	 * in the deploytool-gen'd SQL (discovered by Kim Fowler)
	 * In EJB2 we use EJB QL to make this explicit.
	 * @return a collection of all the rows.
	 */
	public Collection findAllUsers ()
			throws RemoteException, FinderException;
}
