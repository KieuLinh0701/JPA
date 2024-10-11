package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
@NamedQuery(name = "Role.findAll", query = "SELECT c FROM Role c")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId")
	private int roleId;

	@Column(name = "roleName", columnDefinition = "NVARCHAR(255) NULL")
	private String roleName;
	
	// bi-directional many-to-one association to Video
	@OneToMany(mappedBy = "role")


	private List<User> users;

	public Role() {
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);
		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);
		return user;
	}
}
