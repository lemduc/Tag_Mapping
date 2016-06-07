package mapping.controller;

import mapping.models.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EVC_User on 6/7/2016.
 */

@RestController
public class MappingController {

    @RequestMapping("/get_mapping")
    public Mapping getMapping(@RequestParam(value="keyword", defaultValue = "hello") String keyword){
        return new Mapping(keyword, keyword);
    }

}
