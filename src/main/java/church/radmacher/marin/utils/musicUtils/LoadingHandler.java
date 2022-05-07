package church.radmacher.marin.utils.musicUtils;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;


public class LoadingHandler implements AudioLoadResultHandler {
    private final TrackScheduler trackScheduler;

    public LoadingHandler(TrackScheduler trackScheduler) {
        this.trackScheduler = trackScheduler;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        trackScheduler.queue(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        for (AudioTrack track : playlist.getTracks()) {
            trackScheduler.queue(track);
        }
    }

    @Override
    public void noMatches() {
        //notFound
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        //everything died
    }
}
