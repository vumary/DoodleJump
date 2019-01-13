
public class Background extends Block{
	
	public Background(String filename, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, (int)(Math.random() * 600), y, 50, 20, 100, 0);
	}
	
}
