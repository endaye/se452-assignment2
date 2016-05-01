
import java.util.HashMap;

public class Console {
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();

	public Console(String name, double price, String image, String retailer,String condition,double discount, HashMap<String,Accessory> accessories){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.setAccessories(accessories);
	}
	
	public Console(){
		this.setAccessories(new HashMap<String,Accessory>());
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public HashMap<String,Accessory> getAccessories() {
		return accessories;
	}
	public void setAccessories(HashMap<String,Accessory> accessories) {
		this.accessories = accessories;
	}



	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		String out = "";
		out += getName() + ", ";
		out += getPrice() + ", ";
		out += getImage() + ", ";
		out += getRetailer() + ", ";
		out += getCondition() + ", ";
		out += getDiscount() + ", ";
		out += getAccessories();
		return out;
	}
}
