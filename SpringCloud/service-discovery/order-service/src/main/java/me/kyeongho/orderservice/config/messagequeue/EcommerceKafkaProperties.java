package me.kyeongho.orderservice.config.messagequeue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "kafka")
@Data
public class EcommerceKafkaProperties {

    private Consumer consumer;
    private Server server;
    private Map<String, String> properties = new HashMap<>();

    @Data
    public static class Consumer {

        private String groupId;
    }

    @Data
    public static class Server {

        private String host;
        private String port;

        /**
         * @return host + ":" + port
         */
        public String getUrl() {
            return host + ":" + port;
        }
    }
}
