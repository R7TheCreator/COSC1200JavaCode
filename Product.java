/*
Name: Rasul
Date: April 3, 2026
Description: Product class for assignment 4
 */

// Attributes
public class Product {
    private String sku;
    private String productName;
    private double unitCost;
    private double salePrice;
    private int quantityOnHand;
    private int quantityNeeded;
    private String specialInstructions;

    // Default constructor with placeholders
    public Product() {
        this.sku = "00000000";
        this.productName = "Unknown Product";
        this.unitCost = 0.0;
        this.salePrice = 0.0;
        this.quantityOnHand = 0;
        this.quantityNeeded = 0;
        this.specialInstructions = "None";
    }

    // Constructor that validates inputs via setters
    public Product(String sku, String productName, double unitCost, double salePrice,
                   int quantityOnHand, int quantityNeeded, String specialInstructions){
        setSku(sku);
        setProductName(productName);
        setUnitCost(unitCost);
        setSalePrice(salePrice);
        setQuantityOnHand(quantityOnHand);
        setQuantityNeeded(quantityNeeded);
        setSpecialInstructions(specialInstructions);
    }

    // Getters and Setters
    public final String getSku(){
        return sku;
    }
    // Validates 8+ digits
    public final void setSku(String sku) {
        if (sku != null && sku.matches("\\d{8,}")) {
            this.sku = sku;
        } else {
            this.sku = "00000000";
        }
    }

    public final String getProductName(){
        return productName;
    }

    // Ensures name isn't empty
    public final void setProductName(String productName) {
        if (productName != null && !productName.trim().isEmpty()) {
            this.productName = productName;
        } else {
            this.productName = "Unknown Product";
        }
    }

    public final double getUnitCost() {
        return unitCost;
    }

    public final void setUnitCost(double unitCost) {
        this.unitCost = Math.max(0, unitCost);
    }

    public final double getSalePrice() {
        return salePrice;
    }

    public final void setSalePrice(double salePrice) {
        this.salePrice = Math.max(0, salePrice);
    }

    public final int getQuantityOnHand() {
        return quantityOnHand;
    }

    public final void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = Math.max(0, quantityOnHand);
    }

    public final int getQuantityNeeded() {
        return quantityNeeded;
    }

    public final void setQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = Math.max(0, quantityNeeded);
    }

    public final String getSpecialInstructions() {
        return specialInstructions;
    }

    public final void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    // Formatted output for product details
    @Override
    public String toString() {
        return "SKU: " + sku + "\n" +
                "Product Name: " + productName + "\n" +
                "Unit Cost: USD " + unitCost + "\n" +
                "Sale Price: USD " + salePrice + "\n" +
                "Quantity on hand: " + quantityOnHand + "\n" +
                "Quantity Needed: " + quantityNeeded + "\n" +
                "Special Instructions: " + specialInstructions + "\n";
    }

}
