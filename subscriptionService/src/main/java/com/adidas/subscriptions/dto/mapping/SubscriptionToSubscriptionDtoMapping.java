package com.adidas.subscriptions.dto.mapping;

import com.adidas.subscriptions.dto.SubscriptionDto;
import com.adidas.subscriptions.model.Customer;
import com.adidas.subscriptions.model.Newsletter;
import com.adidas.subscriptions.model.Subscription;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionToSubscriptionDtoMapping implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Subscription.class, SubscriptionDto.class)
                .customize(new CustomMapper<Subscription, SubscriptionDto>() {
                    @Override
                    public void mapAtoB(Subscription subscription, SubscriptionDto dto, MappingContext context) {
                        dto.setEmail(subscription.getCustomer().getEmail());
                        dto.setConsent(subscription.isConsent());
                        dto.setDateOfBirth(subscription.getCustomer().getDateOfBirth());
                        dto.setFirstName(subscription.getCustomer().getFirstName());
                        dto.setGender(subscription.getCustomer().getGender());
                        dto.setNewsletterId(subscription.getNewsletter().getId());
                    }

                    @Override
                    public void mapBtoA(SubscriptionDto dto, Subscription subscription, MappingContext context) {
                        Customer  customer = new Customer().setDateOfBirth(dto.getDateOfBirth())
                                .setEmail(dto.getEmail())
                                .setFirstName(dto.getFirstName())
                                .setGender(dto.getGender());
                        Newsletter newsletter = new Newsletter().setId(dto.getNewsletterId());
                        subscription.setNewsletter(newsletter).setCustomer(customer).setConsent(dto.isConsent());

                    }
                    })
                .byDefault()
                .register();
    }


}
