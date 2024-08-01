package riccardogulin;

import com.github.javafaker.Faker;
import riccardogulin.entities.User;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

	public static void main(String[] args) {

		Supplier<User> userSupplier = () -> {
			Faker f = new Faker(Locale.ITALY);
			Random rnd = new Random();
			return new User(f.lordOfTheRings().character(), f.name().lastName(), rnd.nextInt(0, 100), f.lordOfTheRings().location());
		};

		List<User> usersList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			usersList.add(userSupplier.get());
		}

		usersList.forEach(System.out::println);

		// ******************************************************* COLLECTORS ***********************************************
		// 1. Raggruppiamo gli user per città
		System.out.println("-------------------- 1. Raggruppiamo gli user per città --------------------------");
		Map<String, List<User>> usersByCity = usersList.stream().filter(user -> user.getAge() >= 18).collect(Collectors.groupingBy(User::getCity));
		usersByCity.forEach((city, userList) -> System.out.println("Città: " + city + ", " + userList));

		// 2. Raggruppiamo gli user per età
		System.out.println("-------------------- 2. Raggruppiamo gli user per età --------------------------");
		Map<Integer, List<User>> usersByAge = usersList.stream().collect(Collectors.groupingBy(User::getAge));
		usersByAge.forEach((age, userList) -> System.out.println("Età: " + age + ", " + userList));

/*		System.out.println("Utenti con 80 anni");
		usersByAge.get(80).forEach(user -> System.out.println(user));*/

		// 3. Concateniamo tutti i nomi e cognomi degli user ottenendo una stringa tipo "aldo baglio, giovanni storti, giacomo poretti,..."
		System.out.println("-------------------- 3. Concateniamo tutti i nomi e cognomi --------------------------");
		String nomiECognomi = usersList.stream().map(user -> user.getName() + " " + user.getSurname()).collect(Collectors.joining(", "));
		System.out.println(nomiECognomi);

		// 4. Concateniamo tutte le età
		System.out.println("-------------------- 4. Concateniamo tutte le età --------------------------");
		String etàConcatenate = usersList.stream().map(user -> "" + user.getAge()).collect(Collectors.joining(", "));
		System.out.println(etàConcatenate);

		// 5. Calcolo la media delle età
		System.out.println("-------------------- 5. Calcolo la media delle età --------------------------");
		double average = usersList.stream().collect(Collectors.averagingInt(User::getAge));
		System.out.println("La media delle età è: " + average);

		// 6. Calcolo la somma delle età
		System.out.println("-------------------- 6. Calcolo la somma delle età --------------------------");

		int sum = usersList.stream().collect(Collectors.summingInt(User::getAge));
		System.out.println("La somma delle età è: " + sum);

	}
}
