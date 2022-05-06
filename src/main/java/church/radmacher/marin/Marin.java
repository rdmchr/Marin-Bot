package church.radmacher.marin;

import church.radmacher.marin.commands.MessageListener;
import church.radmacher.marin.commands.PingCommand;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;


public class Marin {
    public static void main(String[] args) {
        try {
            CommandClientBuilder builder = new CommandClientBuilder();

            // set prefixes
            builder.setPrefix("!");
            builder.setAlternativePrefix("+");
            builder.setOwnerId(172726364900687872L);

            // add commands
            builder.addSlashCommand(new PingCommand());

            // force update for test server
            builder.forceGuildOnly(970439367715856384L);

            CommandClient client = builder.build();

            JDA api = JDABuilder.createDefault(Config.discToken).addEventListeners(client).build();
            api.getPresence().setPresence(Activity.competing("the daily struggle that is life"), false);
            api.addEventListener(new MessageListener());
        } catch (LoginException e) {
            System.out.println("Could not start bot. Check token.");
        }
    }
}
