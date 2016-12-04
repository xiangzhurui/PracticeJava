package framework.spring.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="managerIndexController")
@RequestMapping("/manager/index")
public class IndexController {
    @RequestMapping(value={"/","","/index","/index.html"})
    public String home(){
        return "manager/index";
    }

}
