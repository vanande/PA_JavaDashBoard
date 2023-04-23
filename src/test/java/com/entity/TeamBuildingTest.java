package com.entity;

import com.entity.TeamBuilding;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamBuildingTest {

    @Test
    void getTotalPriceTest() {
        TeamBuilding tb = new TeamBuilding(2);
       assertEquals(8058, tb.getTotalPrice());

    }
}
