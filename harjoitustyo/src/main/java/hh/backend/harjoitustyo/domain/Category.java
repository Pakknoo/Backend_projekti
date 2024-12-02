package hh.backend.harjoitustyo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    private Long categoryId;
    @NotEmpty (message = "Anna kategorian nimi!")
    private String categoryName;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="category")
    @JsonIgnore
    private List<Product> products;

    public Category(){

    }

    public Category( String categoryName) {
        super();
        this.categoryName = categoryName;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
  
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
    }
   
}
