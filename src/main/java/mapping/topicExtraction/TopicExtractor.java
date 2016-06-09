package mapping.topicExtraction;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;

/**
 * Created by EVC_User on 6/7/2016.
 */
public class TopicExtractor {

    private ResourceLoader resourceLoader;
    Resource[] resources;
    HashMap<String, String> mapping = new HashMap<>();

    @Autowired
    public TopicExtractor(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    Resource[] loadResources(String pattern) throws IOException {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
    }

    public TopicExtractor() {
        resourceLoader = new FileSystemResourceLoader();
        try {
            resources= loadResources("data/_sent_mail/*");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Resource resource:resources){

            try {
                String input = IOUtils.toString(resource.getInputStream());
                String tag = input.split("\n")[2].split(" ")[1];
                System.out.println(tag);

                // try to extract keywords
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
                String url = "http://rabid.engba.veritas.com:8081/vishay/pos_tag";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("sent", input);

          //       HttpEntity<Keywords> entity = new HttpEntity<>(new Keywords());
                HttpEntity<?> entity = new HttpEntity<>(headers);
                 HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, String.class);
           //      Keywords response = restTemplate.postForObject(builder.build().encode().toUri(), entity, Keywords.class);
//                 Keywords keywords = restTemplate.postForObject(url, Keywords.class)

                 System.out.println(response);
                 String body = response.getBody();
                 String[] keywords =  body.substring(1, body.length()-2).replace("\"", "").split(",");
                 for (String keyword: keywords){

                 }
//                String keywords = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
