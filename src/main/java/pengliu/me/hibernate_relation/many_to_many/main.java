package pengliu.me.hibernate_relation.many_to_many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pengliu.me.hibernate_relation.HibernateUtil;
import pengliu.me.hibernate_relation.domain.Category;
import pengliu.me.hibernate_relation.domain.StockDailyRecordEntity;
import pengliu.me.hibernate_relation.domain.StockEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main
{
    public static void main(String[] args) throws Exception
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

            Category category1 = new Category();
            category1.setDesc("haha");
            category1.setName("my name");

            Category category2 = new Category();
            category2.setDesc("2 cat");
            category2.setName("my 2");

            Set<Category> categories = new HashSet<Category>();
            categories.add(category1);
            categories.add(category2);

            StockEntity stock = new StockEntity();
            stock.setCategories(categories);
            stock.setStockName("my new stock");
            stock.setStockCode("my codes");

            session.save(stock);

            StockDailyRecordEntity stockDailyRecordEntity1 = new StockDailyRecordEntity();
            stockDailyRecordEntity1.setDate(new Timestamp(new Date().getTime()));
            stockDailyRecordEntity1.setPriceChange("12334.3");
            stockDailyRecordEntity1.setPriceOpen("123ahah");
            stockDailyRecordEntity1.setPriceClose("123close");
            stockDailyRecordEntity1.setVolume("123volume");
            stockDailyRecordEntity1.setStock(stock);
            session.save(stockDailyRecordEntity1);

            StockDailyRecordEntity stockDailyRecordEntity2 = new StockDailyRecordEntity();
            stockDailyRecordEntity2.setDate(new Timestamp(new Date().getTime()));
            stockDailyRecordEntity2.setPriceChange("a12334.3");
            stockDailyRecordEntity2.setPriceOpen("a123ahah");
            stockDailyRecordEntity2.setPriceClose("a123close");
            stockDailyRecordEntity2.setVolume("a123volume");
            stockDailyRecordEntity2.setStock(stock);
            session.save(stockDailyRecordEntity2);


            tx.commit();
        }
        catch (Exception e)
        {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally
        {
            session.close();
        }
    }
}
