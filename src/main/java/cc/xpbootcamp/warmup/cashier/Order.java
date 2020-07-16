package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {
    private static final double TAX_RATE = .10;
    private List<LineItem> lineItemList;

    public Order(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    double calculateTotalAmountWithTax() {
      double total = 0d;
      for (LineItem lineItem : getLineItems()) {
        total += lineItem.totalAmount() + lineItem.totalAmount() * TAX_RATE;
      }
      return total;
    }

    double calculateTotalSalesTax() {
      double totalSalesTax = 0d;
      for (LineItem lineItem : getLineItems()) {

        double salesTax = lineItem.totalAmount() * TAX_RATE;
        totalSalesTax += salesTax;
      }
      return totalSalesTax;
    }
}
