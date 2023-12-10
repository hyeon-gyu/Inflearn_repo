package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}




/** h2 console에서 jdbc url 이걸로 해서접속해야함
 * 기존걸로 하면 여러 app에서 접근시 충돌 일어날 수도
 * jdbc:h2:tcp://localhost/~/test
 * */