package com.ibm.rest.client.registry;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ServiceOptions;

@ApplicationScoped
public class BeanRegistry {
    //Consul server Host
    @ConfigProperty(name = "consul.host")
    String host;
    @ConfigProperty(name = "consul.port")
    int port;

    //service host and port
    @ConfigProperty(name = "hello-service-port", defaultValue = "9000")
    int helloPort;

    public void init(@Observes StartupEvent startupEvent, Vertx vertx) {
        ConsulClient client = ConsulClient.create(vertx,
                new ConsulClientOptions().setHost(host).setPort(port));
        //Register service with Consul Server
//        client.registerService(new ServiceOptions()
//                .setId("hello")
//                .setName("hello-service")
//                .setAddress("localhost")
//                .setPort(helloPort));

        //Load balancing service
//        ServiceOptions serviceOptions1 = new ServiceOptions()
//                .setId("hello1")
//                .setName("hello-service")
//                .setAddress("localhost")
//                .setPort(helloPort);
//
//        ServiceOptions serviceOptions2 = new ServiceOptions()
//                .setId("hello2")
//                .setName("hello-service")
//                .setAddress("localhost")
//                .setPort(helloPort);
//
//        client.registerService(serviceOptions1);
//        client.registerService(serviceOptions2);
        //scaling should be via coding
        for (int i = 0; i < 10; i++) {
            client.registerService(new ServiceOptions()
                    .setId("hello" + i)
                    .setName("hello-service")
                    .setAddress("localhost")
                    .setPort(helloPort));
        }


    }


}
