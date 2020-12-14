## $5 Tech Unlocked 2021!
[Buy and download this Book for only $5 on PacktPub.com](https://www.packtpub.com/product/microservices-deployment-cookbook/9781786469434)
-----
*If you have read this book, please leave a review on [Amazon.com](https://www.amazon.com/gp/product/178646943X).     Potential readers can then use your unbiased opinion to help them make purchase decisions. Thank you. The $5 campaign         runs from __December 15th 2020__ to __January 13th 2021.__*

[![GitHub issues](https://img.shields.io/github/issues/PacktPublishing/Microservices-Deployment-Cookbook.svg)](https://github.com/PacktPublishing/Microservices-Deployment-Cookbook/issues)   [![GitHub forks](https://img.shields.io/github/forks/PacktPublishing/Microservices-Deployment-Cookbook.svg)](https://github.com/PacktPublishing/Microservices-Deployment-Cookbook/network)   [![GitHub stars](https://img.shields.io/github/stars/PacktPublishing/Microservices-Deployment-Cookbook.svg)](https://github.com/PacktPublishing/Microservices-Deployment-Cookbook/stargazers)   [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/PacktPublishing/Microservices-Deployment-Cookbook/master/LICENSE)

# Microservices Deployment Cookbook
This is the code repository for [Microservices Deployment Cookbook](https://www.packtpub.com/virtualization-and-cloud/microservices-deployment-cookbook?utm_source=github&utm_medium=repository&utm_campaign=9781786469434), published by Packt. It contains all the supporting project files necessary to work through the book from start to finish.


## About the Book
This book will help any team or organization understand, deploy, and manage microservices at scale. It is driven by a sample application, helping you gradually build a complete microservice-based ecosystem. Rather than just focusing on writing a microservice, this book addresses various other microservice-related solutions: deployments, clustering, load balancing, logging, streaming, and monitoring.

The initial chapters offer insights into how web and enterprise apps can be migrated to scalable microservices. Moving on, you’ll see how to Dockerize your application so that it is ready to be shipped and deployed. We will look at how to deploy microservices on Mesos and Marathon and will also deploy microservices on Kubernetes. Next, you will implement service discovery and load balancing for your microservices. We’ll also show you how to build asynchronous streaming systems using Kafka Streams and Apache Spark.

Finally, we wind up by aggregating your logs in Kafka, creating your own metrics, and monitoring the metrics for the microservice.
## Instructions and Navigation
All of the code is organized into folders. For example, Chapter02.



The code will look like the following:
```
package com.packt.microservices.geolocation;

import java.io.Serializable;
import java.util.UUID;

public class GeoLocation implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private double latitude;
  private double longitude;
  private UUID userId;
  private long timestamp;
  
  public double getLatitude() {
    return latitude;
  }
  
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }
  
  public double getLongitude() {
    return longitude;
  }
```



## Related Microservices Products
* [Mastering Microservices with Java](https://www.packtpub.com/application-development/mastering-microservices-java?utm_source=github&utm_medium=repository&utm_campaign=9781785285172)

* [Microservices with Azure](https://www.packtpub.com/virtualization-and-cloud/microservices-azure?utm_source=github&utm_medium=repository&utm_campaign=9781787121140)

* [Developing Microservices with Node.js](https://www.packtpub.com/web-development/developing-microservices-nodejs?utm_source=github&utm_medium=repository&utm_campaign=9781785887406)


