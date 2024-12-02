package hh.backend.harjoitustyo.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long manufacturerId;
    @NotEmpty (message = "Anna valmistajan nimi!")
    private String manufacturerName;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="manufacturer")
    private List<Product> products;

    public Manufacturer(){

    }

    public Manufacturer(String manufacturerName) {
        super();
        this.manufacturerName = manufacturerName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    @Override
    public String toString() {
        return "Manufacturer [manufacturerId=" + manufacturerId + ", manufacturerName=" + manufacturerName + "]";
    }

}       
