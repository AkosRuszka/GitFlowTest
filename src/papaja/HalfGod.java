package papaja;

public class HalfGod implements CallGod {
	private int speed;
	private String name;
	
	public HalfGod(String name, int speed) {
		this.speed = speed;
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public String getName() {
		return name;
	}
}
