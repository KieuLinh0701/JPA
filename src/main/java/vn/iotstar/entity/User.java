package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT v FROM User v")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;

	@Column(name = "username", columnDefinition = "NVARCHAR(50) NULL")
	private String username;

	@Column(name = "password", columnDefinition = "NVARCHAR(50) NULL")
	private String password;

	@Column(name = "fullname", columnDefinition = "NVARCHAR(100) NULL")
	private String fullname;

	@Column(name = "email", columnDefinition = "NVARCHAR(50) NULL")
	private String email;

	@Column(name = "images", columnDefinition = "NVARCHAR(50) NULL")
	private String images;
	
	@Column(name = "phone", columnDefinition = "NVARCHAR(50) NULL")
	private String phone;
	
	@Column(name = "creatDate")
	private LocalDateTime createDate;

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "roleId")

	private Role role;

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
