package com.discord.org.webrtc.audio;

import android.media.AudioManager;
import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.discord.org.webrtc.Logging;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
class VolumeLogger {
    private static final String TAG = "VolumeLogger";
    private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
    private static final int TIMER_PERIOD_IN_SECONDS = 30;
    private final AudioManager audioManager;
    private Timer timer;

    public class LogVolumeTask extends TimerTask {
        private final int maxRingVolume;
        private final int maxVoiceCallVolume;

        public LogVolumeTask(int i10, int i11) {
            this.maxRingVolume = i10;
            this.maxVoiceCallVolume = i11;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            int mode = VolumeLogger.this.audioManager.getMode();
            if (mode == 1) {
                Logging.d(VolumeLogger.TAG, c.s("STREAM_RING stream volume: ", VolumeLogger.this.audioManager.getStreamVolume(2), " (max=", this.maxRingVolume, ")"));
            } else if (mode == 3) {
                Logging.d(VolumeLogger.TAG, c.s("VOICE_CALL stream volume: ", VolumeLogger.this.audioManager.getStreamVolume(0), " (max=", this.maxVoiceCallVolume, ")"));
            }
        }
    }

    public VolumeLogger(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public void start() {
        Logging.d(TAG, TtmlNode.START + WebRtcAudioUtils.getThreadInfo());
        if (this.timer != null) {
            return;
        }
        Logging.d(TAG, "audio mode is: " + WebRtcAudioUtils.modeToString(this.audioManager.getMode()));
        Timer timer = new Timer(THREAD_NAME);
        this.timer = timer;
        timer.schedule(new LogVolumeTask(this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0L, 30000L);
    }

    public void stop() {
        Logging.d(TAG, "stop" + WebRtcAudioUtils.getThreadInfo());
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }
}
