package pengliu.me.hibernate_relation.one_to_many.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "stock_daily_record", schema = "hibernate_test", catalog = "")
public class StockDailyRecordEntity
{
    private Integer dailyRecordId;
    private String priceOpen;
    private String priceClose;
    private String priceChange;
    private String volume;
    private Timestamp date;
    private StockEntity stock;

    @Id
    @Column(name = "DAILY_RECORD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getDailyRecordId()
    {
        return dailyRecordId;
    }

    public void setDailyRecordId(Integer dailyRecordId)
    {
        this.dailyRecordId = dailyRecordId;
    }

    @Basic
    @Column(name = "PRICE_OPEN")
    public String getPriceOpen()
    {
        return priceOpen;
    }

    public void setPriceOpen(String priceOpen)
    {
        this.priceOpen = priceOpen;
    }

    @Basic
    @Column(name = "PRICE_CLOSE")
    public String getPriceClose()
    {
        return priceClose;
    }

    public void setPriceClose(String priceClose)
    {
        this.priceClose = priceClose;
    }

    @Basic
    @Column(name = "PRICE_CHANGE")
    public String getPriceChange()
    {
        return priceChange;
    }

    public void setPriceChange(String priceChange)
    {
        this.priceChange = priceChange;
    }

    @Basic
    @Column(name = "VOLUME")
    public String getVolume()
    {
        return volume;
    }

    public void setVolume(String volume)
    {
        this.volume = volume;
    }

    @Basic
    @Column(name = "DATE")
    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    public StockEntity getStock()
    {
        return stock;
    }

    public void setStock(StockEntity stock)
    {
        this.stock = stock;
    }
}
