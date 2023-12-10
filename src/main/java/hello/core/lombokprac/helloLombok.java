package hello.core.lombokprac;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString /** 이거 쓰면 객체 내부가 보임, 안쓰면 객체 주소만 출력됨*/
public class helloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        helloLombok helloLombok = new helloLombok();
        helloLombok.setAge(14);
        helloLombok.setName("sdfsdf");
        System.out.println(helloLombok.getAge());
        System.out.println(helloLombok.getName());
        System.out.println("hellolombok using ToString"+ helloLombok);
    }
}
