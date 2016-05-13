package item;

import java.util.HashMap;

public class Console extends CatalogItem {

	private HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();

	public Console(String name, double price, String image, String retailer,String condition,double discount, HashMap<String,Accessory> accessories){
		super(name, price, image, retailer, condition, discount);
		this.setAccessories(accessories);
	}
	
	public Console(){
		this.setAccessories(new HashMap<String,Accessory>());
	}

	public HashMap<String,Accessory> getAccessories() {
		return accessories;
	}
	public void setAccessories(HashMap<String,Accessory> accessories) {
		this.accessories = accessories;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + getAccessories();
	}
}
