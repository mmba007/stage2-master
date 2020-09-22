# example using kafka-python
import kafka


consumer = kafka.KafkaConsumer(group_id='test', bootstrap_servers=['192.168.99.100:9094'])
topics = consumer.topics()

if not topics: 
    raise RuntimeError()