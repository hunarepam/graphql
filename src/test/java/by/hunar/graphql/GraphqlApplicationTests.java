package by.hunar.graphql;

import static org.mockito.Mockito.when;

import by.hunar.graphql.model.Order;
import by.hunar.graphql.model.OrderState;
import by.hunar.graphql.service.OrderService;
import java.util.Collections;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.boot.test.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureGraphQlTester
class GraphqlApplicationTests {

	@Autowired
	private WebGraphQlTester graphQlTester;
	@MockBean
	private OrderService orderService;

	@Test
	void contextLoads() {
		String orderName = "Kastus Kalinouski";
		OrderState state = new OrderState(1, "In processing");
		Order order = new Order(1, orderName,
			state, null, new Date(System.currentTimeMillis()));

		when(orderService.getAllOrders()).thenReturn(Collections.singletonList(order));

		graphQlTester
			.query("query{orders{id name}}")
			.execute()
			.path("data.orders[*].name")
			.entityList(String.class)
			.contains(orderName);

	}

}
