package church.radmacher.marin;

public class Config {
    public static String discToken = System.getenv("DISCORD_TOKEN");
    public static String DOCKER_HOST = "tcp://localhost:2375";
    public static boolean DOCKER_TSL = false;
}
