package model.entity;

public class Category {

	//private fields
	
	
	private int id;
	private String name;
	private String Description;
	//@Column(name = "imageURL")
	private String imageurl;
	//@Column(name = "isActive")
	private boolean active = true;
	
	//active fields
	
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	/*@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", Description=" + Description + ", imageurl=" + imageurl
				+ ", active=" + active + "]";
	}*/

}
