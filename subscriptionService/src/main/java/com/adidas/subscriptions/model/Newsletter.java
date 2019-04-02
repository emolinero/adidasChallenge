package com.adidas.subscriptions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "newsletters")
public class Newsletter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 60)
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public Newsletter setName(String name) {

        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Newsletter setDescription(String description) {

        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Newsletter setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Newsletter that = (Newsletter) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
