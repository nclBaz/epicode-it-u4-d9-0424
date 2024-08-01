package riccardogulin.files;

import org.apache.commons.io.FileUtils;
import riccardogulin.entities.User;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FilesMain {
	public static void main(String[] args) {
		File file = new File("src/info.txt"); // <-- Creo un riferimento al percorso dove starà il file

		try {
			User aldo = new User("Aldo", "Baglio", 20, "NEW YORK");
			FileUtils.writeStringToFile(file, aldo.getName() + "-" + aldo.getSurname() + "-" + aldo.getAge() + "-" + aldo.getCity() + System.lineSeparator(), StandardCharsets.UTF_8, true);
			// System.lineSeparator() serve per restituire il carattere 'a capo', carattere che varia a seconda del sistema operativo che stiamo usando

			String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			// Se splitto il contenuto per righe ottengo i singoli user come elementi di un array di stringhe
			String[] contentAsArray = content.split(System.lineSeparator());
			System.out.println(Arrays.toString(contentAsArray));
			// Se poi per ogni user splitto per il separatore '-' ottengo un array di stringhe con tutti i dati dell'utente
			// Poi posso usare quell'array per creare un oggetto ->	User fromDb = new User(arr[0], arr[1], arr[2], arr[3]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
