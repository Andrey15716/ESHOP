package by.teachmeskills.eshop.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column
    private String name;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> user;
}