package site.palex;

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
	tc.pringData();		//Sasha 777
	int number = tc.getNumber();	//777
	String name = "";	//null

		try {
			Field[] fieldPublic = tc.getClass().getFields();	//Этот метод вернет все публичные поля класса, но это для примера;

			Field fieldName = tc.getClass().getDeclaredField("name");	//Этот вернет все поля, включая private pritected;
			Field fieldNumber = tc.getClass().getDeclaredField("number");

			fieldName.setAccessible(true);
			fieldNumber.setAccessible(true);	// Этот метод разрешает нам дальнейшую работу с полем объекта;

			name = (String) fieldName.get(tc);	//присваеваем переменной значение приватного поля в объекте tc
			fieldNumber.set(tc, (int) 666); //присваеваем приватному полю в объекте новое значение
			tc.pringData();		//Sasha 666

			Method method = tc.getClass().getDeclaredMethod("printFields");	//Получаем нужный метод по имени доступа к нему
			method.setAccessible(true);	//Даем разрешение на доступ
			method.invoke(tc);	//вызываем метод у объекта	//Sasha 666
		} catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(number + name);	//777 Sasha
    }


}
