package com.greenfox.malachit.service;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageQueueService {

  private final static String QUEUE_NAME = "hello";

  public void sendMessage() throws Exception{

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "ok";
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,"message".getBytes());

    channel.close();
    connection.close();
  }

  public void receiveMessage() throws Exception{

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
        String message = new String(body, "UTF-8");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);

    channel.close();
    connection.close();
  }

  public int messagesInQueue() throws Exception{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    AMQP.Queue.DeclareOk dok = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    return dok.getMessageCount();
  }

  public void dispach() throws Exception{
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("SEND_EVENT_QUEUE_NAME"));
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "ok";
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,"message".getBytes());

    channel.close();
    connection.close();
  }
}
