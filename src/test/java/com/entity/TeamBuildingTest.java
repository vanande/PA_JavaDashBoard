package com.entity;

import com.entity.TeamBuilding;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamBuildingTest {

    @Test
    void getTotalPriceTest() {
        TeamBuilding tb = new TeamBuilding(2);
        System.out.println(tb.getTotalPrice());
        System.out.println(tb.getActivities());
        for (Activite a : tb.getActivities()){
            System.out.println(a.getNom());
            for (Option o : a.getOptions()){
                System.out.println(o.getNom());
            }
        }
    }
}
