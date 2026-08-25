package com.discord.socialsdk;

import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceInfoToDiscordDeviceType {
    public static final HashMap<Integer, DiscordDeviceType> mapping = new HashMap<Integer, DiscordDeviceType>() { // from class: com.discord.socialsdk.DeviceInfoToDiscordDeviceType.1
        {
            put(2, DiscordDeviceType.BUILTIN_SPEAKER);
            DiscordDeviceType discordDeviceType = DiscordDeviceType.BLUETOOTH_HEADSET;
            put(27, discordDeviceType);
            put(26, discordDeviceType);
            put(7, discordDeviceType);
            put(23, discordDeviceType);
            DiscordDeviceType discordDeviceType2 = DiscordDeviceType.WIRED_HEADSET;
            put(22, discordDeviceType2);
            put(11, discordDeviceType2);
            put(3, discordDeviceType2);
            put(4, discordDeviceType2);
        }
    };
}
