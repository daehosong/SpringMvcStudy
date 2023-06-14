package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//  @Controller
//  보통 컨트롤러를 사용하면 리턴 값이 뷰 이름이 반환이 된다.
//  @RestController를 사용 하면 문자를 반환 하게 되면 바로 문자가 반환이 되어버림
//  즉, Http 메세지 바디에 return 값 문자를 넣어 버린다고 생각하면 된다.
//  간단히 테스트 할 때 좋음. RESTAPI개발할 때 핵심

//private final Loogger log = LoggerFactory.getLogger(getClass));
// 위의 기능을 똑같이 하는 것이 @Slf4j
@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";
        System.out.println("name= "+name);

        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        return "ok";
    }
}
