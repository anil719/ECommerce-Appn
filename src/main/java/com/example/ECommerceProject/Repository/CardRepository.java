package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(value = "select * from Card c where c.expiry_Date > :expiryDate", nativeQuery = true)
    List<Card> getCardsGreaterThanGivenExpiryDate(Date expiryDate);

    Card findByCardNum(String cardNum);
}
