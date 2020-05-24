package application.business.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of="idModel")
@Getter @Setter

@Entity
@Table(name="rccModel")
public class Model {
	
	@Id
	@Column(length=100)
	private String idModel;    // model name
	
	private double dailyRate;
	private double tankVolume;
	
	@OneToMany(mappedBy="model")
	private Set<Car> cars = new HashSet<>();

	public Model(String idModel, double dailyRate, double tankVolume) {
		super();
		this.idModel = idModel;
		this.dailyRate = dailyRate;
		this.tankVolume = tankVolume;
	}
}
