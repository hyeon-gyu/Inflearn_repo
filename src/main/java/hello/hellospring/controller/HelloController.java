package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.GetExchange;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","lim!!"); //model(data:hello!!)
        return "spring"; //templates의 spring.html을 실행시켜라(thymeleaf 템플릿 엔진 처리)
        //컨트롤러가 리턴 값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리한다.
        //템플릿 엔진 기본 viewName 매핑 : resources:templates/viewname+.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name); //(변수이름, 변수에 넣을 값) -> html에서 ${}를 이용해 값을 가져온다
        //url 검색할때 localhost:8080/hello-mvc?name=lim1 이라고 쳐야함
        return "hello-template"; //HTML파일이 넘어감
    }

    @GetMapping("hello-string")
    @ResponseBody //HTTP통신에서 응답 BODY부분에 데이터를 넣겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //VIEW 필요없이 문자열이 곧장 화면에 출력
    }

    @GetMapping("hello-api")
    @ResponseBody //반환할때 리턴값이 객체라면 디폴트값은 json이다    //@responsebody가 없으면 viewresolver에게 전달
    public Hello helloApi(@RequestParam ("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 넘김 화면에 json으로 출력됨
        //localhost:8080/hello-api?name=lim 검색하면 {"name":"lim"} 처럼보임
    }
    static class Hello{
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
