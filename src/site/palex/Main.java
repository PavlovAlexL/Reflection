package site.palex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * что позволяет рефлексия:
 *
 * Узнать/определить класс объекта;
 * Получить информацию о модификаторах класса, полях, методах, константах, конструкторах и суперклассах;
 * Выяснить, какие методы принадлежат реализуемому интерфейсу/интерфейсам;
 * Создать экземпляр класса, причем имя класса неизвестно до момента выполнения программы;
 * Получить и установить значение поля объекта по имени;
 * Вызвать метод объекта по имени.
 */

public class Main {

    public static void main(String[] args) {
		TestClass tc = new TestClass();
		tc.pringData();        //Sasha 777
		int number = tc.getNumber();    //777
		String name = "";    //null

		try {
			Field[] fieldPublic = tc.getClass().getFields();    //Этот метод вернет все публичные поля класса, но это для примера;
			Field fieldName = tc.getClass().getDeclaredField("name");    //Этот вернет все поля, включая private pritected;
			Field fieldNumber = tc.getClass().getDeclaredField("number");

			fieldName.setAccessible(true);
			fieldNumber.setAccessible(true);    // Этот метод разрешает нам дальнейшую работу с полем объекта;

			name = (String) fieldName.get(tc);    //присваеваем переменной значение приватного поля в объекте tc
			fieldNumber.set(tc, (int) 666); //присваеваем приватному полю в объекте новое значение
			tc.pringData();        //Sasha 666

			Method method = tc.getClass().getDeclaredMethod("printFields");    //Получаем нужный метод по имени доступа к нему
			method.setAccessible(true);    //Даем разрешение на доступ
			method.invoke(tc);    //вызываем метод у объекта	//Sasha 666
		} catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(number + name);    //777 Sasha


		//Создадие экземпляр класса с помощью рефлексии
		tc = null;
//Дефолтный конструктор!!!
		try {
			Class clazz = Class.forName(TestClass.class.getName());	//заставить ClassLoader загрузить TestClass и получить описание его в виде переменной типа Class.
			tc = (TestClass) clazz.newInstance();	//Получив Сlass, вызов метода newInstance() вернет Object, который будет создан по тому самому описанию
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(tc);//output created object reflection.MyClass@60e53b93

		//Конструктор с параметрами!!!
		tc = null;
		try {
			Class clazz = Class.forName(TestClass.class.getName());
			Class[] params = {int.class, String.class};
			tc = (TestClass) clazz.getConstructor(params).newInstance(1, "default2");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(tc);//output created object reflection.MyClass@60e53b93

//Для получения конструкторов класса следует у описания класса вызвать метод getConstructors(), а для получения параметров конструктора - getParameterTypes():
		try {
			Class clazz = Class.forName(TestClass.class.getName());
			Constructor[] constructors = clazz.getConstructors();
			for (Constructor constructor : constructors) {
				Class[] paramTypes = constructor.getParameterTypes();
				for (Class paramType : paramTypes) {
					System.out.print(paramType.getName() + " ");
				}
				System.out.println();
			}
		} catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
		e.printStackTrace();
	}

	}


}

