package com.discord.socialsdk;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class DiscordAudioManager extends DiscordAudioManagerInterface {
    private static DiscordAudioManager instance;

    private DiscordAudioManager(Context context) {
        super(context);
    }

    public static DiscordAudioManager getInstance(Context context) {
        if (instance == null) {
            Log.d("DiscordSocialSdk", "Creating new DiscordAudioManager instance");
            instance = new DiscordAudioManager(context);
        }
        return instance;
    }

    @Override // com.discord.socialsdk.DiscordAudioManagerInterface
    public AudioDeviceInfo[] androidGetAudioDevices() {
        return (AudioDeviceInfo[]) this.audioManager.getAvailableCommunicationDevices().toArray(new AudioDeviceInfo[0]);
    }

    @Override // com.discord.socialsdk.DiscordAudioManagerInterface
    public void setActiveCall(boolean z) {
        this.isActiveCall = z;
        if (!z) {
            this.audioManager.unregisterAudioDeviceCallback(this.audioDeviceCallback);
            this.audioManager.clearCommunicationDevice();
            updateAudioModeForCurrentState();
        } else {
            this.audioManager.registerAudioDeviceCallback(this.audioDeviceCallback, null);
            updateAudioDevices();
            updateAudioModeForCurrentState();
            updateActiveAudioDevice();
        }
    }

    @Override // com.discord.socialsdk.DiscordAudioManagerInterface
    public boolean setActiveDevice(AudioDeviceInfo audioDeviceInfo) {
        if (audioDeviceInfo == null) {
            return false;
        }
        Log.d("DiscordSocialSdk", "setActiveDevice to type: " + audioDeviceInfo.getType());
        return this.audioManager.setCommunicationDevice(audioDeviceInfo);
    }
}
