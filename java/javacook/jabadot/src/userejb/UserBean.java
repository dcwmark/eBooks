package jabadot;

import javax.ejb.*;
import java.util.Date;
import java.io.*;

/** Implementation of Entity EJB (2.0) that represents one valid user.
 * JabaDot runs its own login service to allow it to have
 * full control over such things, instead of delegating
 * to the J2EE container.
 * @version $Id: UserBean.java,v 1.2 2001/12/25 20:43:19 ian Exp $
 */
public abstract class UserBean implements EntityBean {

	// CMP Database fields.
	public abstract String getName();
	public abstract void setName(String nick);

	public abstract String getPassword();
	public abstract void setPassword(String arg);

	public abstract String getFullName();
	public abstract void setFullName(String arg);

	public abstract String getEmail();
	public abstract void setEmail(String arg);

	public abstract String getCity();
	public abstract void setCity(String arg);

	public abstract String getProvince();
	public abstract void setProvince(String arg);

	public abstract String getCountry();
	public abstract void setCountry(String arg);

	public abstract Date getCreationDate();
	public abstract void setCreationDate(Date arg);

	public abstract Date getLastLoginDate();
	public abstract void setLastLoginDate(Date arg);

	public abstract boolean getEditPrivs();
	public abstract void setEditPrivs(boolean p);

	public abstract boolean getAdminPrivs();
	public abstract void setAdminPrivs(boolean p);

	/** Construct a user with just the name, by delegating to the rest */
	public String ejbCreate(String name) {
		ejbCreate(name, null, null, null, null, null, null);
		return name;
	}
	public void ejbPostCreate(String n) {
	}

	/** Construct a user with all text fields.
	 * After this method completes, the container enters
	 * the fields we've set into the database.
	 */
	public String ejbCreate(String nick, String pw,
		String nam, String em,
		String cy, String pr, String co) {

		setName(nick);
		setPassword(pw);
		setFullName(nam);
		setEmail(em);
		setCity(cy);
		setProvince(pr);
		setCountry(co);
		setCreationDate(new Date());
		setLastLoginDate(null);
		return nick;
	}
	/** Post-create for all-string version.
	 * Nothing to do.
	 */
	public void ejbPostCreate(String nick, String pw,
		String nam, String em,
		String cy, String pr, String co) {
		}


	/** Construct a user with all text fields and privs as an int. */
	public String ejbCreate(String nick, String pw,
		String nam, String em,
		String cy, String pr, String co,
		boolean edit, boolean adm) {

		ejbCreate(nick, pw, nam, em, cy, pr, co);
		setAdminPrivs(adm);
		setEditPrivs(edit);
		return nick;
	}
	public void ejbPostCreate(String nick, String pw,
			String nam, String em,
			String cy, String pr, String co,
		boolean edit, boolean adm) {
	}

	/** Validate a given password against the user's. */
	public boolean checkPassword(String userInput) {

		// Nobody can login if a passwd hasn't been set yet.
		if (getPassword() == null)
			return false;

		// Otherwise just see if they match.
		// XXX TODO use encryption.
		return getPassword().equals(userInput);
	}


	/** Return a String representation. */
	public String toString() {
		return new StringBuffer("UserBean[").append(getName()).append(',').append(getFullName()).append(']').toString();
	}

	// Stuff for the SessionBean interface

	// data item to hold a reference to a passed Entity context
	private EntityContext ctx;

	// save the entity context
	public void setEntityContext(EntityContext x) {
			ctx = x;
	}
	public void unsetEntityContext() {
			ctx = null;
	}

	// set the id value when ejb is activated
	public void ejbActivate() {
	}

	// nullify the ejb field values
	public void ejbPassivate() {
	}

	// other method implementations
	// imposed on us by EntityBean interface

	public void ejbRemove() {}
	public void ejbLoad() {}
	public void ejbStore() {}
	public void ejbPostCreate() {}
}
