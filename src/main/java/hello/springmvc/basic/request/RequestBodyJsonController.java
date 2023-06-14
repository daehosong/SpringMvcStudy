package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {username:"hello",age:"20"}
 * content-type : application/json
 */

@Slf4j
@Controller
//  Json이 있으니까 ObjectMapper 필요함
public class RequestBodyJsonController {
    private ObjectMapper  objectMapper =new ObjectMapper();

    //  주의 사항 : content-type/json 인지 확인을 해야 http메시지 컨버터가 작동함.
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody ={} ",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {} ",helloData.getUsername(),helloData.getAge());
        response.getWriter().write("Ok5");
    }
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody ={} ",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {} ",helloData.getUsername(),helloData.getAge());
        return  "Ok6";
    }
    //  @ResponseBody에 객체를 파라미터로 넘겨줄 수 있음.
    //  @ResponseBody 또는 HttpEntity를 사용하면 http메시지 컨버터가 http 메시지 바디의 내용을
    //  우리가 원하는 문자나 객체 등으로 변환 해준다.
    //  생략은 할 수 없다. 생략을 하게 되면 ModelAttribute로 착각 할 수 있음.
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

        log.info("username = {}, age = {} ",helloData.getUsername(),helloData.getAge());
        return  "ok7";
    }

    //HttpEntity를 사용하면 바디를 꺼내와서 사용 해야 한다.
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity< HelloData> data) throws IOException {
        HelloData helloData = data.getBody();
        log.info("username = {}, age = {} ",helloData.getUsername(),helloData.getAge());
        return  "ok8";
    }
    //  @ResponseBody가 있으면 Http컨버터가 반환할 때도 적용 됨.
    //  HelloData 객체가 http메시지 컨버터로 인해 json으로 바뀐 문자가 메시지 응답으로 바뀌어서 나감.
    //  username,age 값이 있는 json이 HelloData 객체가 되었다가 다시 반환 되어 나갈때 json으로 또 바뀜
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV4(@RequestBody HelloData data) throws IOException {

        log.info("username = {}, age = {} ",data.getUsername(),data.getAge());
        return  data;
    }
}
