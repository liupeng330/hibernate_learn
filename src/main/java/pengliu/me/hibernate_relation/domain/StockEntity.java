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

    //MappedBy signals hibernate that the key for the relationship is on the other side.
    //This means that although you link 2 tables together,
    // only 1 of those tables has a foreign key constraint to the other one.
    // MappedBy allows you to still link from the table not containing the constraint to the other table.
    // MappedBy的含义就是，对于1对多关系，一般出现在1的一边，表示通知hibernate：这个1对多关系的外键不在我这个表中
    // 在对面的那个表中实体对象的属性用mappedBy指定的那个。
    // 而当前1这边的实体，也希望通过这种关联关系获取到对面实体的对象集合，就需要像下面这样，使用@OneToMany的mappedBy。
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
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

