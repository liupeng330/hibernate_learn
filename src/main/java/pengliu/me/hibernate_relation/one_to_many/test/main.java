package pengliu.me.hibernate_relation.one_to_many.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pengliu.me.hibernate_relation.HibernateUtil;
import pengliu.me.hibernate_relation.domain.StockDailyRecordEntity;
import pengliu.me.hibernate_relation.domain.StockEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class main
{
    public static void main(String[] args) throws Exception
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try
        {
//            tx = session.beginTransaction();
//
//            StockEntity stockEntity = new StockEntity();
//            stockEntity.setStockCode("123");
//            stockEntity.setStockName("liupeng");
//
//            // 在StockDailyRecordEntity的stock属性上设置级联后,
//            // 不用先将stockEntity入库, 在stockdaily入库时,会级联将相关的实体入库
////            session.save(stockEntity);
//
//            StockDailyRecordEntity stockDailyRecordEntity = new StockDailyRecordEntity();
//            stockDailyRecordEntity.setDate(new Timestamp(new Date().getTime()));
//            stockDailyRecordEntity.setPriceChange("34.3");
//            stockDailyRecordEntity.setPriceOpen("ahah");
//            stockDailyRecordEntity.setPriceClose("close");
//            stockDailyRecordEntity.setStock(stockEntity);
//            stockDailyRecordEntity.setVolume("volume");
//            session.save(stockDailyRecordEntity);
//
//            tx.commit();
            tx = session.beginTransaction();

            List ret = session.createQuery("from StockDailyRecordEntity where priceClose = ?").setParameter(0, "close").list();
            if(ret.size() > 0)
            {
                StockDailyRecordEntity stockDaily =  (StockDailyRecordEntity) ret.get(0);
                System.out.println("Getting stock from stockdaily, id is " + stockDaily.getStock().getStockId());
                StockEntity stock = stockDaily.getStock();
                stock.setStockName(stock.getStockName() + " changed!!");

                // 测试级联删除, 现在要删除的是stock,
                // 但是想级联删除stockdailyrecord,
                // 所以要在stock的stockdailyrecordentities上标注级联删除特性
                session.delete(stock);
            }

//            //一对多关系，从一的一边获取多个对应实体
//            List ret = session.createQuery("from StockEntity where id = ?").setParameter(0, 6).list();
//
//            for(StockDailyRecordEntity i: ((StockEntity)ret.get(0)).getStockDailyRecordEntities())
//            {
//                System.out.println(i.getDailyRecordId());
//            }


//            //多对一关系，从多的一边获取一个对应实体
//            ret = session.createQuery("from StockDailyRecordEntity where id = ?").setParameter(0, 3).list();
//            System.out.println(((StockDailyRecordEntity)ret.get(0)).getStock().getStockName());

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
