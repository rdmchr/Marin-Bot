package church.radmacher.marin.commands.musicCommands;

import church.radmacher.marin.utils.musicUtils.AudioManagement;
import church.radmacher.marin.utils.musicUtils.AudioPlayerSendHandler;
import church.radmacher.marin.utils.musicUtils.TrackScheduler;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;

public class PlaySongCommand extends SlashCommand {
    private final AudioManagement audioManagement;
    public PlaySongCommand(AudioManagement audioManagement) {
        this.name = "play";
        this.help = "plays the requested song";
        this.audioManagement = audioManagement;


        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "song", "The Link of the song you want to play").setRequired(true));

        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        OptionMapping option = event.getOption("song");
        if (option == null || !event.isFromGuild()) {
            return;
        }
        Guild guild = event.getGuild();
        AudioChannel voiceChannel = event.getMember().getVoiceState().getChannel();
        AudioManager audioManager = guild.getAudioManager();

        audioManager.setSendingHandler(audioManagement.playSong(option.getAsString()));
        audioManager.openAudioConnection(voiceChannel);
        audioManager.setSelfDeafened(true);
    }
}
