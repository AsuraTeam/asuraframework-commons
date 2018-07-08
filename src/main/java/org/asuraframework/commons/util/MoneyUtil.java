/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.util;

import org.asuraframework.commons.math.BigDecimalUtil;
import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p></p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 * @Date 2018/7/7 下午11:08
 */
public class MoneyUtil {

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private BigDecimal originMoney = new BigDecimal("0");

        public Builder with(double money){
            originMoney = new BigDecimal(Double.toString(money));
            return this;
        }

        public Builder with(long money){
            originMoney = new BigDecimal(Long.toString(money));
            return this;
        }

        public Builder with(int money){
            originMoney = new BigDecimal(Integer.toString(money));
            return this;
        }

        public Builder add(double money){
            BigDecimal addMoney = new BigDecimal(Double.toString(money));
            originMoney = originMoney.add(addMoney);
            return this;
        }
        public Builder add(long money){
            BigDecimal addMoney = new BigDecimal(Long.toString(money));
            originMoney = originMoney.add(addMoney);
            return this;
        }
        public Builder add(int money){
            BigDecimal addMoney = new BigDecimal(Integer.toString(money));
            originMoney = originMoney.add(addMoney);
            return this;
        }

        public Builder subtract(double money){
            BigDecimal addMoney = new BigDecimal(Double.toString(money));
            originMoney = originMoney.subtract(addMoney);
            return this;
        }
        public Builder subtract(long money){
            BigDecimal addMoney = new BigDecimal(Long.toString(money));
            originMoney = originMoney.subtract(addMoney);
            return this;
        }
        public Builder subtract(int money){
            BigDecimal addMoney = new BigDecimal(Integer.toString(money));
            originMoney = originMoney.subtract(addMoney);
            return this;
        }

        public Builder multiply(double money){
            BigDecimal addMoney = new BigDecimal(Double.toString(money));
            originMoney = originMoney.multiply(addMoney);
            return this;
        }
        public Builder multiply(long money){
            BigDecimal addMoney = new BigDecimal(Long.toString(money));
            originMoney = originMoney.multiply(addMoney);
            return this;
        }
        public Builder multiply(int money){
            BigDecimal addMoney = new BigDecimal(Integer.toString(money));
            originMoney = originMoney.multiply(addMoney);
            return this;
        }
        public Builder divide(double money,int scale){
            BigDecimal addMoney = new BigDecimal(Double.toString(money));
            originMoney = originMoney.divide(addMoney,scale, RoundingMode.HALF_UP);
            return this;
        }
        public Builder divide(long money,int scale){
            BigDecimal addMoney = new BigDecimal(Long.toString(money));
            originMoney = originMoney.divide(addMoney,scale, RoundingMode.HALF_UP);
            return this;
        }
        public Builder divide(int money,int scale){
            BigDecimal addMoney = new BigDecimal(Integer.toString(money));
            originMoney = originMoney.divide(addMoney,scale, RoundingMode.HALF_UP);
            return this;
        }

        public Builder round(int scale){
            BigDecimal one = new BigDecimal("1");
            originMoney = originMoney.divide(one, scale, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Double get(){
            return originMoney.doubleValue();
        }

        public String formatWithPrefix(){
            return format("¥%s");
        }
        public String formatWithsuffix(){
            return String.format("%s元");
        }

        public String format(String pattern){
            return String.format(pattern,Double.valueOf(originMoney.doubleValue()));
        }
    }
}
