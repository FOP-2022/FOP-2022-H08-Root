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
		try {
			Class.forName("h08.BadUpdateTimeException");
		} catch (ClassNotFoundException e) {
			fail("class BadUpdateTimeException does not exist");
		}



		// not abstract
		assertFalse(isAbstract(Class.forName("h08.BadUpdateTimeException").getModifiers()), "class BadUpdateTimeException is abstract");
		//is public
		assertTrue(isPublic(Class.forName("h08.BadUpdateTimeException").getModifiers()), "class BadUpdateTimeException is not public");
		//superclass correct
		assertTrue(Class.forName("h08.BadUpdateTimeException").getSuperclass().equals(Exception.class), "class BadUpdateTimeException does not extend class Exception");
	}


	@BeforeEach
	void checkClassUpdateTimeBeforeLastUpdateException() throws ClassNotFoundException {
		try {
			Class.forName("h08.UpdateTimeBeforeLastUpdateException");
		} catch (ClassNotFoundException e) {
			fail("class UpdateTimeBeforeLastUpdateException does not exist");
		}



		// not abstract
		assertFalse(isAbstract(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()), "class UpdateTimeBeforeLastUpdateException is abstract");
		//is public
		assertTrue(isPublic(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getModifiers()), "class UpdateTimeBeforeLastUpdateException is not public");
		//superclass correct
		assertTrue(Class.forName("h08.UpdateTimeBeforeLastUpdateException").getSuperclass().equals(BadUpdateTimeException.class), "class UpdateTimeBeforeLastUpdateException does not extend class BadUpdateTimeException");
	}



	@BeforeEach
	void checkClassUpdateTimeInTheFutureException() throws ClassNotFoundException {
		try {
			Class.forName("h08.UpdateTimeInTheFutureException");
		} catch (ClassNotFoundException e) {
			fail("class UpdateTimeInTheFutureException does not exist");
		}



		// not abstract
		assertFalse(isAbstract(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()), "class UpdateTimeInTheFutureException is abstract");
		//is public
		assertTrue(isPublic(Class.forName("h08.UpdateTimeInTheFutureException").getModifiers()), "class UpdateTimeInTheFutureException is not public");
		//superclass correct
		assertTrue(Class.forName("h08.UpdateTimeInTheFutureException").getSuperclass().equals(BadUpdateTimeException.class), "class UpdateTimeInTheFutureException does not extend class BadUpdateTimeException");
	}

	@Test
	void checkClasses() {}



}
