package vampire.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import vampire.Game;
import vampire.graphics.Sprite;
import vampire.level.Level;

public class MapLydzjeLoader {

	public static void loadLydzm(String fileName, Level level) {
		FileReader isr = null;
		File objFile = new File("/maps/" + fileName + ".lydzm");

		try {
			isr = new FileReader(objFile);
		} catch (FileNotFoundException e) {
			System.err.println("File not found in res; don't use any extention");
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(isr);
		String line;

		try {
			while (true) {
				line = reader.readLine();
				if (line.startsWith("z0 ")) {
					String[] currentLine = line.split(" ");
					String type = currentLine[1];
					int amount = Integer.parseInt(currentLine[2]);
					char loopMode = currentLine[3].charAt(0);
					int x = Integer.parseInt(currentLine[3]);
					int y = Integer.parseInt(currentLine[4]);
					int space = Integer.parseInt(currentLine[5]);

					create(level, type, amount, loopMode, x, y, space);
				}

			}
		} catch (Exception e) {
			System.err.println("Error reading the file");
			e.printStackTrace();
		}
	}

	public static void create(Level level, String type, int amount, char loopMode, int x, int y, int space) {
		if (type.equals("tree")) {
			if (loopMode == 'd') {
				for (int i = 0; i < amount; i++) {
					int aX = (x + Game.random.nextInt(space) + 1) * 32;
					int aY = y * 32;
					if (level.getCamera().contains(aX, aX + Sprite.tree.width)) {
						// Screen.renderSprite(aX,aY,Sprite.tree,true);
					}
				}
			} else if (loopMode == 's') {
				for (int i = 0; i < amount; i++) {
					int aX = (x + space) * 32;
					int aY = y * 32;
					if (level.getCamera().contains(aX, aX + Sprite.tree.width)) {
						// Screen.renderSprite(aX,aY,Sprite.tree,true);
					}
				}
			}
		}
	}

}
