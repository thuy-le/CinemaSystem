/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

/**
 *
 * @author PC
 */
public class SeatCategory {
    private int categoryID;
    private String categoryName;
    private String imageSrc;
    
    public SeatCategory(final int categoryID){
        this.categoryID = categoryID;
        if(this.categoryID==1){
            this.categoryName = "Standard Seat";
            this.imageSrc = "images/standardSeat.png";
        }
        else if(this.categoryID==2){
            this.categoryName = "VIP Seat";
            this.imageSrc = "images/vipSeat.png";
        }
        else if(this.categoryID==3){
            this.categoryName = "Couple Seat";
            this.imageSrc = "images/coupleSeat.png";
        }
        else if(this.categoryID==4){
            this.categoryName = "Your Seat";
            this.imageSrc = "images/yourSeat.png";
        }
        else if(this.categoryID==5){
            this.categoryName = "Sold Seat";
            this.imageSrc = "images/soldSeat.png";
        }
        else{
            this.categoryName = "";
            this.imageSrc = "";
        }
        
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
    
}
