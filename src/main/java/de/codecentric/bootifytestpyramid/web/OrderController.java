package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.Address;
import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;
import de.codecentric.bootifytestpyramid.domain.Order;
import de.codecentric.bootifytestpyramid.domain.OrderGateway;
import de.codecentric.bootifytestpyramid.domain.OrderItem;
import de.codecentric.bootifytestpyramid.domain.Price;
import de.codecentric.bootifytestpyramid.domain.Weight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderGateway orderGateway;

    public OrderController(final OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // enable validation on fields
        binder.initDirectFieldAccess();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll(@RequestParam(value = "city", required = false) Optional<String> city) {
        List<Order> orders = city.map(orderGateway::findByCity).orElse(orderGateway.findAll());

        List<OrderResponse> responses = orders.stream()
                .map(OrderResponse::new)
                .collect(toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOne(@PathVariable("id") final Long id) {
        Optional<Order> order = orderGateway.findById(id);

        return order.map(OrderResponse::new).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final CreateOrderRequest createOrderRequest,
                                    final UriComponentsBuilder builder) {
        final Order created = orderGateway.createOrder(createOrderRequest.toCommand());

        final URI location = builder
                .pathSegment("orders", Long.toString(created.getId()))
                .build()
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
