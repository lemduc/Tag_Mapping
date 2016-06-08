package mapping;

import mapping.topicExtraction.TopicExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by EVC_User on 6/7/2016.
 */

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);

        TopicExtractor topicExtractor = new TopicExtractor();

    }
}
