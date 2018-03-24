package entity;

import util.FormatUtil;

import java.math.BigDecimal;
import java.util.Objects;

public class Lender implements Comparable<Lender>{

    private String lender;
    private BigDecimal rate;
    private Integer available;

    public Lender() {
    }

    public Lender(String lender, BigDecimal rate, Integer available) {
        this.lender = lender;
        this.rate = rate;
        this.available = available;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lender lender1 = (Lender) o;
        return Objects.equals(lender, lender1.lender) &&
                Objects.equals(rate, lender1.rate) &&
                Objects.equals(available, lender1.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lender, rate, available);
    }

    public static Lender of(String name, String rate, String avail){
        BigDecimal rateBD = FormatUtil.toBigDecimal(rate);
        Integer availInt = FormatUtil.toInteger(avail);
        return new Lender(name, rateBD, availInt);
    }

    @Override
    public int compareTo(Lender o) {
        return this.getRate().compareTo(o.getRate());
    }
}
