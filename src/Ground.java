
public class Ground extends Block{
	
	public Ground(String filename, int y) {
			
			//String filename, int x, int y, int width, int height, int boost, int vel
			super(filename, 0, y, 600, 20, 0, 0);
			
		}
		public String toString() {
			return "Ground";
		}

}
