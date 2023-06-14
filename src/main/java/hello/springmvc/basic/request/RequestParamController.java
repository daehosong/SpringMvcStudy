package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age= {} ",username,age);
        response.getWriter().write("ok");
    }

    //  @RestController와 같은 효과
    //  @ResponseBody는 Http 메세지 바디를 직접 조회하는 기능
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){
            log.info("username={} , age={} ", memberName, memberAge);
    return "ok";
    }
    //  @RequestParam 생략 할 수 도 있음. 단, 변수명이 요청 파라미터와 같아야 함
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){
        log.info("username={} , age={} ", username, age);
        return "ok";
    }
    //  @RequestParam을 완전히 생략 할 수도 있음. (int ,String ,Integer 같은 간단한 단순타입형일 때)
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(@RequestParam String username, int age){
        log.info("username={} , age={} ", username, age);
        return "ok";
    }
    //  @RequestParam(required = false/true(기본)) true는 무조건 있어야 하는 값, false는 없어도 문제 없는 값
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age){

    //  null , "" -> null은 true 값에 걸러지지만 "" 빈문자값은 걸러지지 않기 때문에 조심해야함.
        log.info("username={} , age={} ", username, age);
        return "ok";
    }
    //  defaultValue="" -> 빈문자에도 설정된 값이 적용이 되고 , 값이 오지 않았을 경우에 사용할 값을 저장하는 코드다.
    //  사실 그래서 defaultValue 가 있다면 required가 필요가 없다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true,
                                        defaultValue = "guest") String username,
                                      @RequestParam(required = false,
                                      defaultValue = "-1") int age){
        log.info("username={} , age={} ", username, age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    //  파라미터 값이 여러개라면 MultiValueMap 으로도 사용이 가능하다.
    //  파라미터 값은 보통 하나만 쓴다.
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={} , age={} ", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //  ModelAttribute가 있으면 다음과 같이 실행함. 아래로 예시를 든다면,
    //  HelloData 객체를 생성 한다. -> 요청 파라미터의 이름으로 HelloData 객체 프로퍼티를 찾는다.(getter,setter)
    //  해당 프로퍼티의 setter를 호출하여 파라미터의 값을 입력 한다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={} , age={} ", helloData.getUsername(), helloData.getAge());
        return "ok-modelattribute";
    }
    //  ModelAttribute 도 생략 가능
    //  RequestParam도 생략 가능한데 구분 가능한 점은 간단한 타입형은 RequestParam, 그 외 나머지 ModelAttribute
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={} , age={} ", helloData.getUsername(), helloData.getAge());
        return "ok-modelattribute2";
    }

}
