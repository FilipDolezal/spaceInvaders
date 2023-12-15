package tp1.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import tp1.logic.Level;

public class InitialConfiguration {
	public static final InitialConfiguration NONE = new InitialConfiguration();
	private List<String> descriptions;
	private InitialConfiguration() {}
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}

	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException
	{
		FileReader fr = new FileReader(filename);
		Scanner sc = new Scanner(fr);

		List<String> list = new ArrayList<>();
		while(sc.hasNextLine()) list.add(sc.nextLine());

		return new InitialConfiguration(list);
	}
}
