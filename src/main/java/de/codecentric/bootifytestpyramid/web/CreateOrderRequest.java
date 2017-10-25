/*
 * Copyright 2017 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
final class CreateOrderRequest {

    @NotNull
    @Valid
    private AddressPart deliveryAddress;

    @NotEmpty
    @Valid
    private List<OrderItemPart> items;

    CreateOrderCommand toCommand() {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand();
        createOrderCommand.deliveryAddress = deliveryAddress.toAddress();
        createOrderCommand.items = items.stream()
                .map(OrderItemPart::toCreateOrderItem)
                .collect(toList());
        return createOrderCommand;
    }
}
