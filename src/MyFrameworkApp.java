import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;


public class MyFrameworkApp {

    static void start(Class clazz) {

        Method[] methods = clazz.getDeclaredMethods();

        List<Method> methodList = new LinkedList<>();
        int countBeforeSuite = 0;
        int countAfterSuite = 0;
        Method afterSuiteMethod = null;
        Method beforeSuiteMethod = null;

        for (Method method : methods) {
            if (method.getDeclaredAnnotation(Test.class) != null) {
                methodList.add(method);
            } else if (method.getDeclaredAnnotation(BeforeSuite.class) != null) {
                beforeSuiteMethod = method;
                countBeforeSuite++;
            } else if (method.getDeclaredAnnotation(AfterSuite.class) != null) {
                afterSuiteMethod = method;
                countAfterSuite++;
            }
        }
        if (countAfterSuite > 1 || countBeforeSuite > 1) {
            throw new RuntimeException("Check annotations in testing class");
        }

        methodList.sort((o1, o2) -> {
            if (o1.getDeclaredAnnotation(Test.class).priority() > o2.getDeclaredAnnotation(Test.class).priority())
                return 1;
            else if (o1.getDeclaredAnnotation(Test.class).priority() < o2.getDeclaredAnnotation(Test.class).priority())
                return -1;
            return 0;
        });
        if (countAfterSuite == 1) {
            methodList.add(afterSuiteMethod);
        }
        if (countBeforeSuite == 1) {
            methodList.add(0, beforeSuiteMethod);
        }

        try {
            Constructor constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();

            for (Method method : methodList) {
                method.invoke(obj);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }



    static void start(String name) {

        Class clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method[] methods = clazz.getDeclaredMethods();

        List<Method> methodList = new LinkedList<>();
        int countBeforeSuite = 0;
        int countAfterSuite = 0;
        Method afterSuiteMethod = null;
        Method beforeSuiteMethod = null;

        for (Method method : methods) {
            if (method.getDeclaredAnnotation(Test.class) != null) {
                methodList.add(method);
            } else if (method.getDeclaredAnnotation(BeforeSuite.class) != null) {
                beforeSuiteMethod = method;
                countBeforeSuite++;
            } else if (method.getDeclaredAnnotation(AfterSuite.class) != null) {
                afterSuiteMethod = method;
                countAfterSuite++;
            }
        }
        if (countAfterSuite > 1 || countBeforeSuite > 1) {
            throw new RuntimeException("Check annotations in testing class");
        }

        methodList.sort((o1, o2) -> {
            if (o1.getDeclaredAnnotation(Test.class).priority() > o2.getDeclaredAnnotation(Test.class).priority())
                return 1;
            else if (o1.getDeclaredAnnotation(Test.class).priority() < o2.getDeclaredAnnotation(Test.class).priority())
                return -1;
            return 0;
        });
        if (countAfterSuite == 1) {
            methodList.add(afterSuiteMethod);
        }
        if (countBeforeSuite == 1) {
            methodList.add(0, beforeSuiteMethod);
        }


        try {
            Constructor constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();

            for (Method method : methodList) {
                method.invoke(obj);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}


