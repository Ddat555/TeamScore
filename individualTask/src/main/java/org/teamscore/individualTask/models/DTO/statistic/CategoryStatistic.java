package org.teamscore.individualTask.models.DTO.statistic;

import java.math.BigDecimal;

public class CategoryStatistic extends AbstractStatistic {
    private final float percentage;
    private final String color;


    public CategoryStatistic(String name, BigDecimal totalSum, float percentage, String color) {
        super(name, totalSum);
        this.percentage = percentage;
        this.color = color;
    }

    public float getPercentage() {
        return percentage;
    }

    public String getColor() {
        return color;
    }
}
