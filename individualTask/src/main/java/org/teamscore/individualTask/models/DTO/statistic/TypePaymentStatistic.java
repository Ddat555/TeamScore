package org.teamscore.individualTask.models.DTO.statistic;

import java.math.BigDecimal;

public class TypePaymentStatistic extends AbstractStatistic {
    private final float percentage;

    public TypePaymentStatistic(String name, BigDecimal totalSum, float percentage) {
        super(name, totalSum);
        this.percentage = percentage;
    }

    public float getPercentage() {
        return percentage;
    }
}
