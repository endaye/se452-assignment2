package user;

public class User {
	private int id;
	private String name;
	private String password;
	private String usertype;
	private UserPersonalInfo info;

	public User(String name, String password, String usertype) {
		setName(name);
		setPassword(password);
		setUsertype(usertype);
		setInfo(new UserPersonalInfo());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public UserPersonalInfo getInfo() {
		return info;
	}

	public void setInfo(UserPersonalInfo info) {
		this.info = info;
	}
}
