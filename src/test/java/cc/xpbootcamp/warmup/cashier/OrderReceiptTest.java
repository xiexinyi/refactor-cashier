package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderReceiptTest {

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk\t10.0\t2\t20.0\n"));
        assertThat(output, containsString("biscuits\t5.0\t5\t25.0\n"));
        assertThat(output, containsString("chocolate\t20.0\t1\t20.0\n"));
        assertThat(output, containsString("Sales Tax\t6.5"));
        assertThat(output, containsString("Total Amount\t71.5"));
    }

    @Test
    void shouldPrintHeaderOnReceipt() {
        OrderReceipt receipt = new OrderReceipt(new Order(new ArrayList<>()));

        String output = receipt.printReceipt();

        assertThat(output, startsWith("======老王超市，值得信赖======\n"));
    }

    @Test
    void shouldPrintDateOnReceipt() {
        DateUtil mockDateUtil = mock(DateUtil.class);
        when(mockDateUtil.getDateAsString()).thenReturn("2020年7月17日");
        when(mockDateUtil.getDateInWeekAsString()).thenReturn("星期日");

        OrderReceipt receipt = new OrderReceipt(new Order(new ArrayList<>()), mockDateUtil);

        String output = receipt.printReceipt();

        assertThat(output, containsString("2020年7月17日"));
            assertThat(output, containsString("星期日"));
    }
}