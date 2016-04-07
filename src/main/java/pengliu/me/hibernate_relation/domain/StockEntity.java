package pengliu.me.hibernate_relation.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock", schema = "hibernate_test", catalog = "")
public class StockEntity
{
    private Integer stockId;
    private String stockCode;
    private String stockName;
    private Set<StockDailyRecordEntity> stockDailyRecordEntities = new HashSet<StockDailyRecordEntity>();
    private Set<Category> categories = new HashSet<Category>();

    @Id
    @Column(name = "STOCK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getStockId()
    {
        return stockId;
    }

    public void setStockId(Integer stockId)
    {
        this.stockId = stockId;
    }

    @Basic
    @Column(name = "STOCK_CODE")
    public String getStockCode()
    {
        return stockCode;
    }

    public void setStockCode(String stockCode)
    {
        this.stockCode = stockCode;
    }

    @Basic
    @Column(name = "STOCK_NAME")
    public String getStockName()
    {
        return stockName;
    }

    public void setStockName(String stockName)
    {
        this.stockName = stockName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set<StockDailyRecordEntity> getStockDailyRecordEntities()
    {
        return stockDailyRecordEntities;
    }

    public void setStockDailyRecordEntities(Set<StockDailyRecordEntity> stockDailyRecordEntities)
    {
        this.stockDailyRecordEntities = stockDailyRecordEntities;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "stock_category", joinColumns = {
            @JoinColumn(name = "STOCK_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false)})
    public Set<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(Set<Category> categories)
    {
        this.categories = categories;
    }
}
