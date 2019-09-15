package holeFiller;

import java.util.Collection;

import primitives.Pixel;

public interface IholeFinder {
	
	public Collection<Pixel> getBoundary();

	public Collection<Pixel> getHole();
}
