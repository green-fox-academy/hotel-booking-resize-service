package com.greenfox.malachit.service;

import com.rabbitmq.client.*;

import java.io.IOException;

public class MessageQueueService {

  private final static String QUEUE_NAME = "hello";

  public void sendMessage() throws Exception{

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,"message".getBytes());
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }

  public void receiveMessage() throws Exception{

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);

    channel.close();
    connection.close();
  }
}
