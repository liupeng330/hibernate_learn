package pengliu.me.hibernate_relation.one_to_many.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pengliu.me.hibernate_relation.HibernateUtil;
import pengliu.me.hibernate_relation.domain.StockDailyRecordEntity;
import pengliu.me.hibernate_relation.domain.StockEntity;

import java.util.List;

public class main
{
    public static void main(String[] args) throws Exception
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();

//            StockEntity stockEntity = new StockEntity();
//            stockEntity.setStockCode("123");
//            stockEntity.setStockName("liupeng");
//            session.save(stockEntity);
//
//            StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
//            stockDailyRecordEntity.setDate(new Timestamp(new Date().getTime()));
//            stockDailyRecordEntity.setPriceChange("34.3");
//            stockDailyRecordEntity.setPriceOpen("ahah");
//            stockDailyRecordEntity.setPriceClose("close");
//            stockDailyRecordEntity.setStock(stockEntity);
//            stockDailyRecordEntity.setVolume("volume");
//            session.save(stockDailyRecordEntity);

            //一对多关系，从一的一边获取多个对应实体
            List ret = session.createQuery("from StockEntity where id = ?").setParameter(0, 6).list();

            for(StockDailyRecordEntity i: ((StockEntity)ret.get(0)).getStockDailyRecordEntities())
            {
                System.out.println(i.getDailyRecordId());
            }


            //多对一关系，从多的一边获取一个对应实体
            ret = session.createQuery("from StockDailyRecordEntity where id = ?").setParameter(0, 3).list();
            System.out.println(((StockDailyRecordEntity)ret.get(0)).getStock().getStockName());


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
