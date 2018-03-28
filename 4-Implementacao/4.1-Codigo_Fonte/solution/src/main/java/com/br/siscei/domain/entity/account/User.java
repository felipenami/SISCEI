package com.br.siscei.domain.entity.account;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.siscei.domain.entity.address.Address;
import com.br.siscei.domain.entity.matriculation.Responsible;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * 
 * @since 02/06/2014
 * @version 1.0
 * @category
 */
@Entity
@Audited
@Table(name = "\"user\"")
@DataTransferObject(javascript = "User")
public class User extends AbstractEntity implements Serializable, UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4052986759552589018L;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	// Basic
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String name;
	/**
	 * 
	 */
	@Email
	@NotNull
	@Column(nullable = false, length = 144, unique = true)
	private String email;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean enabled;
	/**
	 * 
	 */
	@NotBlank
	@Length(min = 8)
	@Column(nullable = false, length = 100) 
	private String password;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastLogin;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private Address address;
	/**
	 * 
	 */
	@Column(nullable = true)
	private String motherName;
	/**
	 * 
	 */
	@Column(nullable = true)
	private String fatherName;
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private Responsible responsible;
	/**
	 * 
	 */
	@Column(nullable = true, length = 19 )
	private String phone;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Calendar birthDate;
	/**
	 * 
	 */
	@Column(nullable = true, length = 16)
	private String cpf;
	/**
	 * 
	 */
	@Column(nullable = true, length = 25)
	private String rg;
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public User()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public User( Long id )
	{
		super( id );
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param enabled
	 */
	public User( Long id, String name, String email, Boolean enabled )
	{
		super( id );
		this.email 			= email;
		this.name 			= name;
		this.enabled 		= enabled;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param enabled
	 * @param role
	 */
	public User( Long id, String name, String email, Boolean enabled, UserRole role )
	{
		super( id );
		this.email 			= email;
		this.name 			= name;
		this.enabled 		= enabled;
		this.role 			= role;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param enabled
	 * @param role
	 * @param password
	 */
	public User( Long id, String name, String email, Boolean enabled, UserRole role, String password )
	{
		super( id );
		this.email 			= email;
		this.name 			= name;
		this.enabled 		= enabled;
		this.password 		= password;
		this.role 			= role;
	}
	
	public User( Long id, String name, String email, Boolean enabled, UserRole role, String password, 
			Address address, String motherName, String fatherName, Responsible responsible, String phone, Calendar birthDate  )
	{
		super( id );
		this.email 			= email;
		this.name 			= name;
		this.enabled 		= enabled;
		this.password 		= password;
		this.role 			= role;
		this.address 		= address;
		this.motherName 	= motherName;
		this.fatherName		= fatherName;
		this.responsible	= responsible;
		this.phone 			= phone;
		this.birthDate 		= birthDate;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( role == null )
		{
			return null;
		}
		
		authorities.add( role );

		if ( role.equals( UserRole.ADMINISTRATOR ) )
		{
			authorities.add( UserRole.SECRETARY );
			authorities.add( UserRole.TEACHER );
		}

		authorities.add( UserRole.STUDENT );

		return authorities;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isEnabled()
	{
		return this.enabled == null ? false : this.enabled;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( address == null ) ? 0 : address.hashCode() );
		result = prime * result + ( ( birthDate == null ) ? 0 : birthDate.hashCode() );
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( enabled == null ) ? 0 : enabled.hashCode() );
		result = prime * result + ( ( fatherName == null ) ? 0 : fatherName.hashCode() );
		result = prime * result + ( ( lastLogin == null ) ? 0 : lastLogin.hashCode() );
		result = prime * result + ( ( motherName == null ) ? 0 : motherName.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( password == null ) ? 0 : password.hashCode() );
		result = prime * result + ( ( phone == null ) ? 0 : phone.hashCode() );
		result = prime * result + ( ( responsible == null ) ? 0 : responsible.hashCode() );
		result = prime * result + ( ( rg == null ) ? 0 : rg.hashCode() );
		result = prime * result + ( ( role == null ) ? 0 : role.hashCode() );
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		User other = ( User ) obj;
		if ( address == null )
		{
			if ( other.address != null ) return false;
		}
		else if ( !address.equals( other.address ) ) return false;
		if ( birthDate == null )
		{
			if ( other.birthDate != null ) return false;
		}
		else if ( !birthDate.equals( other.birthDate ) ) return false;
		if ( cpf == null )
		{
			if ( other.cpf != null ) return false;
		}
		else if ( !cpf.equals( other.cpf ) ) return false;
		if ( email == null )
		{
			if ( other.email != null ) return false;
		}
		else if ( !email.equals( other.email ) ) return false;
		if ( enabled == null )
		{
			if ( other.enabled != null ) return false;
		}
		else if ( !enabled.equals( other.enabled ) ) return false;
		if ( fatherName == null )
		{
			if ( other.fatherName != null ) return false;
		}
		else if ( !fatherName.equals( other.fatherName ) ) return false;
		if ( lastLogin == null )
		{
			if ( other.lastLogin != null ) return false;
		}
		else if ( !lastLogin.equals( other.lastLogin ) ) return false;
		if ( motherName == null )
		{
			if ( other.motherName != null ) return false;
		}
		else if ( !motherName.equals( other.motherName ) ) return false;
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		if ( password == null )
		{
			if ( other.password != null ) return false;
		}
		else if ( !password.equals( other.password ) ) return false;
		if ( phone == null )
		{
			if ( other.phone != null ) return false;
		}
		else if ( !phone.equals( other.phone ) ) return false;
		if ( responsible == null )
		{
			if ( other.responsible != null ) return false;
		}
		else if ( !responsible.equals( other.responsible ) ) return false;
		if ( rg == null )
		{
			if ( other.rg != null ) return false;
		}
		else if ( !rg.equals( other.rg ) ) return false;
		if ( role != other.role ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	@Transient
	public String getPassword()
	{
		return this.password;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	@Transient
	public String getUsername()
	{
		return this.email;
	}
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail( String email )
	{
		this.email = email;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled( Boolean enabled )
	{
		this.enabled = enabled;
	}

	/**
	 * @return the role
	 */
	public UserRole getRole()
	{
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole( UserRole userRole )
	{
		this.role = userRole;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword( String password )
	{
		this.password = password;
	}

	/**
	 * @return the lastLogin
	 */
	public Calendar getLastLogin()
	{
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin( Calendar lastLogin )
	{
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the address
	 */
	public Address getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress( Address address )
	{
		this.address = address;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName()
	{
		return motherName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName( String motherName )
	{
		this.motherName = motherName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName()
	{
		return fatherName;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName( String fatherName )
	{
		this.fatherName = fatherName;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled()
	{
		return enabled;
	}

	/**
	 * @return the responsible
	 */
	public Responsible getResponsible()
	{
		return responsible;
	}

	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible( Responsible responsible )
	{
		this.responsible = responsible;
	}

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone( String phone )
	{
		this.phone = phone;
	}

	/**
	 * @return the birthDate
	 */
	public Calendar getBirthDate()
	{
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate( Calendar birthDate )
	{
		this.birthDate = birthDate;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf()
	{
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf( String cpf )
	{
		this.cpf = cpf;
	}

	/**
	 * @return the rg
	 */
	public String getRg()
	{
		return rg;
	}

	/**
	 * @param rg the rg to set
	 */
	public void setRg( String rg )
	{
		this.rg = rg;
	}

	
	
}