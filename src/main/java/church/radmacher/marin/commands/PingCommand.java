package church.radmacher.marin.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class PingCommand extends SlashCommand {

    public PingCommand() {
        this.name = "ping"; // has to be lowercase
        this.help = "Ping an IP-Address or domain name.";

        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "domain", "The domain you want to ping.").setRequired(true));

        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        OptionMapping option = event.getOption("domain"); // must match above configuration
        if (option == null) {
            return;
        }
        try {
            InetAddress ipAddress = InetAddress.getByName(option.getAsString());
            event.reply(String.format("IP Address of *%s* is `%s`", option.getAsString(), ipAddress.getHostAddress())).queue();
        } catch (Exception e) {
            event.reply("Could not find domain address.").queue();
        }
    }
}