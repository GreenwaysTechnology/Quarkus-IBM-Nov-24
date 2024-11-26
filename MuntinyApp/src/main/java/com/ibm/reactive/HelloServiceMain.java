package com.ibm.reactive;

public class HelloServiceMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloService();
        //subscribe
        helloService.sayHello().subscribe().with(item->{
            System.out.println("Got : " + item );
        });
    }
}
