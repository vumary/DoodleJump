
public class Monster extends Block{
	
	public Monster(String filename, int y) {
			
			//String filename, int x, int y, int width, int height, int boost, int vel
			super(filename, (int)(Math.random() * 600), y, 60, 60, -100, 8);
			
		}
		public String toString() {
			return "Monster";
		}

}
