package papaja;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test{

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		long start , stop;

		// Fájlból beolvasás (JDK8)
		try {
			Path path = Paths.get(Test.class.getClassLoader().getResource("resources/text.txt").toURI());

			Stream<String> stream = Files.lines(path);
			// Széttördeljük a fáljt (" , ") mentén
			list = Arrays.asList(stream.iterator().next().split("\" , \""));

			stream.close();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}


		// Stream nélkül (standard)
		List<String> ka = new ArrayList<>();
		list.forEach(act -> {
			if(act.contains("a"))
				ka.add(act.toUpperCase());
		});

		/* Stream használatával
			Az eredmény halmazt az eve-be tároljuk el. A list-re meghívjuk a stream()-et, ezzel alakítjuk át azzá,
			ezután használható a stream műveletei. Elsőként a filtert használjuk amely megvizsgálja hogy a stream-ben található elemek
			melyikére igaz az hogy (ebben az esetben) tartalmazza az "a" betűt. Az így kigyűjtött elemekre folyik tovább a "műveletsor".
			A map() hívással átalakítjuk a beérkező elemeket map(x -> x.toUpperCase) -nek felel meg, csak függvény referenciával van leírva.
			Az átalakítás után a collect hívásnak átadunk egy Collectort amivel újra valamilyen tárolóvá alakítjuk az eredményhalmazt.
		 */
		List<String> eve = list.stream()
				.filter(i -> i.contains("a"))
				.map(String::toUpperCase)
				.collect(Collectors.toList());


		// Következő példa ---------------------------------------------------------------
		// Összeszámolni a hallgatók átlag életkorát

		List<Student> students = new ArrayList<>();

		// Alap megoldás -------
		int boysSum = 0;
		int boysCount = 0;
		for(Student s : students) {
			if(s.gender == Gender.BOY) {
				boysSum += s.age;
				boysCount++;
			}
		}
		//System.out.println("Átlag kor: " + (boysSum/boysCount));
		// Alap megoldás vége --------

		// Stream megoldás
		System.out.println("Átlag kor: " + students.stream()
				.filter(x -> x.gender == Gender.BOY)
				.mapToInt(x -> x.age)
				.average());
		// Stream megoldás vége --------

		// Build pattern, fluid interface
		System.out.println("Átlag kor: " + students.stream()
				.collect(Collectors.groupingBy(Student::getGender, Collectors.summarizingInt(Student::getAge))));
		// vége ---------

		final int BEGIN = 0;
		final int END = 100;
		List<Integer> list1 = new ArrayList<>();
		start = System.currentTimeMillis();
		IntStream.range(BEGIN,END).forEach(i -> list1.add(i*i*i));
		stop  = System.currentTimeMillis();
		//System.out.println("Eltelt idő: " + (stop-start));
		list1.clear();
		start = System.currentTimeMillis();
		IntStream.range(BEGIN,END).parallel().forEach(i -> list1.add(i*i*i));
		stop = System.currentTimeMillis();
		//System.out.println("Eltelt idő: " + (stop-start));
	}

	private static final class Student {
		private int age;
		private Gender gender;

		public Gender getGender() { return gender; }

		public int getAge() { return age; }

		//...

	}

	enum Gender { GIRL, BOY };

}
