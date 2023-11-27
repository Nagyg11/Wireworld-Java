package Run;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Data.WWDataActual;

public class TestStatusChanger {
	
	WWDataActual wwDat;
	StatusChanger sc;
	

	@Before
	public void setUp() throws Exception {
		wwDat=new WWDataActual(10,20);
		sc=new StatusChanger();
	}
	
	@Test
	public void testNextStatus0to0() {
		sc.oneStep(wwDat);
		for(int x=0; x<10;x++) {
			for(int y=0;y<20;y++) {
				Assert.assertEquals(0, wwDat.getXY(x, y));
			}
		}
	}
	
	@Test
	public void testNextStatus1to2() {
		wwDat.setXY(2, 2, 1);
		sc.oneStep(wwDat);
		Assert.assertEquals(2, wwDat.getXY(2, 2));
	}
	
	@Test
	public void testNextStatus2to3() {
		wwDat.setXY(3, 3, 2);
		sc.oneStep(wwDat);
		Assert.assertEquals(3, wwDat.getXY(3, 3));
	}
	
	@Test
	public void testNextStatus3to1() {
		wwDat.setXY(2, 2, 1);
		wwDat.setXY(1, 1, 3);
		sc.oneStep(wwDat);
		Assert.assertEquals(1, wwDat.getXY(1, 1));
		
		//Ne változzon meg mivel több mint 2 kék van közelében.
		wwDat.resetWireWorldMatrix();
		wwDat.setXY(2, 2, 1);
		wwDat.setXY(2, 1, 1);
		wwDat.setXY(0, 0, 1);
		wwDat.setXY(1, 1, 3);
		sc.oneStep(wwDat);
		Assert.assertEquals(3, wwDat.getXY(1, 1));
	}
	
	@Test
	public void testOneStep() {
		Assert.assertFalse(sc.oneStep(wwDat));

		Random rand=new Random();
		wwDat.setXY(rand.nextInt(10), rand.nextInt(20), 3);
		Assert.assertFalse(sc.oneStep(wwDat));
		
		wwDat.resetWireWorldMatrix();
		wwDat.setXY(rand.nextInt(10), rand.nextInt(20), 1);
		Assert.assertTrue(sc.oneStep(wwDat));
		
		wwDat.resetWireWorldMatrix();
		wwDat.setXY(rand.nextInt(10), rand.nextInt(20), 2);
		Assert.assertTrue(sc.oneStep(wwDat));
		
		
	}

}
