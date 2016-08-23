package com.napkinstudio.entity;

import javax.persistence.*;
import java.util.List;
//import com.napkinstudio.entity.Article;

import static javax.persistence.CacheStoreMode.REFRESH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Entity

@NamedQueries({
//        @NamedQuery(name = "User.findAllByFirstName", query = "SELECT u FROM  User  u  WHERE u.firstName =:firstName"),
//        @NamedQuery(name = "User.findAllByLastName", query = "SELECT u FROM  User  u  WHERE u.lastName  =:lastName"),
//        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM  User  u  WHERE u.login   =:login"),
//        @NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.userId = ?1"),
        /*@NamedQuery(name = "User.deactivateById", query = "update User as u set u.enabled =0  where u.userId = ?1"),
        @NamedQuery(name = "User.activateById", query = "update User as u set u.enabled =1  where u.userId = ?1"),*/ })

@Table(name = "articles")
public class Article {

 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer articleId;
    private String itemNum;
    private String debItemNum;
    private String size;
    private String material;
    private String foldingMeth;
    private String napkinCol;
    private String color1;
    private String color2;
    private String color3;
    private String color4;
    private String version;
    private Boolean deleted;
    
    
//    @ManyToMany(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
//    @JoinTable
//    private List<Role> roles;
//
//    public List<Role> getRoles() {
//        return roles;    }
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;    }
    
    
    public String getItemNum() {
        return itemNum;    }   
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;    }

    public String getDebItemNum() {
        return debItemNum;    }   
    public void setDebItemNum(String debItemNum) {
        this.debItemNum = debItemNum;    }

    public String getSize() {
        return size;    }   
    public void setSize(String size) {
        this.size = size;    }

    public String getMaterial() {
        return material;    }   
    public void setMaterial(String material) {
        this.material = material;    }

    public String getFoldingMeth() {
        return foldingMeth;    }   
    public void setFoldingMeth(String foldingMeth) {
        this.foldingMeth = foldingMeth;    }

    public String getNapkinCol() {
        return napkinCol;    }   
    public void setNapkinCol(String napkinCol) {
        this.napkinCol = napkinCol;    }

    public String getColor1() {
        return color1;    }   
    public void setColor1(String color1) {
        this.color1 = color1;    }

    public String getColor2() {
        return color2;    }   
    public void setColor2(String color2) {
        this.color2 = color2;    }

    public String getColor3() {
        return color3;    }   
    public void setColor3(String color3) {
        this.color3 = color3;    }

    public String getColor4() {
        return color4;    }   
    public void setColor4(String color4) {
        this.color4 = color4;    }

    public String getVersion() {
        return version;    }   
    public void setVersion(String version) {
        this.version = version;    }

    public Boolean getDeleted() {
        return deleted;    }
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;    }

}
