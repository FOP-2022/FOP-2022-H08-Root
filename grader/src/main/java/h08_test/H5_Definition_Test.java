package h08_test;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import h08.TestTimeStampExceptions;
import h08.TimeStamp;

class H5_Definition_Test {

	@BeforeEach
	void classExistence() throws ClassNotFoundException{
		Class.forName("h08.TestTimeStampExceptions");
		
		
		// not abstract
		assertFalse(isAbstract(Class.forName("h08.TimeStamp").getModifiers()));
		//is public
		assertTrue(isPublic(Class.forName("h08.TimeStamp").getModifiers()));
		
		
	}
	
	@ParameterizedTest(name = "testCatch{index}")
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void testExistenceTestCatch(int nr) {
		String methodName = "testCatch" + nr;
		
		Method[] methods = TestTimeStampExceptions.class.getDeclaredMethods();
		boolean hasMethod = false;
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				assertTrue(isPublic(m.getModifiers()));
				assertFalse(isStatic(m.getModifiers()));
				assertTrue(void.class.equals(m.getReturnType()));
				
				Parameter[] parameter = m.getParameters();
				boolean containsParam = false;
				Object[] returnClasses = {Calendar.class, TimeStamp.class, int.class};
				for (Parameter p : parameter) {
					for (Object retClass : returnClasses) {
						if (retClass.equals(p.getType()))
							containsParam = true;
					}
					assertTrue(containsParam);
					containsParam = false;
				}
				
				assertTrue(parameter.length == 3);
				hasMethod = true;
			}
		}
		assertTrue(hasMethod);
		
	}

}
