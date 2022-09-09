package by.teachmeskills.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Product extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column
    private String name;
    @Column
    @ToString.Exclude
    private String description;
    @Column
    private int price;
    @Column(name = "image_name")
    @ToString.Exclude
    private String imageName;
}