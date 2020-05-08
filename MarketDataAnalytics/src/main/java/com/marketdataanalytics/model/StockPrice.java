package com.marketdataanalytics.model;

import com.marketdataanalytics.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPrice extends BaseEntity {
    private String dataProvider;
    private LocalDateTime dateTime;
    private String stockCode;
    private BigDecimal oldPrice;
    private BigDecimal price;
}
