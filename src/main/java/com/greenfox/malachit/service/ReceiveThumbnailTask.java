package com.greenfox.malachit.service;

import com.greenfox.malachit.repository.ImageDataRepository;
import com.greenfox.malachit.repository.ThumbnailRepository;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveThumbnailTask {

  private final UploadService uploader = new UploadService();
  private final ImageDataRepository imageDataRepository;
  private final ThumbnailRepository thumbnailRepository;

  private final static String QUEUE_NAME = "thumbnails";

  public ReceiveThumbnailTask(ImageDataRepository imageDataRepository, ThumbnailRepository thumbnailRepository) {
    this.imageDataRepository = imageDataRepository;
    this.thumbnailRepository = thumbnailRepository;
  }

  public void receiveTask() throws Exception {
    Connection connection = initConnection();
    Channel channel = initChannel(connection);
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(
          String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        try {
          uploader.downloadImage(message, imageDataRepository, thumbnailRepository);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          System.out.println(" [x] Done");
          channel.basicAck(envelope.getDeliveryTag(), false);
          try {
            closeMQ(connection, channel);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    };
    channel.basicConsume(QUEUE_NAME, false, consumer);
  }

  private Connection initConnection() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    return connection;
  }

  private Channel initChannel(Connection connection) throws Exception {
    Channel channel = connection.createChannel();
    channel.basicQos(1);
    return channel;
  }

  private void closeMQ(Connection connection, Channel channel) throws Exception {
    channel.close();
    connection.close();
  }
}
