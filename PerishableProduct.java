/*
Name: Rasul.A
Date: April 3rd, 2026
Description: Perishable Product class for assignment 4
 */

import java.util.Date;
// inherit
public class PerishableProduct extends Product {

    private Date expiryDate;
    // Default constructor
    public PerishableProduct(){
        super();
        this.expiryDate = new Date();
    }
    // Overloaded constructor
    public PerishableProduct(String sku, String productName, double unitCost, double salePrice,
                   int quantityOnHand, int quantityNeeded, String specialInstructions, Date expiryDate){

        super(sku, productName, unitCost, salePrice, quantityOnHand, quantityNeeded, specialInstructions);
        setExpiryDate(expiryDate);
    }
    // getter and setter for expiry date
    public final Date getExpiryDate(){
        return expiryDate;
    }

    public final void setExpiryDate(Date expiryDate){
        this.expiryDate = expiryDate;
    }

    // Overrides the product display to add expiry date at the end
    @Override
    public String toString() {
        return super.toString() + "Expiry Date: " + expiryDate + "\n";
    }

}

