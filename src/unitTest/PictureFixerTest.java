package unitTest;

import org.junit.Test;
import org.opencv.core.Core;

import holeFiller.HoleFinder;
import holeFiller.DefaultHoleFiller;
import holeFiller.IholeFiller;
import holeFiller.LinearHoleFiller;
import holeFillerHelpers.DefaultWeightFunction;
import holeFillerHelpers.EightConnected;
import holeFillerHelpers.FourConnected;
import holeFillerHelpers.Iconnected;
import holeFillerHelpers.IweightFunction;
import imageLoader.ImageConvertor;

public class PictureFixerTest {
	public PictureFixerTest() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private final String IMAGE_PATH = System.getProperty("user.dir") + "//src//pictures//";
	private final String test1=IMAGE_PATH+"test1.jpg";
	private final String test2=IMAGE_PATH+"test2.jpg";
	private final String test3=IMAGE_PATH+"test3.jpg";
	private final String test4=IMAGE_PATH+"test4.jpg";
	private final String test5=IMAGE_PATH+"test5.jpg";
	
	private Iconnected connectedFour=new FourConnected();
	private Iconnected connectedEight=new EightConnected();
	
	double epsilon=0.00001;
	double z=5;
	double z1=10;
	private IweightFunction dWeightFunction=new DefaultWeightFunction(epsilon,z);
	
	private IholeFiller dHoleFiller=new DefaultHoleFiller(dWeightFunction);
	private IholeFiller eightHoleFiller=new LinearHoleFiller(connectedEight);
	private IholeFiller fourHoleFiller=new LinearHoleFiller(connectedFour);
	
	@Test
	public void fillTheHoleTest1() throws Exception
	{
		ImageConvertor imageToArr = new ImageConvertor(test1);
		double[][] image = imageToArr.convertImageToArr();
		HoleFinder boundary = new HoleFinder(image, connectedEight);
		image = dHoleFiller.holeFiller(image, boundary);
		imageToArr.convertArrToImage(image);
	}
	
	@Test
	public void fillTheHoleTest2() throws Exception
	{
		ImageConvertor imageToArr = new ImageConvertor(test2);
		double[][] image = imageToArr.convertImageToArr();
		HoleFinder boundary = new HoleFinder(image, connectedFour);
		image = eightHoleFiller.holeFiller(image, boundary);
		imageToArr.convertArrToImage(image);
	}
	
	
	@Test
	public void fillTheHoleTest3() throws Exception
	{
		ImageConvertor imageToArr = new ImageConvertor(test3);
		double[][] image = imageToArr.convertImageToArr();
		HoleFinder boundary = new HoleFinder(image, connectedEight);
		image = fourHoleFiller.holeFiller(image, boundary);
		imageToArr.convertArrToImage(image);
	}
	
	@Test
	public void fillTheHoleTest4() throws Exception
	{
		ImageConvertor imageToArr = new ImageConvertor(test4);
		double[][] image = imageToArr.convertImageToArr();
		HoleFinder boundary = new HoleFinder(image, connectedFour);
		image = dHoleFiller.holeFiller(image, boundary);
		imageToArr.convertArrToImage(image);
	}
	
	@Test
	public void fillTheHoleTest5() throws Exception
	{
		ImageConvertor imageToArr = new ImageConvertor(test5);
		double[][] image = imageToArr.convertImageToArr();
		HoleFinder boundary = new HoleFinder(image, connectedEight);
		image = dHoleFiller.holeFiller(image, boundary);
		imageToArr.convertArrToImage(image);
	}
	

}
