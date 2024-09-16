package com.revature.repositories;

import com.revature.models.Order;
import com.revature.models.QueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query(value = "SELECT PRODUCT_CATEGORY, COUNT(PRODUCT_CATEGORY) FROM ETL.ORDERS GROUP BY PRODUCT_CATEGORY ORDER BY COUNT", nativeQuery = true)
    List<QueryResult> findCategory();

    @Modifying
    @Transactional
    @Query(value = "SELECT PRODUCT_CATEGORY, COUNTRY, COUNT(PRODUCT_CATEGORY) FROM ETL.ORDERS GROUP BY PRODUCT_CATEGORY, COUNTRY ORDER BY COUNT", nativeQuery = true)
    List<QueryResult> findCategoryCountry();

    @Modifying
    @Transactional
    @Query(value = "SELECT PRODUCT_CATEGORY, EXTRACT(MONTH FROM DATETIME) AS MONTH, COUNT(PRODUCT_CATEGORY) FROM ETL.ORDERS GROUP BY PRODUCT_CATEGORY, MONTH ORDER BY MONTH", nativeQuery = true)
    List<QueryResult> findYearPopularity();

    @Modifying
    @Transactional
    @Query(value = "SELECT PRODUCT_CATEGORY, COUNTRY, EXTRACT(MONTH FROM DATETIME) AS MONTH, COUNT(PRODUCT_CATEGORY) FROM ETL.ORDERS GROUP BY PRODUCT_CATEGORY, COUNTRY, MONTH ORDER BY MONTH", nativeQuery = true)
    List<QueryResult> findYearPopularityCountry();

    @Modifying
    @Transactional
    @Query(value = "SELECT COUNTRY, COUNT(COUNTRY) FROM ETL.ORDERS GROUP BY COUNTRY ORDER BY COUNT", nativeQuery = true)
    List<QueryResult> findTopCountry();

    @Modifying
    @Transactional
    @Query(value = "SELECT EXTRACT(HOUR FROM DATETIME) AS HOUR, COUNT(EXTRACT(HOUR FROM DATETIME)) FROM ETL.ORDERS GROUP BY HOUR ORDER BY HOUR", nativeQuery = true)
    List<QueryResult> findBusiestTime();

    @Modifying
    @Transactional
    @Query(value = "SELECT EXTRACT(HOUR FROM DATETIME) AS HOUR, COUNTRY, COUNT(EXTRACT(HOUR FROM DATETIME)) FROM ETL.ORDERS GROUP BY HOUR, COUNTRY ORDER BY HOUR", nativeQuery = true)
    List<QueryResult> findBusiestTimeCountry();
//
//    //List<Order> findAll();
//    List<Order> findPaymentType(String paymentType);
//    List<Order> findProcessFails(String failureReason);
//    List<Order> findCountry(String Country); // getCountry?
//    List<Order> findHighestSales(String price, int count);



    // most common payment type (map?)
        // get all orders
        // expose payment type
        // aggregate by country
    // find all process fails
    // highest sales
        // get all orders
        // expose price
        // add up price per country
}
