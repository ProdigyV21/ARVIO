package com.discord.org.webrtc;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RTCStatsReport {
    private final Map<String, RTCStats> stats;
    private final long timestampUs;

    public RTCStatsReport(long j10, Map<String, RTCStats> map) {
        this.timestampUs = j10;
        this.stats = map;
    }

    private static RTCStatsReport create(long j10, Map map) {
        return new RTCStatsReport(j10, map);
    }

    public Map<String, RTCStats> getStatsMap() {
        return this.stats;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("{ timestampUs: ");
        sb2.append(this.timestampUs);
        sb2.append(", stats: [\n");
        boolean z = true;
        for (RTCStats rTCStats : this.stats.values()) {
            if (!z) {
                sb2.append(",\n");
            }
            sb2.append(rTCStats);
            z = false;
        }
        sb2.append(" ] }");
        return sb2.toString();
    }
}
