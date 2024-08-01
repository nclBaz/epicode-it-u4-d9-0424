package riccardogulin;

import com.github.javafaker.Faker;
import riccardogulin.entities.User;

import java.util.*;
import java.util.function.Supplier;

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
/*
		System.out.println("-------------------- 1. Raggruppiamo gli user per città --------------------------");
		Map<String, List<User>> usersByCity = usersList.stream().filter(user -> user.getAge() >= 18).collect(Collectors.groupingBy(User::getCity));
		usersByCity.forEach((city, userList) -> System.out.println("Città: " + city + ", " + userList));

		// 2. Raggruppiamo gli user per età
		System.out.println("-------------------- 2. Raggruppiamo gli user per età --------------------------");
		Map<Integer, List<User>> usersByAge = usersList.stream().collect(Collectors.groupingBy(User::getAge));
		usersByAge.forEach((age, userList) -> System.out.println("Età: " + age + ", " + userList));

*/
/*		System.out.println("Utenti con 80 anni");
		usersByAge.get(80).forEach(user -> System.out.println(user));*//*


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

		// 7. Raggruppiamo per città e calcoliamo la medie delle età per ognuna di esse
		System.out.println("-------------------- 7. Raggruppiamo per città e calcoliamo la medie delle età --------------------------");
		Map<String, Double> averageAgePerCity = usersList.stream().collect(Collectors.groupingBy(User::getCity, Collectors.averagingInt(User::getAge)));
		averageAgePerCity.forEach((city, a) -> System.out.println("Città: " + city + ", " + a));

		// 8. Raggruppiamo per città ed otteniamo statistiche tipo media età, età più alta, più bassa, ecc ecc
		System.out.println("-------------------- 7. Raggruppiamo per città e calcoliamo la medie delle età --------------------------");
		Map<String, IntSummaryStatistics> statsPerCity = usersList.stream().collect(Collectors.groupingBy(User::getCity, Collectors.summarizingInt(User::getAge)));
		statsPerCity.forEach((city, stats) -> System.out.println("Città: " + city + ", " + stats));
*/


		// ******************************************************* COMPARATORS ***********************************************
		// 1. Ordiniamo la lista utenti per età (ordine crescente)
		System.out.println("-------------------- 1. Ordiniamo la lista utenti per età (ordine crescente) --------------------------");
		List<User> usersSortedByAge = usersList.stream().sorted(Comparator.comparingInt(User::getAge)).toList();
		usersSortedByAge.forEach(System.out::println);

		// 2. Ordiniamo la lista utenti per età (ordine decrescente)
		System.out.println("-------------------- 2. Ordiniamo la lista utenti per età (ordine decrescente) --------------------------");
		List<User> usersSortedByAgeDesc = usersList.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).toList();
		usersSortedByAgeDesc.forEach(System.out::println);

		// 3. Ordiniamo la lista utenti per cognome
		System.out.println("-------------------- 3. Ordiniamo la lista utenti per cognome --------------------------");
		List<User> usersSortedBySurname = usersList.stream().sorted(Comparator.comparing(User::getSurname)).toList();
		usersSortedBySurname.forEach(System.out::println);

		// ******************************************************* LIMIT ***********************************************
		// 1. Ottengo i 5 user più vecchi, tramite sorted li ordino per età decrescente, tramite limit ne conservo 5 sul totale,
		// Opzionalmente posso usare anche skip(5) per saltare tot elementi se volessi ottenere gli elementi dal sesto al decimo
		System.out.println("-------------------- 1. Ottengo i 5 user più vecchi --------------------------");
		List<User> fiveUsersSortedByAgeDesc = usersList.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).skip(5).limit(5).toList();
		fiveUsersSortedByAgeDesc.forEach(System.out::println);
	}
}
