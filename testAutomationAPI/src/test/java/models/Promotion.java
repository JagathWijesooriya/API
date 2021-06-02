package models;

public class Promotion {
private String Id;
private String Name;
private String Description;
private double Price;
private int MinimumPhotoCount;
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
public double getPrice() {
	return Price;
}
public void setPrice(double price) {
	Price = price;
}
public int getMinimumPhotoCount() {
	return MinimumPhotoCount;
}
public void setMinimumPhotoCount(int minimumPhotoCount) {
	MinimumPhotoCount = minimumPhotoCount;
}
	
	
}
