package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    /** static이라서 하나만 생성*/

    public static SingletonService getInstance(){ //이 메소드를 통해서만 조회할 수 있다. 항상 같은 메소드를 반환한다.
        return instance;
    }

    private SingletonService(){
        // private 생성자라서 어디서도 밖에서 생성할 수 없다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
