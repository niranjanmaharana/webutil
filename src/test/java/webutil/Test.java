package webutil;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		// String[] filenames = new String[]{"Cisco_WebEx_Add-On.exe", "npp.7.5.6.Installer.x64.exe", "Postman-win64-6.1.3-Setup.exe"};
		// deleteFile(filenames);

		int i = 5;
		i += 3;
		System.out.println(i);
	}

	public static void deleteFile(String[] filenames) {
		for (String fileName : filenames) {
			File file = new File("C:\\Users\\AF83580\\Downloads\\" + fileName);
			if (file.delete()) {
				System.out.println(fileName + " File deleted successfully");
			} else {
				System.out.println(fileName + " Failed to delete the file");
			}
			System.out.println(fileName + (file.exists() ? " Exists" : "Not exist"));
		}
	}
}