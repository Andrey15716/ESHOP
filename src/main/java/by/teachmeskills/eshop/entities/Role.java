//package by.teachmeskills.eshop.entities;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.List;
//
//@Getter
//@Setter
//@SuperBuilder
//@RequiredArgsConstructor
//@Entity
//@Table(name = "role")
//public class Role extends BaseEntity implements GrantedAuthority {
//    private String name;
//    @OneToMany(mappedBy = "role")
//    private List<User> user;
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }
//}