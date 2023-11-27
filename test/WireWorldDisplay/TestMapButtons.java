package WireWorldDisplay;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMapButtons {
	MapButton mb;
	
	@Before
	public void setUp() throws Exception {
		mb=new MapButton();
	}

	@Test
	public void testHoverStatus() {
		mb.setHoverStatus();
		Assert.assertEquals(3,mb.getStatus());
	}
	
	@Test
	public void testClearStatus() {
		mb.setClearStatus();
		Assert.assertEquals(0,mb.getStatus());
	}
	
	@Test
	public void testChangeBetweenStatus() {
		mb.setStatus(0);
		mb.changeBetweenStatus();
		Assert.assertEquals(3,mb.getStatus());
		
		mb.changeBetweenStatus();
		Assert.assertEquals(1,mb.getStatus());
		
		mb.changeBetweenStatus();
		Assert.assertEquals(2,mb.getStatus());
		
		mb.changeBetweenStatus();
		Assert.assertEquals(3,mb.getStatus());
	}

}
