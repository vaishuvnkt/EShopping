package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//------------------
	@OneToOne//relation between user table and cart table // the name of the attribute created by this relation is objname(user)_primarykeyOfTheUserTable(id) => user_id
	//@JoinColumn(name = "uid") this annotation is used to change the name of the column created by the relation onetoone
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//--------------
	@Column(name = "grand_total")
	private double grandTotal;
	@Column(name = "cart_lines")
	private int cartLines;
	@Column(name = "user_id")

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCartLines() {
		return cartLines;
	}
	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", grandTotal=" + grandTotal + ", cartLines=" + cartLines + "]";
	}
	
}
