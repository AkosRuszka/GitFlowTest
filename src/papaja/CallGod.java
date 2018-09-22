package papaja;

import java.util.Collection;

public interface CallGod {
	default void printGod(Object o) {
		printM(o);
	}
	
	default void printGods(Collection<HalfGod> o) {
		o.forEach(this::printM);
	}
	
	private void printM(Object o) {
		System.out.println("A félistenek kemények. Véged!");
		System.out.println("Félisten neve:" + ((HalfGod)o).getName());
		System.out.println("Félisten ilyen sebességgel fut: "+((HalfGod)o).getSpeed());
	}
}
