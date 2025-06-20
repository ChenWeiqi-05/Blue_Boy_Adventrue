package main;

import java.io.*;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // full screen
            if (gp.fullScreenOn == true) {
                bw.write("On");
            }
            if (gp.fullScreenOn == false) {
                bw.write("Off");
            }

            bw.newLine();// new line

            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();// new line

            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();// new line

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() throws IOException {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            if (s.equals("On")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("Off")) {
                gp.fullScreenOn = false;
            }
            //Music Volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);
//se Volume
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
