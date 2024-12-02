package hh.backend.harjoitustyo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long productId;
    @NotEmpty (message = "Anna tuotteen nimi!")
    private String productName;
    @NotNull (message = "Anna tuotteen hinta!")
    @PositiveOrZero(message = "Tuotteen hinta on oltava 0 tai enemmän!")
    private double price;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    
    @ManyToOne
    @JoinColumn(name="manufacturerId")
    private Manufacturer manufacturer;

    public Product (){
    }

    public Product(String productName, double price, Category category, Manufacturer manufacturer) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    
   

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", category="
                + category + ", manufacturer=" + manufacturer    +  /*  ", message=" + message +   /*   ", user=" + user    +*/ "]";
    }
  
}
