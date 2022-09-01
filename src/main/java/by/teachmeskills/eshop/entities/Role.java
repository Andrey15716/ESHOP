package by.teachmeskills.eshop.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> user;
}