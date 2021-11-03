package h08_test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import h08.BadUpdateTimeException;
import h08.UpdateTimeBeforeLastUpdateException;
import h08.UpdateTimeInTheFutureException;

class H3_Test {
	
	@Test
	void testConstructorExistenceBadUpdateTimeException() {
		int exceptions = 0;
		try {
			Constructor constructor = BadUpdateTimeException.class.getDeclaredConstructor(Calendar.class, boolean.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			exceptions++;
		}
		try {
			Constructor constructor = BadUpdateTimeException.class.getDeclaredConstructor(boolean.class, Calendar.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			exceptions++;
		}
		assertTrue(exceptions < 2);
		
	}
	
	@Test
	void testConstructorContentBadUpdateTimeException() {
		Calendar[] calendars = createManyRandomCalendars();
		
		for (Calendar c : calendars) {
			assertTrue(new BadUpdateTimeException(c, true).getMessage().equals(Helper.createCorrectMessage(c, true)));
			assertTrue(new BadUpdateTimeException(c, false).getMessage().equals(Helper.createCorrectMessage(c, false)));
		}
	}
	
	@Test
	void testConstructorExistenceUpdateTimeBeforeLastUpdateException() {
		try {
			Constructor constructor = UpdateTimeBeforeLastUpdateException.class.getDeclaredConstructor(Calendar.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void testConstructorContentUpdateTimeBeforeLastUpdateException() {
		//wie testet man, dass auch wirklich der Konstruktor der Superklasse aufgerufen wird und nicht der String neu erzeugt wird?
		Calendar[] calendars = createManyRandomCalendars();
		
		for (Calendar c : calendars) {
			assertTrue(new UpdateTimeBeforeLastUpdateException(c).getMessage().equals(Helper.createCorrectMessage(c, true)));
		}
	}
	
	@Test
	void testConstructorExistenceUpdateTimeInTheFutureException() {
		try {
			Constructor constructor = UpdateTimeInTheFutureException.class.getDeclaredConstructor(Calendar.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void testConstructorContentUpdateTimeInTheFutureException() {
		//wie testet man, dass auch wirklich der Konstruktor der Superklasse aufgerufen wird und nicht der STring neu erzeugt wird?
		Calendar[] calendars = createManyRandomCalendars();
		
		for (Calendar c : calendars) {
			assertTrue(new UpdateTimeInTheFutureException(c).getMessage().equals(Helper.createCorrectMessage(c, false)));
		}
	}
	
	
	
	
	Calendar[] createManyRandomCalendars() {
		int nrOfTests = 100;
		
		Calendar[] calendars = new Calendar[nrOfTests];
		calendars[0] = Calendar.getInstance();
		for ( int i = 1; i< nrOfTests-1; i++) {
			calendars[i] = Calendar.getInstance();
			calendars[i].set(Calendar.YEAR, (int) (Math.random()*3000) + 1);
			calendars[i].set(Calendar.MONTH, (int) (Math.random()*12));
			calendars[i].set(Calendar.DAY_OF_MONTH, (int) (Math.random()*31) + 1);
			calendars[i].set(Calendar.HOUR_OF_DAY, (int) (Math.random()*24));
			calendars[i].set(Calendar.MINUTE, (int) (Math.random()*60));
			calendars[i].set(Calendar.SECOND, (int) (Math.random()*60));
			calendars[i].set(Calendar.MILLISECOND, (int) (Math.random()*1000));
			
		}
		calendars[nrOfTests-1] = Calendar.getInstance();
		
		return calendars;
	}

}
