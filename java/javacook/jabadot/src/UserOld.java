package jabadot;

import java.util.Date;

/** Represents one logged in user 
 */
public class User implements java.io.Serializable {

	// #name:password:fullName:email:City:Prov:Country:privs
	protected String name;
	protected String password;
	protected String fullName;
	protected String email;
	protected String city;
	protected String prov;
	protected String country;
	protected Date creationDate;
	protected Date lastLoginDate;

	protected boolean editPrivs = false;
	protected boolean adminPrivs = false;

	static final int P_ADMIN = 01000;
	static final int P_EDIT = 0100;

	/** Construct a user with no data -- must be a no-argument
	 * constructor for use in jsp:useBean.
	 */
	public User() {
		creationDate = new Date();
	}

	/** Construct a user with just the name */
	public User(String n) {
		this();			// set credt
		name = n;
	}

	/** Construct a user with all text fields. */
	public User(String nick, String pw, String nam, String em,
		String cy, String pr, String co) {
		this();			// set credt
		name = nick;
		password = pw;
		fullName = nam;
		email = em;
		city = cy;
		prov = pr;
		country = co;
	}

	/** Construct a user with all text fields and privs as an int. */
	public User(String nick, String pw, String nam, String em,
		String cy, String pr, String co, int privs) {
		this(nick, pw, nam, em, cy, pr, co);
		if ((privs & P_ADMIN) != 0)
			adminPrivs = true;
		if ((privs & P_EDIT) != 0)
			editPrivs = true;
	}

	/** Return the nickname. */
	public String getName() {
		return name;
	}

	/** The name should not be changeable, but we
	 * want to be able to say <jsp:setProperty property="*"/>
	 * and get it all...
	 */
	public void setName(String nick) {
		name = nick;
	}

	public String getPassword() {
		return password;
	}

	/** Validate a given password against the user's. */
	public boolean checkPassword(String userInput) {
		return password.equals(userInput);
	}

	/** Set password */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Get email */
	public String getEmail() {
		return email;
	}

	/** Set email */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Get fullName */
	public String getFullName() {
		return fullName;
	}

	/** Set fullName */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/** Get city */
	public String getCity() {
		return city;
	}

	/** Set city */
	public void setCity(String city) {
		this.city = city;
	}

	/** Get prov */
	public String getProv() {
		return prov;
	}

	/** Set prov */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/** Get country */
	public String getCountry() {
		return country;
	}

	/** Set country */
	public void setCountry(String country) {
		this.country = country;
	}

	/** Get adminPrivs */
	public boolean isAdminPrivileged() {
		return adminPrivs;
	}

	/** Set adminPrivs */
	public void setAdminPrivileged(boolean adminPrivs) {
		this.adminPrivs = adminPrivs;
	}

	/** Get EditPrivs */
	public boolean isEditPrivileged() {
		return editPrivs;
	}

	/** Set EditPrivs */
	public void setEditPrivileged(boolean editPrivs) {
		this.editPrivs = editPrivs;
	}

	/** Get all privs, as an int, for use in the database */
	public int getPrivs() {
		int i = 0;
		if (adminPrivs)
			i |= P_ADMIN;
		if (editPrivs)
			i |= P_EDIT;
		return i;
	}

	/** Get the Creation Date (read only field) */
	public Date getCreationDate() {
		return creationDate;
	}

	/** Get the LastLog Date (read only field) */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/** Get the LastLog Date (read only field) */
	public void setLastLoginDate(Date d) {
		lastLoginDate = d;
	}

	/** Return a String representation. */
	public String toString() {
		return new StringBuffer("User[").append(name).append(',').append(fullName).append(']').toString();
	}

	/** Check if all required fields have been set */
	public boolean isComplete() {
		if (name == null || name.length()==0 ||
		    email == null || email.length()==0 ||
		    fullName == null || fullName.length()==0 )
			return false;
		return true;
	}
}
