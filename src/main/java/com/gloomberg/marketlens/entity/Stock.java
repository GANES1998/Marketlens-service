package com.gloomberg.marketlens.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gloomberg.marketlens.dto.contribution.ContributionResult;
import com.gloomberg.marketlens.dto.econinfluence.EconInfluenceResult;
import com.gloomberg.marketlens.dto.news.NewsInfluenceResult;
import com.gloomberg.marketlens.dto.variance.SectorVarianceResult;
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
@SqlResultSetMapping(
        name = "NEWS_INFLUENCE_RESULT_SET_MAPPING",
        classes = {
                @ConstructorResult(
                        targetClass = NewsInfluenceResult.class,
                        columns = {
                                @ColumnResult(name = "SECTOR", type = String.class),
                                @ColumnResult(name="YEAR", type = Integer.class),
                                @ColumnResult(name="SUB_YEAR", type = Integer.class),
                                @ColumnResult(name="VALUE", type = Double.class),
                        }
                )
        }
)
@SqlResultSetMapping(
        name = "CONTRIBUTION_RESULT_SET",
        classes = {
                @ConstructorResult(
                        targetClass = ContributionResult.class,
                        columns = {
                                @ColumnResult(name = "SYMBOL", type = String.class),
                                @ColumnResult(name="YEAR", type = Integer.class),
                                @ColumnResult(name="SUB_YEAR", type = Integer.class),
                                @ColumnResult(name="MARKET_CAP", type = Double.class),
                        }
                )
        }
)
@SqlResultSetMapping(
        name = "ECON_INFLUENCE_RESULT_SET",
        classes = {
                @ConstructorResult(
                        targetClass = EconInfluenceResult.class,
                        columns = {
                                @ColumnResult(name = "TYPE", type = String.class),
                                @ColumnResult(name = "SYMBOL", type = String.class),
                                @ColumnResult(name="YEAR", type = Integer.class),
                                @ColumnResult(name="SUBYEAR", type = Integer.class),
                                @ColumnResult(name="VALUE", type = Double.class),
                        }
                )
        }
)
@SqlResultSetMapping(
        name = "SECTOR_VARIANCE_RESULT_SET",
        classes = {
                @ConstructorResult(
                        targetClass = SectorVarianceResult.class,
                        columns = {
                                @ColumnResult(name = "SYMBOL", type = String.class),
                                @ColumnResult(name = "YEAR", type = Integer.class),
                                @ColumnResult(name="SUBYEAR", type = Integer.class),
                                @ColumnResult(name="VALUE", type = Double.class),
                                @ColumnResult(name="STD_DEV", type = Double.class),
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
