package hello.springmvc.basic.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
    /**
     * 회원 관리 API
     * 회원 목록 조회: GET /users
     * 회원 등록: POST /users
     * 회원 조회: GET /users/{userId}
     * 회원 수정: PATCH /users/{userId}
     * 회원 삭제: DELETE /users/{userId}
     */
        //@GetMapping("/mapping/users")
        @GetMapping
        public String user(){
            return "get users";
        }

        //@PostMapping("/mapping/users")
        @PostMapping
        public String adduser(){
            return "post users";
        }

        //@GetMapping("/mapping/users/{userId}")
        @GetMapping("/{userId}")
        public String findUser(@PathVariable("userId") String userId){
            return "get userId = "+userId ;
        }

        //@PatchMapping("/mapping/users/{userId}")
        @PatchMapping("/{userId}")
        public String updateUser(@PathVariable("userId") String userId){
            return "update userId = " + userId ;
        }

        //@DeleteMapping("/mapping/users/{userId}")
        @DeleteMapping("/{userId}")
        public String deleteUser(@PathVariable("userId") String userId){
            return "delete userId = "+userId ;
        }
}
