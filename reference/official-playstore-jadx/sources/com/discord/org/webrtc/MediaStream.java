package com.discord.org.webrtc;

import androidx.fragment.app.a2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MediaStream {
    private static final String TAG = "MediaStream";
    private long nativeStream;
    public final List<AudioTrack> audioTracks = new ArrayList();
    public final List<VideoTrack> videoTracks = new ArrayList();
    public final List<VideoTrack> preservedVideoTracks = new ArrayList();

    public MediaStream(long j10) {
        this.nativeStream = j10;
    }

    private void checkMediaStreamExists() {
        if (this.nativeStream == 0) {
            throw new IllegalStateException("MediaStream has been disposed.");
        }
    }

    private static native boolean nativeAddAudioTrackToNativeStream(long j10, long j11);

    private static native boolean nativeAddVideoTrackToNativeStream(long j10, long j11);

    private static native String nativeGetId(long j10);

    private static native boolean nativeRemoveAudioTrack(long j10, long j11);

    private static native boolean nativeRemoveVideoTrack(long j10, long j11);

    private static void removeMediaStreamTrack(List<? extends MediaStreamTrack> list, long j10) {
        Iterator<? extends MediaStreamTrack> it = list.iterator();
        while (it.hasNext()) {
            MediaStreamTrack next = it.next();
            if (next.getNativeMediaStreamTrack() == j10) {
                next.dispose();
                it.remove();
                return;
            }
        }
        Logging.e(TAG, "Couldn't not find track");
    }

    public void addNativeAudioTrack(long j10) {
        this.audioTracks.add(new AudioTrack(j10));
    }

    public void addNativeVideoTrack(long j10) {
        this.videoTracks.add(new VideoTrack(j10));
    }

    public boolean addPreservedTrack(VideoTrack videoTrack) {
        checkMediaStreamExists();
        if (!nativeAddVideoTrackToNativeStream(this.nativeStream, videoTrack.getNativeVideoTrack())) {
            return false;
        }
        this.preservedVideoTracks.add(videoTrack);
        return true;
    }

    public boolean addTrack(AudioTrack audioTrack) {
        checkMediaStreamExists();
        if (!nativeAddAudioTrackToNativeStream(this.nativeStream, audioTrack.getNativeAudioTrack())) {
            return false;
        }
        this.audioTracks.add(audioTrack);
        return true;
    }

    public void dispose() {
        checkMediaStreamExists();
        while (!this.audioTracks.isEmpty()) {
            AudioTrack audioTrack = this.audioTracks.get(0);
            removeTrack(audioTrack);
            audioTrack.dispose();
        }
        while (!this.videoTracks.isEmpty()) {
            VideoTrack videoTrack = this.videoTracks.get(0);
            removeTrack(videoTrack);
            videoTrack.dispose();
        }
        while (!this.preservedVideoTracks.isEmpty()) {
            removeTrack(this.preservedVideoTracks.get(0));
        }
        JniCommon.nativeReleaseRef(this.nativeStream);
        this.nativeStream = 0L;
    }

    public String getId() {
        checkMediaStreamExists();
        return nativeGetId(this.nativeStream);
    }

    public long getNativeMediaStream() {
        checkMediaStreamExists();
        return this.nativeStream;
    }

    public void removeAudioTrack(long j10) {
        removeMediaStreamTrack(this.audioTracks, j10);
    }

    public boolean removeTrack(AudioTrack audioTrack) {
        checkMediaStreamExists();
        this.audioTracks.remove(audioTrack);
        return nativeRemoveAudioTrack(this.nativeStream, audioTrack.getNativeAudioTrack());
    }

    public void removeVideoTrack(long j10) {
        removeMediaStreamTrack(this.videoTracks, j10);
    }

    public String toString() {
        return androidx.compose.material3.d.j(this.videoTracks.size(), "]", a2.q("[", getId(), ":A=", this.audioTracks.size(), ":V="));
    }

    public boolean addTrack(VideoTrack videoTrack) {
        checkMediaStreamExists();
        if (!nativeAddVideoTrackToNativeStream(this.nativeStream, videoTrack.getNativeVideoTrack())) {
            return false;
        }
        this.videoTracks.add(videoTrack);
        return true;
    }

    public boolean removeTrack(VideoTrack videoTrack) {
        checkMediaStreamExists();
        this.videoTracks.remove(videoTrack);
        this.preservedVideoTracks.remove(videoTrack);
        return nativeRemoveVideoTrack(this.nativeStream, videoTrack.getNativeVideoTrack());
    }
}
