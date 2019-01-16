public class CoolBlock extends Block {
	
	public CoolBlock(String filename, int y) {
		//String filename, int x, int y, int width, int height, int boost, int vel
		super(filename, (int)(Math.random() * 600), y, 40, 53, 60, 8);
	}

	//change boost or something
	
	public String toString() {
		return "CoolBlock";
	}
}
