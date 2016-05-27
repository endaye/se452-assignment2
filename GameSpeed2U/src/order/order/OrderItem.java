package order.order;

public class OrderItem {
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;

	public OrderItem(String id, String name, double price, String image, String retailer){
		setId(id);
		setName(name);
        setPrice(price);
        setImage(image);
        setRetailer(retailer);
	}

    public OrderItem(OrderItem oi) {
		setId(oi.getId());
        setName(oi.getName());
        setPrice(oi.getPrice());
        setImage(oi.getImage());
        setRetailer(oi.getRetailer());
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
