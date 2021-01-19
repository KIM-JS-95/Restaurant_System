package com.reservationsystem.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {


    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String address;

//    private String regionName;
//    private String categoryName;
//    private String tagNames;

    @Transient
    private List<MenuItem> menuItems;

    public String getInformation(){
        return name + " in " + address;
    }


    public void setMenuItems(List<MenuItem> menuItems){
        this.menuItems = new ArrayList<>(menuItems);

    }

    public void updatedInformation(String name, String address) {
        this.name=name;
        this.address = address;
    }
}
