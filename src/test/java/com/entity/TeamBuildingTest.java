package com.entity;

import com.entity.TeamBuilding;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamBuildingTest {

    @Test
    void getTotalPriceTest() {
        TeamBuilding tb = new TeamBuilding(1);
        System.out.println(tb);


        float expectedPrice = 145.5f;
        float totalPrice = tb.getTotalPrice();

        assertEquals(expectedPrice, totalPrice, 0.01f);
    }
}
