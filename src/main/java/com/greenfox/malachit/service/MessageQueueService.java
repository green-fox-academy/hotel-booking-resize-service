package com.greenfox.malachit.service;

import com.greenfox.malachit.repository.ImageDataRepository;
import com.greenfox.malachit.repository.ThumbnailRepository;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageQueueService {

  ImageDataRepository imageDataRepository;
  ThumbnailRepository thumbnailRepository;

  private final static String QUEUE_NAME = "hello";
  private ConnectionFactory factory ;
  private Connection connection;
  private Channel channel;

  @Autowired
  public MessageQueueService(ImageDataRepository imageDataRepository, ThumbnailRepository thumbnailRepository) {
    this.imageDataRepository = imageDataRepository;
    this.thumbnailRepository = thumbnailRepository;
  }

  public void sendMessage() throws Exception{
    initMQ();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "ok";
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
    closeMQ();
  }

  public void receiveMessage() throws Exception{
    initMQ();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {}
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

  public void sendTask(long id) throws Exception {
    SendThumbnailTask sendThumbnailTask = new SendThumbnailTask();
    String fileName = imageDataRepository.findOne(id).getUniqueName();
    sendThumbnailTask.sendTask(fileName);
  }

  public void receiveTask() throws Exception {
    ReceiveThumbnailTask receiveThumbnailTask = new ReceiveThumbnailTask(imageDataRepository, thumbnailRepository);
    receiveThumbnailTask.receiveTask();
  }
}