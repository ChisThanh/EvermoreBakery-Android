package project.evermorebakery.Model;

public class ModelComment
{
    private String reviewId;
    private String customerId;
    private String customerName;
    private String itemId;
    private String content;
    private int reviewRating;
    private String itemName;

    // Constructor
    public ModelComment(String reviewId, String customerId, String customerName, String itemId, String content, int reviewRating, String itemName) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.itemId = itemId;
        this.content = content;
        this.reviewRating = reviewRating;
        this.itemName = itemName;
    }
    public ModelComment( String customerName, String content, int reviewRating) {
        this.customerName = customerName;
        this.content = content;
        this.reviewRating = reviewRating;
    }

    public ModelComment() {
    }

    // Getters and Setters
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId='" + reviewId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", itemId='" + itemId + '\'' +
                ", content='" + content + '\'' +
                ", reviewRating=" + reviewRating +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
