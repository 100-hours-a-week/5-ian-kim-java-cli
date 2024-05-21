package util;

public class AsciiArt {
    public static void mainLogo() {
        try {
            // Wait for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String title = "\n" +
                " ______                         __                         ____     __  __      ____      \n" +
                "/\\__  _\\                       /\\ \\                       /\\  _`\\  /\\ \\/\\ \\    /\\  _`\\    \n" +
                "\\/_/\\ \\/       __       ___    \\ \\/      ____             \\ \\ \\L\\ \\\\ \\ \\ \\ \\   \\ \\ \\L\\ \\  \n" +
                "   \\ \\ \\     /'__`\\   /' _ `\\   \\/      /',__\\             \\ \\ ,__/ \\ \\ \\ \\ \\   \\ \\  _ <' \n" +
                "    \\_\\ \\__ /\\ \\L\\.\\_ /\\ \\/\\ \\         /\\__, `\\             \\ \\ \\/   \\ \\ \\_\\ \\   \\ \\ \\L\\ \\\n" +
                "    /\\_____\\\\ \\__/.\\_\\\\ \\_\\ \\_\\        \\/\\____/              \\ \\_\\    \\ \\_____\\   \\ \\____/\n" +
                "    \\/_____/ \\/__/\\/_/ \\/_/\\/_/         \\/___/                \\/_/     \\/_____/    \\/___/ \n" +
                "                                                                                          \n" +
                "                                                                                          \n";
        System.out.println(title);
    }

    public static void menuLogo() {
        String asciiArt = """
                                
                                
                .___  ___.  _______ .__   __.  __    __ \s
                |   \\/   | |   ____||  \\ |  | |  |  |  |\s
                |  \\  /  | |  |__   |   \\|  | |  |  |  |\s
                |  |\\/|  | |   __|  |  . `  | |  |  |  |\s
                |  |  |  | |  |____ |  |\\   | |  `--'  |\s
                |__|  |__| |_______||__| \\__|  \\______/ \s
                                
                                
                """;
        System.out.println(asciiArt);
    }
}
