package personal.xzr.practice.web.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = {"", "hello"}, method = RequestMethod.GET)
    public String hello() {
        log.info("进入欢迎页面");
        return "home/hello";
    }
}
