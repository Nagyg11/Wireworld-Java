package Data;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestWWData {
	WWDataActual wwDat;
	Random rand;

	@Before
	public void setUp() throws Exception {
		wwDat=new WWDataActual(10,20);
		rand=new Random();
	}

	@Test
	public void testWWDataConstructorColumnRowNum() {
		Assert.assertEquals(10, wwDat.getColumnNum());
		Assert.assertEquals(20, wwDat.getRowNum());
	}
	
	@Test
	public void testWWDataSetGetXY() {
		int x=rand.nextInt(10);
		int y=rand.nextInt(20);
		int value=rand.nextInt(4);
		
		wwDat.setXY(x, y, value);
		Assert.assertEquals(value, wwDat.getXY(x, y));
		
	}
	
	@Test
	public void testWWDataReset() {
		for(int x=0; x<10;x++) {
			for(int y=0;y<20;y++) {
				wwDat.setXY(x,y,rand.nextInt(4));
			}
		}
		wwDat.resetWireWorldMatrix();
		
		for(int x=0; x<10;x++) {
			for(int y=0;y<20;y++) {
				Assert.assertEquals(0, wwDat.getXY(x, y));
			}
		}
	}
	
	@Test
	public void testWWDataCopy() {
		for(int x=0; x<10;x++) {
			for(int y=0;y<20;y++) {
				wwDat.setXY(x,y,rand.nextInt(4));
			}
		}
		
		WWData copiedData=new WWData(10,20);
		
		copiedData.copy(wwDat);
		
		for(int x=0; x<10;x++) {
			for(int y=0;y<20;y++) {
				Assert.assertEquals(wwDat.getXY(x, y), copiedData.getXY(x, y));
			}
		}
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testNotSameSizeCopy() throws IndexOutOfBoundsException{
		WWData copiedData=new WWData(10,10);
		copiedData.copy(wwDat);
	}

}
