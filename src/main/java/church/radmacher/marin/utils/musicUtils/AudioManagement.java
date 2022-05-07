package church.radmacher.marin.utils.musicUtils;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

public class AudioManagement {
    private final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private final TrackScheduler trackScheduler = new TrackScheduler();
    private AudioPlayer player;

    public AudioManagement() {
        AudioSourceManagers.registerRemoteSources(playerManager);
        this.player = playerManager.createPlayer();
        player.addListener(trackScheduler);
    }

    public AudioPlayerSendHandler playSong(String identifier) {
        playerManager.loadItem(identifier, new LoadingHandler(trackScheduler));
        try {
            Thread.sleep(20);
        } catch(Exception e) {}
        trackScheduler.playNextTrack(player);
        return new AudioPlayerSendHandler(player);
    }
}
