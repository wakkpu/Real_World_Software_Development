package item1;

public class Instance {

    private String name = "Hong";
    private String stdCode = "123456789";

    public Instance() {}

    public Instance(String name) {
        this.name = name;
    }

	/* 1개의 String 타입의 파라미터를 갖는 생성자가 이미 존재하므로 오류 발생
	public Instance(String stdCode) {
		this.stdCode = stdCode;
	} */

    // 시그니처가 같은 생성자 여러 개 생성 가능
    // 시그니처는 메서드 명과 파라미터의 순서, 타입, 개수를 의미.
    public static Instance studentWithName(String name) {
        Instance instance = new Instance();
        instance.name = name;
        return instance;
    }

    public static Instance studentWithStdCode(String stdCode) {
        Instance instance = new Instance();
        instance.stdCode = stdCode;
        return instance;
    }

    public static void main(String[] args) {
        Instance student1 = new Instance("Jeon");
        // Jeon이라는 이름을 갖는 인스턴스 생성
        Instance student2 = Instance.studentWithName("Jeon");
        // 201703132라는 학번을 갖는 인스턴스 생성
        Instance student3 = Instance.studentWithStdCode("201703132");
    }
}