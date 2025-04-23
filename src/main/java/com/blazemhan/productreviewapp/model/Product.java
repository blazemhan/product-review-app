package com.blazemhan.productreviewapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Product description is required")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   private List<Review> reviews = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Product name cannot be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name cannot be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Product description is required") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Product description is required") String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
