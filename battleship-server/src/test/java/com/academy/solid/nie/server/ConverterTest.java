package com.academy.solid.nie.server;

import org.assertj.core.util.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {
    @DataProvider
    private static Object[][] shipStings() {
        return new Object[][]{
                {"0,5;|", Lists.newArrayList("0,5;")},
                {"1,3;2,3;3,3;4,3;|", Lists.newArrayList("1,3;", "2,3;", "3,3;", "4,3;")},
                {"4,5;5,5;6,5;|", Lists.newArrayList("4,5;", "5,5;", "6,5;")}
        };
    }

    @Test(groups = {"unit"}, dataProvider = "shipStings")
    public void converterShouldConvertStringToAllShips(String shipString, ArrayList<String> expectedList) {
        //when
        List<List<String>> lists = Converter.convert(shipString);

        //then
        assertThat(lists).containsOnly(expectedList);
    }
}