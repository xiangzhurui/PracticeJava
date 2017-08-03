package framework.web.commons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order")
public class OrderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7953015875710087514L;
    
    public String errorDirect(){
        return "redirect:";
    }
}
