package h08_test;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import h08.BadUpdateTimeException;
import h08.TimeStamp;
import h08.UpdateTimeBeforeLastUpdateException;
import h08.UpdateTimeInTheFutureException;

class H3_Definition_Test {

	@BeforeEach
	void checkClassBadUpdateTimeException() throws ClassNotFoundException {
		Class.forName("h08.BadUpdateTimeException");


		// not abstract
		assertFalse(isAbstract(Class.forName("h08.BadUpdateTimeException").getModifiers()));
		//is public
		assertTrue(isPublic(Class.forName("h08.BadUpdateTimeException").getModifiers()));
		//superclass correct
		assertTrue(Class.forName("h08.BadUpdateTimeException").getSuperclass().equals(Exception.class));
	}


	@BeforeEach
	void checkClassUpdateTimeBeforeLastUpdateException() throws ClassNotFoundException {
		Class.forName("h08.UpdateTimeBeforeLastUpdateException");


		// not abstract
		assertFalse(isAbstract(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()));
		//is public
		assertTrue(isPublic(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()));
		//superclass correct
		assertTrue(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getSuperclass().equals(BadUpdateTimeException.class));
	}



	@BeforeEach
	void checkClassUpdateTimeInTheFutureException() throws ClassNotFoundException {
		Class.forName("h08.UpdateTimeInTheFutureException");


		// not abstract
		assertFalse(isAbstract(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()));
		//is public
		assertTrue(isPublic(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()));
		//superclass correct
		assertTrue(Class.forName("h08.UpdateTimeInTheFutureException").getSuperclass().equals(BadUpdateTimeException.class));
	}

	@Test
	void checkClasses() {}



}
