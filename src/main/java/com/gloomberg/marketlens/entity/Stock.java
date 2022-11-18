package com.gloomberg.marketlens.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gloomberg.marketlens.entity.id.StockId;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(StockId.class)
@SqlResultSetMapping(
        name="growthResultMapping",
        classes={
                @ConstructorResult(
                        targetClass= GrowthResult.class,
                        columns={
                                @ColumnResult(name = "SYMBOL", type = String.class),
                                @ColumnResult(name="YEAR", type = Integer.class),
                                @ColumnResult(name="SUB_YEAR", type = Integer.class),
                                @ColumnResult(name="VALUE", type = Double.class),
                                @ColumnResult(name="PERCENT", type = Double.class)
                        }
                )
        }
)
public class Stock {

    @Id
    @JsonProperty("symbol")
    String symbol;

    @Id
    @JsonProperty("day")
    @JsonFormat(pattern = "yyyy-MMM-dd")
    Date day;

    @JsonProperty
    Double open;

    @JsonProperty
    Double close;

    @JsonProperty
    Double low;

    @JsonProperty
    Double high;
}
