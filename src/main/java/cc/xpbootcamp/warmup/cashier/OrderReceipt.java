package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.round;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private static final String DISCOUNT_DAY = "星期三";
    private static final String TOTAL_PRICE = "总价：";
    private static final String TAX = "税额：";
    private static final String DISCOUNT = "折扣：";
    private static final String HEADER = "======老王超市，值得信赖======\n";
    private static final int SCALE = 2;
    private static final double DISCOUNT_RATE = 0.98;

    private Order order;

    private final DateUtil dateUtil;

    public OrderReceipt(Order order) {
        this.order = order;
        this.dateUtil = new DateUtil();
    }

    public OrderReceipt(Order order, DateUtil dateUtil) {
        this.order = order;
        this.dateUtil = dateUtil;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printHeader(output);

        printDate(output);

        printLineItems(output);

        printSalesTax(output);

        printDiscount(output);

        printTotalPrice(output);

        return output.toString();
    }

    private void printDiscount(StringBuilder output) {
        if (isTodayWed()) {
            BigDecimal discount = new BigDecimal(order.calculateTotalAmountWithTax() * (1 - DISCOUNT_RATE))
                .setScale(SCALE, RoundingMode.HALF_UP);
            output.append(DISCOUNT).append('\t').append(discount);
        }
    }

    private boolean isTodayWed() {
        return DISCOUNT_DAY.equals(dateUtil.getDateInWeekAsString());
    }

    private void printDate(StringBuilder output) {
        output.append('\n').append(dateUtil.getDateAsString())
            .append(", ").append(dateUtil.getDateInWeekAsString());
    }

    private void printTotalPrice(StringBuilder output) {
        if (isTodayWed()) {
            BigDecimal totalPrice = new BigDecimal(order.calculateTotalAmountWithTax() * DISCOUNT_RATE)
                .setScale(SCALE, RoundingMode.HALF_UP);
            output.append(TOTAL_PRICE).append('\t').append(totalPrice);
        } else {
            output.append(TOTAL_PRICE).append('\t').append(order.calculateTotalAmountWithTax());
        }
    }

    private void printSalesTax(StringBuilder output) {
        output.append(TAX).append('\t').append(order.calculateTotalSalesTax());
    }

    private void printLineItems(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.toString());
        }
    }

    private void printHeader(StringBuilder output) {
        output.append(HEADER);
    }
}