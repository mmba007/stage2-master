from kafka import KafkaProducer, KafkaConsumer
import json

producer=KafkaProducer(value_serializer=lambda v: json.dumps(v).encode('utf-8'),
api_version=(0, 10, 1), bootstrap_servers='192.196.99.100:9094')

producer.send("usersProfiles",{'project_id':2})
consumer = KafkaConsumer('stop',
    bootstrap_servers=['192.196.99.100:9094'],api_version=(0, 10, 1),
    auto_offset_reset='earliest',
    group_id='my-group',
    auto_commit_interval_ms=1000,
    enable_auto_commit=True,
    value_deserializer=lambda m: json.loads(m.decode('utf-8')))
for message in consumer:
        #listOfStreamer[message.value["project_id"]].strem_diconnect()
        print(message.value)
print("ok")