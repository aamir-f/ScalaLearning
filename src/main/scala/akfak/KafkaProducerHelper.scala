package akfak

import java.util.Properties

 import org.apache.kafka.clients.producer.{KafkaProducer => NewKafkaProducer, ProducerConfig}

object KafkaProducerHelper {
  def createNewKafkaProducer(brokerList: String,
                             acks: Int = -1,
                             metadataFetchTimeout: Long = 3000L,
                             blockOnBufferFull: Boolean = true,
                             bufferSize: Long = 1024L * 1024L,
                             retries: Int = 0): NewKafkaProducer[Array[Byte], Array[Byte]] = {

    println("Kafka Producer ====> " + brokerList);
    val producerProps = new Properties()
    producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList)
    producerProps.put(ProducerConfig.ACKS_CONFIG, acks.toString)
    // producerProps.put(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG, metadataFetchTimeout.toString)
    // producerProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, blockOnBufferFull.toString)
    producerProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferSize.toString)
    producerProps.put(ProducerConfig.RETRIES_CONFIG, retries.toString)
    producerProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "100")
    producerProps.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, "200")
    producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")
    producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer")

    new NewKafkaProducer[Array[Byte], Array[Byte]](producerProps)
  }
}