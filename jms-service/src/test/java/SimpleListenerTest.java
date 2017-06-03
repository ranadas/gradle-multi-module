import com.rdas.jmsa.BootJmsApplication;
import com.rdas.jmsa.model.Order;
import com.rdas.jmsa.service.JmsQueueWriterService;
import com.rdas.jmsa.service.StoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by x148128 on 30/05/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = BootJmsApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJmsApplication.class)
//@Import(BootJmsApplication.class)
public class SimpleListenerTest {
    @Autowired
    private JmsQueueWriterService clientService;

    @Autowired
    private StoreService storeService;

    /**
     *    private final String id;
     private final String details;
     private BigDecimal amount;
     private String currency;
     */
    @Test
    public void sendSimpleMessage() {
        clientService.addOrder(new Order("10", "order1", new BigDecimal(100), "EUR"));

        Optional<Order> storedOrder = storeService.getReceivedOrder("order1");
        Assert.assertTrue(storedOrder.isPresent());
        Assert.assertEquals("order1", storedOrder.get().getId());
    }
}
