package me.kyeongho.catalogservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CATALOG")
@Data
public class CatalogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false, updatable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP") // h2 databaseq에서 현재 날짜를 가져오는 함수를 사용하여 기본값 채움
    private Date createdAt;
}
