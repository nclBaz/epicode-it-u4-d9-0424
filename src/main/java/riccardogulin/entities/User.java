package riccardogulin.entities;


import riccardogulin.exceptions.StringNotValidException;

public class User implements Comparable<User> {
	private String name;
	private String surname;
	private int age;
	private String city;

	public User(String name, String surname, int age, String city) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws StringNotValidException {
		// Throws è l'alternativa che abbiamo al try-catch. Serve per informare il chiamante
		// (chi invoca il metodo) che il metodo potrebbe in alcune situazioni lanciare questa eccezione
		// CHECKED. E' un po' come delegare la gestione del problema a chi chiama il metodo
		if (name.length() < 3) throw new StringNotValidException(name);
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", age='" + age + '\'' +
				", city='" + city + '\'' +
				'}';
	}

	@Override
	public int compareTo(User o) { // Metodo utilizzato nei TreeSet
		return this.name.compareTo(o.name);
	}
}
