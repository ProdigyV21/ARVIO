package com.discord.socialsdk;

import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.util.Log;
import j$.util.Map;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DiscordAudioManagerInterface {
    protected static final String TAG = "DiscordSocialSdk";
    protected final AudioManager audioManager;
    protected final Context context;
    protected final HashSet<AudioDeviceInfo> audioDevices = new HashSet<>();
    protected boolean preventCommsForBluetooth = false;
    protected boolean isActiveCall = false;
    protected int expectedMode = 0;
    protected final AudioDeviceCallback audioDeviceCallback = new AudioDeviceCallback() { // from class: com.discord.socialsdk.DiscordAudioManagerInterface.1
        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
            if (audioDeviceInfoArr == null || audioDeviceInfoArr.length <= 0) {
                return;
            }
            for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfoArr) {
                if (audioDeviceInfo.isSink()) {
                    DiscordAudioManagerInterface.this.audioDevices.add(audioDeviceInfo);
                }
            }
            DiscordAudioManagerInterface.this.updateAudioModeForCurrentState();
            DiscordAudioManagerInterface.this.updateActiveAudioDevice();
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
            if (audioDeviceInfoArr == null || audioDeviceInfoArr.length <= 0) {
                return;
            }
            for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfoArr) {
                if (audioDeviceInfo.isSink()) {
                    DiscordAudioManagerInterface.this.audioDevices.remove(audioDeviceInfo);
                }
            }
            DiscordAudioManagerInterface.this.updateAudioModeForCurrentState();
            DiscordAudioManagerInterface.this.updateActiveAudioDevice();
        }
    };

    public DiscordAudioManagerInterface(Context context) {
        this.context = context;
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    public abstract AudioDeviceInfo[] androidGetAudioDevices();

    public AudioDeviceInfo findDeviceOfType(DiscordDeviceType discordDeviceType) {
        for (AudioDeviceInfo audioDeviceInfo : this.audioDevices) {
            if (((DiscordDeviceType) Map.EL.getOrDefault(DeviceInfoToDiscordDeviceType.mapping, Integer.valueOf(audioDeviceInfo.getType()), DiscordDeviceType.INVALID)) == discordDeviceType) {
                return audioDeviceInfo;
            }
        }
        return null;
    }

    public boolean isBluetoothConnected() {
        return findDeviceOfType(DiscordDeviceType.BLUETOOTH_HEADSET) != null;
    }

    public abstract void setActiveCall(boolean z);

    public abstract boolean setActiveDevice(AudioDeviceInfo audioDeviceInfo);

    public void setPreventCommsForBluetooth(boolean z) {
        this.preventCommsForBluetooth = z;
    }

    public void updateActiveAudioDevice() {
        if (this.expectedMode == 0) {
            return;
        }
        AudioDeviceInfo audioDeviceInfoFindDeviceOfType = findDeviceOfType(DiscordDeviceType.WIRED_HEADSET);
        if (audioDeviceInfoFindDeviceOfType == null || !setActiveDevice(audioDeviceInfoFindDeviceOfType)) {
            AudioDeviceInfo audioDeviceInfoFindDeviceOfType2 = findDeviceOfType(DiscordDeviceType.BLUETOOTH_HEADSET);
            if (audioDeviceInfoFindDeviceOfType2 == null || !setActiveDevice(audioDeviceInfoFindDeviceOfType2)) {
                AudioDeviceInfo audioDeviceInfoFindDeviceOfType3 = findDeviceOfType(DiscordDeviceType.BUILTIN_SPEAKER);
                if (audioDeviceInfoFindDeviceOfType3 == null || !setActiveDevice(audioDeviceInfoFindDeviceOfType3)) {
                    Log.e(TAG, "Unable to set active device");
                }
            }
        }
    }

    public void updateAudioDevices() {
        this.audioDevices.clear();
        for (AudioDeviceInfo audioDeviceInfo : androidGetAudioDevices()) {
            if (audioDeviceInfo.isSink()) {
                this.audioDevices.add(audioDeviceInfo);
            }
        }
    }

    public void updateAudioModeForCurrentState() {
        if (!this.isActiveCall) {
            if (this.expectedMode != 0) {
                Log.d(TAG, "Active call ended, switching to normal mode");
                this.expectedMode = 0;
                this.audioManager.setMode(0);
                return;
            }
            return;
        }
        boolean z = (this.preventCommsForBluetooth && isBluetoothConnected()) ? false : true;
        if (z && this.expectedMode != 3) {
            Log.d(TAG, "Bluetooth not connected, switching to communication mode");
            this.expectedMode = 3;
            this.audioManager.setMode(3);
        } else {
            if (z || this.expectedMode != 3) {
                return;
            }
            Log.d(TAG, "Bluetooth connected and preventCommsForBluetooth=true, switching to normal mode");
            this.expectedMode = 0;
            this.audioManager.setMode(0);
        }
    }
}
