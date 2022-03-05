public class TestMyFramework {
    public static void main(String[] args) {
        Class<ClassForTest> clazz = ClassForTest.class;
        System.out.println("сработка с обьектом Class :"+"\n");
        MyFrameworkApp.start(clazz);
        System.out.println("\n");
        System.out.println("сработка с именем класса :"+"\n");
        MyFrameworkApp.start("ClassForTest");
    }
}
