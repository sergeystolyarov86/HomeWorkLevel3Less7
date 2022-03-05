public class ClassForTest {

    @Test(priority = 1)
    void method1() {
        System.out.println("method1 сработает вторым");
    }

    @AfterSuite
    void method2() {
        System.out.println("method2 сработает последним");
    }

    @BeforeSuite
    void method3() {
        System.out.println("method3 сработает в начале");
    }

    @Test(priority = 6)
    void method4() {
        System.out.println("method4 сработает после BLA BLA BLA");
    }

    @Test
    void method5() {
        System.out.println("method5 BLA BLA BLA");
    }

    @Test
    void method6() {
        System.out.println("method6 BLA BLA BLA");
    }

}
