package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.sql.Date.*;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "select * from Card c where c.expiry_Date >:expiryDate", nativeQuery = true)
    List<Card> getCardsGreaterThanGivenExpiryDate(Date expiryDate);

    Card findByCardNum(String cardNum);


//    @Query(value = "select  * from Card c where c.expiry_Date > :'2025-01-01'", nativeQuery = true)
    @Query(value = "SELECT * FROM Card c WHERE c.expiry_Date >= '2025-01-01'", nativeQuery = true)
    List<Card> getMasterCardsWithExpiryDateGreaterThanJan1st2k25();
}
