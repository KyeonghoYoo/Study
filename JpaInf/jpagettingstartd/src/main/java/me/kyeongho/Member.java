package me.kyeongho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Member")
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(name = "name")
	private String username;
	
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@Lob
	private String description;
	
	@Embedded
	private Address address;
	
	@ElementCollection
	@CollectionTable(name = "address",
				joinColumns = @JoinColumn(name = "member_id")
			)
	private List<Address> addressHistory = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY) // LAZY를 이용해 프록시로 조회
	@JoinColumn(name = "team_id")
	private Team team;
	
	public Long getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAddressHistory() {
		return addressHistory;
	}

	public void setAddressHistory(List<Address> addressHistory) {
		this.addressHistory = addressHistory;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
