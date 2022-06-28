package com.naces.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "NACE")
public class NaceEntity {

    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private Long order;

    @Column(name = "LEVEL", nullable = false)
    private Integer level;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "PARENT")
    private String parent;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "THIS_ITEM_INCLUDES", nullable = false)
    private String itemInclude;

    @Column(name = "THIS_ITEM_ALSO_INCLUDES")
    private String itemAlsoInclude;

    @Column(name = "RULING")
    private String ruling;

    @Column(name = "THIS_ITEM_EXCLUDES")
    private String itemExclude;

    @Column(name = "REFERENCE_TO_ISIC_REV", nullable = false)
    private String reference;

    @CreationTimestamp
    @Column(name = "CREATED_DATE_TIME")
    private Timestamp createDateTime;

    @CreationTimestamp
    @Column(name = "UPDATED_DATE_TIME")
    private Timestamp updatedDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        NaceEntity entity = (NaceEntity) o;
        return order != null && Objects.equals(order, entity.order);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
