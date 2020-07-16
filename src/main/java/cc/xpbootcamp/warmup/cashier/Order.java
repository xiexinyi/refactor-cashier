package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {
    private static final double TAX_RATE = .10;
    private String customerName;
    private String address;
    private List<LineItem> lineItemList;

    public Order(String customerName, String address, List<LineItem> lineItemList) {
        this.customerName = customerName;
        this.address = address;
        this.lineItemList = lineItemList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return address;
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
