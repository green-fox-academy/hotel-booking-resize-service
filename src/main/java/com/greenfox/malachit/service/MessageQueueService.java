package com.greenfox.malachit.service;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageQueueService {

  private final static String QUEUE_NAME = "hello";
  private ConnectionFactory factory ;
  private Connection connection;
  private Channel channel;

  public void sendMessage() throws Exception{
    initMQ();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "ok";
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,"message".getBytes());
    closeMQ();
  }

  public void receiveMessage() throws Exception{
    initMQ();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
        String message = new String(body, "UTF-8");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
    closeMQ();
  }

  public int messagesInQueue() throws Exception{
    initMQ();
    AMQP.Queue.DeclareOk dok = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    return dok.getMessageCount();
  }

  public void dispatch(String inputMessage) throws Exception{
    initMQ();
    channel.queueDeclare(System.getenv("SEND_EVENT_QUEUE_NAME"), false, false, false, null);
    String message = new EventCreator(inputMessage).createEventString();
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,"message".getBytes());
    closeMQ();
  }

  private void initMQ() throws Exception {
    factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    connection = factory.newConnection();
    channel = connection.createChannel();
  }

  private void closeMQ() throws Exception {
    channel.close();
    connection.close();
  }
}
