package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 스프링 컨테이너가 생성되고
 * 컨트롤러 어노테이션이 있다면 컨트롤러 객체를 생성해서
 * 스프링에 저장 & 관리
 */

@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();

    /**
     * 이렇게 쓰면 나중에 여러개의 controller마다 각각의 서비스 객체를 가지게 됨
     * 스프링 컨테이너에서 하나의 서비스만 가지고 다루고자 한다면
     * service class에 @service를 작성해주자
     */

    @Autowired
    /** membercontroller가 생성될때
     * 스프링 bean에 등록있는 멤버서비스 객체를 넣어줌(CONTROLLER와 연결 = dependency injection)*/
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        /**input박스에 이름을 입력하고 등록버튼을 누르면 form tag의 post 방식으로 name이 넘어온다*/
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
