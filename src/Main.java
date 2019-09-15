import java.util.Scanner;

import org.opencv.core.Core;
import holeFiller.DefaultHoleFiller;
import holeFiller.IholeFiller;
import holeFillerHelpers.DefaultWeightFunction;
import holeFillerHelpers.EightConnected;
import holeFillerHelpers.FourConnected;
import holeFillerHelpers.Iconnected;

public class Main {

	public static void main(String[] args) throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the full path of the image: ");
		String imagePath = scanner.next();

		System.out.println("Enter the level of the pixel connectivity (4 or 8): ");
		int connectivity = scanner.nextInt();

		Iconnected connections = null;
		switch (connectivity) {
		case 4:
			connections = new FourConnected();
			break;

		case 8:
			connections = new EightConnected();
			break;
		}

		System.out.println("Enter the parameter epsilon(double value): ");
		double epsilon = scanner.nextDouble();

		System.out.println("Enter the parameter z (double value): ");
		double z = scanner.nextDouble();

		IholeFiller holeFiller=new DefaultHoleFiller(new DefaultWeightFunction(epsilon,z));
		PictureFixer pf = new PictureFixer(imagePath, connections,holeFiller);
		pf.fillTheHole();
		scanner.close();

	}

}
