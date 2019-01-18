package pinya;

public class Keyword {

	private String name;;
	private double Weight;

	public Keyword(String name, double Weight) {

		this.name = name;
		this.Weight = Weight;

	}

	public String getName() {

		return name;
	}

	public double getWeight() {

		return Weight;
	}

	public String toString() {

		return "[" + name + "," + Weight + "]";
	}
}
