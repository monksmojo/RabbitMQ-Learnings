package com.example.rabbitMQ.rabbitMQdemo;

import java.io.Serializable;

public class SimpleMessage implements Serializable {

    // we implemented the serializable interface
    // to see the data stored in the queue

    private String name;


    private String description;

    public SimpleMessage(){ }

    public SimpleMessage(String name,String description){
        this.name=name;
        this.description=description;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
