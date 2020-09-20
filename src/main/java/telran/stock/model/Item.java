package telran.stock.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"itemNo"})
@Entity
public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String itemNo;
	String name;
	int amount;
	String inventoryCode;
	
	public void addQuantity(int quantity) {
		amount += quantity;
	}
	
	public void deleteQuantity(int quantity) {
		amount -= quantity;
	}
}
