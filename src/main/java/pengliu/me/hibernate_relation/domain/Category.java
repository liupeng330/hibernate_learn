package pengliu.me.hibernate_relation.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by peng on 16-4-7.
 */
@Entity
public class Category
{
    private Integer categoryId;
    private String name;
    private String desc;
    private Set<StockEntity> stocks = new HashSet<StockEntity>();

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 10)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "[DESC]", length = 255)
    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    public Set<StockEntity> getStocks()
    {
        return stocks;
    }

    public void setStocks(Set<StockEntity> stocks)
    {
        this.stocks = stocks;
    }
}
