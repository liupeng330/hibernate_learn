package pengliu.me.hibernate_relation.one_to_many.test;

import org.hibernate.Session;
import pengliu.me.hibernate_relation.HibernateUtil;
import pengliu.me.hibernate_relation.one_to_many.domain.StockDailyRecordEntity;
import pengliu.me.hibernate_relation.one_to_many.domain.StockEntity;

import java.sql.Timestamp;
import java.util.Date;

public class main
{
    public static void main(String[] args)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StockEntity stockEntity = new StockEntity();
        stockEntity.setStockCode("123");
        stockEntity.setStockName("liupeng");
        session.save(stockEntity);

        StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
        stockDailyRecordEntity.setDate(new Timestamp(new Date().getTime()));
        stockDailyRecordEntity.setPriceChange("34.3");
        stockDailyRecordEntity.setPriceOpen("ahah");
        stockDailyRecordEntity.setPriceClose("close");
        stockDailyRecordEntity.setStock(stockEntity);
        stockDailyRecordEntity.setVolume("volume");
        session.save(stockDailyRecordEntity);

        session.getTransaction().commit();
    }
}
