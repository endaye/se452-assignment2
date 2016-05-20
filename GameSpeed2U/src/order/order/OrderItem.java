package order.order;

public class OrderItem {
	private String name;
	private double price;
	private String image;
	private String retailer;

	public OrderItem(String name, double price, String image, String retailer){
		setName(name);
        setPrice(price);
        setImage(image);
        setRetailer(retailer);
	}

    public OrderItem(OrderItem oi) {
        setName(oi.getName());
        setPrice(oi.getPrice());
        setImage(oi.getImage());
        setRetailer(oi.getRetailer());
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


}