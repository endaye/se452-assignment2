package user;

/**
 1. ProductModelName: XBOX 360
 2. ProductCategory: Gaming Console
 3. ProductPrice: $499
 4. RetailerName: GameSpeed
 5. RetailerZip: 60616
 6. RetailerCity: Chicago
 7. RetailerState: IL
 8. ProductOnSale: Yes
 9. ManufacturerName: Microsoft
 10. ManufacturerRebate: Yes
 11. UserID: whksa8
 12. UserAge: 24
 13. UserGender: Male
 14. UserOccupation: accountant
 15. ReviewRating: 4
 16. ReviewDate: 12/15/2013
 17. ReviewText: It has excellent clarity and motion refresh rate for Batman Game, however,
 it overheats after 2 hours of use
 */
public class ReviewForm {
    private String productId;
    private String productName;
    private String productType;
    private double productPrice;
    private String retailerName;
    private String retailerZip;
    private String retailerCity;
    private String retailerState;
    private boolean isOnSale;
    private String manufacturerName;
    private boolean isManufacturerRebate;
    private String userID;
    private int userAge;
    private String userGender;
    private String userOccupation;
    private int reviewRating;
    private String reviewDate;
    private String reviewText;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getRetailerZip() {
        return retailerZip;
    }

    public void setRetailerZip(String retailerZip) {
        this.retailerZip = retailerZip;
    }

    public String getRetailerCity() {
        return retailerCity;
    }

    public void setRetailerCity(String retailerCity) {
        this.retailerCity = retailerCity;
    }

    public String getRetailerState() {
        return retailerState;
    }

    public void setRetailerState(String retailerState) {
        this.retailerState = retailerState;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public boolean isManufacturerRebate() {
        return isManufacturerRebate;
    }

    public void setManufacturerRebate(boolean manufacturerRebate) {
        isManufacturerRebate = manufacturerRebate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
