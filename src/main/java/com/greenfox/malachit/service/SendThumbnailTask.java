package com.greenfox.malachit.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class SendThumbnailTask {

  private final static String QUEUE_NAME = "thumbnails";

  public void sendTask(String task) throws Exception {
    Connection connection = initConnection();
    Channel channel = initChannel(connection);
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, task.getBytes());
    closeMQ(connection, channel);
  }

  private Connection initConnection() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(System.getenv("RABBIT"));
    Connection connection = factory.newConnection();
    return connection;
  }

  private Channel initChannel(Connection connection) throws Exception {
    Channel channel = connection.createChannel();
    return channel;
  }

  private void closeMQ(Connection connection, Channel channel) throws Exception {
    channel.close();
    connection.close();
  }
}
