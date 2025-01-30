package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name can not be empty")
    private String name;

    // @Size(min=5, max=12)
    // @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).*")

    @NotBlank(message = "password can not be empty")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,12}", message="Password must be 5-12 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

    @Column(unique=true)
    @NotBlank(message = "email can not be empty")
    @Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "insert valid email")
    private String email;

    
    private String imageUrl;
    private String role;
    private boolean enabled;

    @Column(length=500)
    @NotBlank(message = "about can not be empty")
    @Size(min=5,max=200 , message="About must be 5-200 characters long")
    private String about;
    
    @AssertTrue(message = "you must accept the terms and conditions")
    private boolean agreement;

    public boolean isAgreement() {
		return agreement;
	}


	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}


	public User(int id, @NotBlank(message = "Name can not be empty") String name,
			@NotBlank(message = "password can not be empty") @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,12}", message = "Password must be 5-12 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") String password,
			@NotBlank(message = "email can not be empty") @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "insert valid email") String email,
			String imageUrl, String role, boolean enabled,
			@NotBlank(message = "about can not be empty") @Size(min = 5, max = 200, message = "About must be 5-200 characters long") String about,
			@AssertTrue(message = "you must accept the terms and conditions") boolean agreement,
			List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.imageUrl = imageUrl;
		this.role = role;
		this.enabled = enabled;
		this.about = about;
		this.agreement = agreement;
		this.contacts = contacts;
	}


	public User(@AssertTrue(message = "you must accept the terms and conditions") boolean agreement) {
		super();
		this.agreement = agreement;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="user")
    private List<Contact> contacts=new ArrayList<>();//since one user can have list of contacts ,also can take set

    public User() {
        super();
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", imageUrl="
				+ imageUrl + ", role=" + role + ", enabled=" + enabled + ", about=" + about + ", agreement=" + agreement
				+ ", contacts=" + contacts + "]";
	}




}
