package de.danielweidle.spring_sandbox.pesistence;


import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name = "production_order")
@Getter
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "productionLine", type = "string")})
@Filter(name = "tenantFilter", condition = "production_line = :productionLine")
public class ProductionOrder extends BaseEntity {

    public static final String TENANT_FILTER_NAME = "ProductionOrder.tenantFilter";

    @Builder
    public ProductionOrder(long id, String orderNumber, String tenant) {
        super(tenant);
        this.id = id;
        this.orderNumber = orderNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orderNumber;

    public ProductionOrder() {
        super(null);
    }
}
