package Control;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class TestWWControl {
	
	WWControl wwc;

	@Before
	public void setUp() throws Exception {
		wwc=new WWControl();
	}

	@Test
	public void testWaitTime() {
		wwc.setWaitTime(12.5);
		double d=wwc.getWaitTime();
		
		Assert.assertEquals(12.5, wwc.getWaitTime(),0);
	}
	
	
	

}
