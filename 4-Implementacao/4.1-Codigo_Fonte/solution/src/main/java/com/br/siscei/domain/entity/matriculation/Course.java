/**
 * 
 */
package com.br.siscei.domain.entity.matriculation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author felip
 *
 */

@Entity
@Audited
@Table(name = "\"course\"")
@DataTransferObject(javascript = "Course")
public class Course extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4665898735804282347L;
	/**
	 * 
	 */

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 60)
	private String name;
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 144 )
	private String description;
	/**
	 * 
	 */
	@OneToMany(mappedBy = "course", cascade = {CascadeType.ALL}, fetch=FetchType.EAGER, orphanRemoval = true)
	private Set<Discipline> discipline = new HashSet<Discipline>();
	/**
	 * 
	 */
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Course()
	{
	}
	/**
	 * 
	 */
	public Course(Long id, String name, String description )
	{
		super(id);
		this.name 			= name;
		this.description 	= description;
	}
	
	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
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
		Course other = ( Course ) obj;
		if ( description == null )
		{
			if ( other.description != null ) return false;
		}
		else if ( !description.equals( other.description ) ) return false;
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		return true;
	}	
	/**
	 * 
	 * @return
	 */
	public Course addList()
	{
		if(this.getDiscipline()!= null)
		{
			for( Discipline discipline : this.getDiscipline() )
			{
				discipline.setCourse( this );
			}
		}
		return this;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription( String description )
	{
		this.description = description;
	}
	/**
	 * @return the discipline
	 */
	public Set<Discipline> getDiscipline()
	{
		return discipline;
	}
	/**
	 * @param discipline the discipline to set
	 */
	public void setDiscipline( Set<Discipline> discipline )
	{
		this.discipline = discipline;
	}
}
