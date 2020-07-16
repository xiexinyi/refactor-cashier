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
        if ("星期三".equals(dateUtil.getDateInWeekAsString())) {
            BigDecimal discount = new BigDecimal(order.calculateTotalAmountWithTax() * 0.02)
                .setScale(2, RoundingMode.HALF_UP);
            output.append("折扣：").append('\t').append(discount);
        }
    }

    private void printDate(StringBuilder output) {
        output.append('\n').append(dateUtil.getDateAsString())
            .append(", ").append(dateUtil.getDateInWeekAsString());
    }

    private void printTotalPrice(StringBuilder output) {
        if ("星期三".equals(dateUtil.getDateInWeekAsString())) {
            BigDecimal totalPrice = new BigDecimal(order.calculateTotalAmountWithTax() * 0.98)
                .setScale(2, RoundingMode.HALF_UP);
            output.append("总价：").append('\t').append(totalPrice);
        } else {
            output.append("总价：").append('\t').append(order.calculateTotalAmountWithTax());
        }
    }

    private void printSalesTax(StringBuilder output) {
        output.append("税额：").append('\t').append(order.calculateTotalSalesTax());
    }

    private void printLineItems(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.toString());
        }
    }

    private void printHeader(StringBuilder output) {
        output.append("======老王超市，值得信赖======\n");
    }
}