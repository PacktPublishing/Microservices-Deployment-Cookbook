package com.packt.microservices.geolocation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.google.gson.Gson;

public class GeoLocationJob {

	public static Producer<String, String> producer;

	public static void main(String[] args) throws Exception {
		SparkConf conf = new SparkConf().setAppName("geolocationJob").setMaster("local[1]");
		JavaStreamingContext context = new JavaStreamingContext(conf, new Duration(2000));

		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "192.168.99.100:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "geolocationJob");
		kafkaParams.put("auto.offset.reset", "latest");
		kafkaParams.put("enable.auto.commit", false);

		Collection<String> topics = Arrays.asList("geolocationJob");

		final JavaInputDStream<ConsumerRecord<String, String>> dstream = KafkaUtils.createDirectStream
				(context,
						LocationStrategies.PreferConsistent(),
						ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams));

		dstream.map(new Function<ConsumerRecord<String,String>, GeoLocation>() { // map to GeoLocation
			private static final long serialVersionUID = -5289370913799710097L;

			@Override
			public GeoLocation call(ConsumerRecord<String, String> record) throws Exception {
				return new Gson().fromJson(record.value(), GeoLocation.class);
			}
		}).filter(new Function<GeoLocation, Boolean>() { // filter out invalid geolocations
			private static final long serialVersionUID = 6980980875802694946L;

			@Override
			public Boolean call(GeoLocation geolocation) throws Exception {
				System.out.println("Spark Job received => " + geolocation);
				return geolocation.getLatitude() >= -90 
						&& geolocation.getLatitude() < 90 
						&& geolocation.getLongitude() >= -180 
						&& geolocation.getLongitude() < 180;
			}
		}).foreachRDD(new VoidFunction<JavaRDD<GeoLocation>>() { //iterate over RDD
			private static final long serialVersionUID = -4161320579495422870L;

			@Override
			public void call(JavaRDD<GeoLocation> rdd) throws Exception {
				rdd.foreach(new VoidFunction<GeoLocation>() {  // send valid geolocations to another topic
					private static final long serialVersionUID = -3282778715126743482L;

					@Override
					public void call(GeoLocation geolocation) throws Exception {
						ProducerRecord<String, String> record = new ProducerRecord<>(
								"geolocations", 
								geolocation.toString());
						getProducer().send(record);
					}
				});
			}
		});

		context.start();
		context.awaitTermination();
	}

	public static Producer<String, String> getProducer() {
		if(producer == null) {
			Properties props = new Properties();
			props.put("bootstrap.servers", "192.168.99.100:9092");
			props.put("key.serializer", StringSerializer.class.getName());
			props.put("value.serializer", StringSerializer.class.getName());

			producer = new KafkaProducer<>(props);
		}
		return producer;
	}
}
