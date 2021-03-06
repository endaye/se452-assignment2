package item;

public class CatalogItem {
    private String name;
    private double price;
    private String image;
    private String retailer;
    private String condition;
    private double discount;

    public CatalogItem(String name, double price, String image, String retailer,String condition,double discount){
        setName(name);
        setPrice(price);
        setImage(image);
        setRetailer(retailer);
        setCondition(condition);
        setDiscount(discount);
    }

    public CatalogItem() {

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
        out += getCondition() + ", ";
        out += getDiscount() + ", ";
        out += getRetailer();
        return out;
    }
}
