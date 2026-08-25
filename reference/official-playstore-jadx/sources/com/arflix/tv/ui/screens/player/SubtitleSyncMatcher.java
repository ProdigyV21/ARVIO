package com.arflix.tv.ui.screens.player;

import android.util.Log;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.arflix.tv.ui.screens.player.SubtitleSyncMatcher;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003NOPB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JI\u0010\r\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00042\u0006\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00162\u0006\u0010\u0015\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010!\u001a\u0004\u0018\u00010\b2\u0006\u0010 \u001a\u00020\u0012H\u0002¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b#\u0010$J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u001b\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b%\u0010$JA\u0010+\u001a\u00020\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010(\u001a\b\u0012\u0004\u0012\u00020'0\u00042\u0006\u0010)\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010*\u001a\u00020\f¢\u0006\u0004\b+\u0010,J?\u0010-\u001a\u00020\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00042\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b-\u0010.J)\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b/\u00100JG\u00104\u001a\u0004\u0018\u0001032\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00042\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\b¢\u0006\u0004\b4\u00105J\u001d\u00107\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b7\u00108J\u001d\u0010;\u001a\u00020\f2\u0006\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u0012¢\u0006\u0004\b;\u0010<J%\u0010>\u001a\u0004\u0018\u00010\u00122\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010=\u001a\u00020\b¢\u0006\u0004\b>\u0010?J\u001b\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010@\u001a\u00020\u0012¢\u0006\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020\u00128\u0002X\u0082T¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010M\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010L¨\u0006Q"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher;", "", "<init>", "()V", "", "Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$TimedCue;", "sorted", "Lx6/x;", "", "referenceIntervals", "offsetMs", "toleranceMs", "", "scoreSortedShifted", "(Ljava/util/List;Ljava/util/List;JJ)D", "ms", "", "useComma", "", "formatTimestamp", "(JZ)Ljava/lang/String;", "text", "", "tokens", "(Ljava/lang/String;)Ljava/util/Set;", "normalize", "(Ljava/lang/String;)Ljava/lang/String;", "url", "", "bytes", "looksGzipped", "(Ljava/lang/String;[B)Z", "value", "parseTimestamp", "(Ljava/lang/String;)Ljava/lang/Long;", "loadRaw", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "loadCues", "cues", "Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$SpokenSample;", "samples", "latencyMs", "minSimilarity", "score", "(Ljava/util/List;Ljava/util/List;JJD)D", "scoreByTiming", "(Ljava/util/List;Ljava/util/List;J)D", "shiftCues", "(Ljava/util/List;J)Ljava/util/List;", "minOffsetMs", "maxOffsetMs", "Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$OffsetMatch;", "estimateOffsetMatch", "(Ljava/util/List;Ljava/util/List;JJ)Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$OffsetMatch;", "raw", "shiftTimestamps", "(Ljava/lang/String;J)Ljava/lang/String;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "similarity", "(Ljava/lang/String;Ljava/lang/String;)D", "timeMs", "cueTextAt", "(Ljava/util/List;J)Ljava/lang/String;", "content", "parseCues", "(Ljava/lang/String;)Ljava/util/List;", "TAG", "Ljava/lang/String;", "Lgb/h0;", "client", "Lgb/h0;", "SINGLE_CUE_MAX_MS", "J", "Lkotlin/text/m;", "TIME_LINE", "Lkotlin/text/m;", "TAG_STRIP", "TimedCue", "SpokenSample", "OffsetMatch", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleSyncMatcher {
    public static final int $stable;
    public static final SubtitleSyncMatcher INSTANCE = new SubtitleSyncMatcher();
    private static final long SINGLE_CUE_MAX_MS = 7000;
    private static final String TAG = "SubMatch";
    private static final kotlin.text.m TAG_STRIP;
    private static final kotlin.text.m TIME_LINE;
    private static final gb.h0 client;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$OffsetMatch;", "", "offsetMs", "", "correctedScore", "", "baseScore", "<init>", "(JDD)V", "getOffsetMs", "()J", "getCorrectedScore", "()D", "getBaseScore", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class OffsetMatch {
        public static final int $stable = 0;
        private final double baseScore;
        private final double correctedScore;
        private final long offsetMs;

        public OffsetMatch(long j10, double d4, double d10) {
            this.offsetMs = j10;
            this.correctedScore = d4;
            this.baseScore = d10;
        }

        public static /* synthetic */ OffsetMatch copy$default(OffsetMatch offsetMatch, long j10, double d4, double d10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = offsetMatch.offsetMs;
            }
            long j11 = j10;
            if ((i10 & 2) != 0) {
                d4 = offsetMatch.correctedScore;
            }
            double d11 = d4;
            if ((i10 & 4) != 0) {
                d10 = offsetMatch.baseScore;
            }
            return offsetMatch.copy(j11, d11, d10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getOffsetMs() {
            return this.offsetMs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final double getBaseScore() {
            return this.baseScore;
        }

        public final OffsetMatch copy(long offsetMs, double correctedScore, double baseScore) {
            return new OffsetMatch(offsetMs, correctedScore, baseScore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OffsetMatch)) {
                return false;
            }
            OffsetMatch offsetMatch = (OffsetMatch) other;
            return this.offsetMs == offsetMatch.offsetMs && Double.compare(this.correctedScore, offsetMatch.correctedScore) == 0 && Double.compare(this.baseScore, offsetMatch.baseScore) == 0;
        }

        public final double getBaseScore() {
            return this.baseScore;
        }

        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final long getOffsetMs() {
            return this.offsetMs;
        }

        public int hashCode() {
            long j10 = this.offsetMs;
            long jDoubleToLongBits = Double.doubleToLongBits(this.correctedScore);
            int i10 = ((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31;
            long jDoubleToLongBits2 = Double.doubleToLongBits(this.baseScore);
            return i10 + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
        }

        public String toString() {
            long j10 = this.offsetMs;
            double d4 = this.correctedScore;
            double d10 = this.baseScore;
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "OffsetMatch(offsetMs=", ", correctedScore=");
            sbR.append(d4);
            sbR.append(", baseScore=");
            sbR.append(d10);
            sbR.append(")");
            return sbR.toString();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$SpokenSample;", "", "text", "", "positionMs", "", "<init>", "(Ljava/lang/String;J)V", "getText", "()Ljava/lang/String;", "getPositionMs", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SpokenSample {
        public static final int $stable = 0;
        private final long positionMs;
        private final String text;

        public SpokenSample(String str, long j10) {
            this.text = str;
            this.positionMs = j10;
        }

        public static /* synthetic */ SpokenSample copy$default(SpokenSample spokenSample, String str, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = spokenSample.text;
            }
            if ((i10 & 2) != 0) {
                j10 = spokenSample.positionMs;
            }
            return spokenSample.copy(str, j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getPositionMs() {
            return this.positionMs;
        }

        public final SpokenSample copy(String text, long positionMs) {
            return new SpokenSample(text, positionMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpokenSample)) {
                return false;
            }
            SpokenSample spokenSample = (SpokenSample) other;
            return kotlin.jvm.internal.p.a(this.text, spokenSample.text) && this.positionMs == spokenSample.positionMs;
        }

        public final long getPositionMs() {
            return this.positionMs;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            int iHashCode = this.text.hashCode() * 31;
            long j10 = this.positionMs;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "SpokenSample(text=" + this.text + ", positionMs=" + this.positionMs + ")";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleSyncMatcher$TimedCue;", "", "startMs", "", "endMs", "text", "", "<init>", "(JJLjava/lang/String;)V", "getStartMs", "()J", "getEndMs", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TimedCue {
        public static final int $stable = 0;
        private final long endMs;
        private final long startMs;
        private final String text;

        public TimedCue(long j10, long j11, String str) {
            this.startMs = j10;
            this.endMs = j11;
            this.text = str;
        }

        public static /* synthetic */ TimedCue copy$default(TimedCue timedCue, long j10, long j11, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = timedCue.startMs;
            }
            long j12 = j10;
            if ((i10 & 2) != 0) {
                j11 = timedCue.endMs;
            }
            long j13 = j11;
            if ((i10 & 4) != 0) {
                str = timedCue.text;
            }
            return timedCue.copy(j12, j13, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getStartMs() {
            return this.startMs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getEndMs() {
            return this.endMs;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final TimedCue copy(long startMs, long endMs, String text) {
            return new TimedCue(startMs, endMs, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TimedCue)) {
                return false;
            }
            TimedCue timedCue = (TimedCue) other;
            return this.startMs == timedCue.startMs && this.endMs == timedCue.endMs && kotlin.jvm.internal.p.a(this.text, timedCue.text);
        }

        public final long getEndMs() {
            return this.endMs;
        }

        public final long getStartMs() {
            return this.startMs;
        }

        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            long j10 = this.startMs;
            long j11 = this.endMs;
            return this.text.hashCode() + (((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) ((j11 >>> 32) ^ j11))) * 31);
        }

        public String toString() {
            long j10 = this.startMs;
            long j11 = this.endMs;
            String str = this.text;
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "TimedCue(startMs=", ", endMs=");
            sbR.append(j11);
            sbR.append(", text=");
            sbR.append(str);
            sbR.append(")");
            return sbR.toString();
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$loadCues$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleSyncMatcher", f = "SubtitleSyncMatcher.kt", l = {52}, m = "loadCues", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleSyncMatcher.this.loadCues(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$loadRaw$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$loadRaw$2", f = "SubtitleSyncMatcher.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ String $url;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$url = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$url, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            gb.s0 s0Var;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = this.$url;
            try {
                gb.j0 j0Var = new gb.j0();
                j0Var.i(str);
                gb.p0 p0VarD = SubtitleSyncMatcher.client.a(j0Var.b()).d();
                try {
                    if (p0VarD.k() && (s0Var = p0VarD.f15786q) != null) {
                        byte[] bArrJ = s0Var.j();
                        c0Var = SubtitleSyncMatcher.INSTANCE.looksGzipped(str, bArrJ) ? ac.b.U(new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bArrJ)), kotlin.text.a.f19924a), 8192)) : new String(bArrJ, kotlin.text.a.f19924a);
                    } else {
                        c0Var = null;
                    }
                    p0VarD.close();
                } finally {
                }
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            String str2 = this.$url;
            Throwable thA = x6.d0.a(c0Var);
            if (thA != null) {
                Log.w(SubtitleSyncMatcher.TAG, "loadRaw failed url=" + str2 + " err=" + thA.getMessage());
            }
            if (c0Var instanceof x6.c0) {
                return null;
            }
            return c0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    static {
        gb.g0 g0Var = new gb.g0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(10L, timeUnit);
        g0Var.e(15L, timeUnit);
        client = new gb.h0(g0Var);
        TIME_LINE = new kotlin.text.m("(\\d{1,2}:\\d{2}:\\d{2}[.,]\\d{1,3}|\\d{1,2}:\\d{2}[.,]\\d{1,3})\\s*-->\\s*(\\d{1,2}:\\d{2}:\\d{2}[.,]\\d{1,3}|\\d{1,2}:\\d{2}[.,]\\d{1,3})");
        TAG_STRIP = new kotlin.text.m("<[^>]*>");
        $stable = 8;
    }

    private SubtitleSyncMatcher() {
    }

    private static final void estimateOffsetMatch$consider(List<TimedCue> list, List<x6.x> list2, kotlin.jvm.internal.g0 g0Var, kotlin.jvm.internal.j0 j0Var, long j10) {
        double dScoreSortedShifted$default = scoreSortedShifted$default(INSTANCE, list, list2, j10, 0L, 8, null);
        double d4 = g0Var.f19741i;
        if (dScoreSortedShifted$default > d4 + 1.0E-9d) {
            g0Var.f19741i = dScoreSortedShifted$default;
            j0Var.f19745i = j10;
        } else {
            if (dScoreSortedShifted$default <= d4 - 1.0E-9d || Math.abs(j10) >= Math.abs(j0Var.f19745i)) {
                return;
            }
            j0Var.f19745i = j10;
        }
    }

    private final String formatTimestamp(long ms, boolean useComma) {
        if (ms < 0) {
            ms = 0;
        }
        long j10 = 3600000;
        long j11 = ms / j10;
        long j12 = ExoPlayer.DEFAULT_STUCK_PLAYING_NOT_ENDING_TIMEOUT_MS;
        long j13 = 1000;
        return String.format(Locale.US, "%02d:%02d:%02d%c%03d", Arrays.copyOf(new Object[]{Long.valueOf(j11), Long.valueOf((ms % j10) / j12), Long.valueOf((ms % j12) / j13), Character.valueOf(useComma ? ',' : '.'), Long.valueOf(ms % j13)}, 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean looksGzipped(String url, byte[] bytes) {
        return kotlin.text.u.K(kotlin.text.o.G0('?', url, url), ".gz", true) || (bytes.length > 2 && bytes[0] == 31 && bytes[1] == -117);
    }

    private final String normalize(String text) {
        StringBuilder sb2 = new StringBuilder(text.length());
        int length = text.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = text.charAt(i10);
            if ((1425 > cCharAt || cCharAt >= 1480) && cCharAt != 8206 && cCharAt != 8207 && cCharAt != 8234 && cCharAt != 8235 && cCharAt != 8236 && cCharAt != 8294 && cCharAt != 8295 && cCharAt != 8297) {
                if (Character.isLetterOrDigit(cCharAt)) {
                    sb2.append(Character.toLowerCase(cCharAt));
                } else {
                    sb2.append(' ');
                }
            }
        }
        return com.arflix.tv.data.repository.g.o(SubtitleSyncMatcherRegexes.INSTANCE.getMULTI_SPACE_REGEX(), sb2.toString(), " ");
    }

    private final Long parseTimestamp(String value) {
        Object c0Var;
        List listX0 = kotlin.text.o.x0(value.replace(',', '.'), new char[]{':'}, 6);
        try {
            int size = listX0.size();
            c0Var = size != 2 ? size != 3 ? null : Long.valueOf((((Long.parseLong((String) listX0.get(1)) * ((long) 60)) + (Long.parseLong((String) listX0.get(0)) * ((long) 3600))) * ((long) 1000)) + ((long) (Double.parseDouble((String) listX0.get(2)) * ((double) 1000)))) : Long.valueOf((Long.parseLong((String) listX0.get(0)) * ((long) 60) * ((long) 1000)) + ((long) (Double.parseDouble((String) listX0.get(1)) * ((double) 1000))));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return (Long) (c0Var instanceof x6.c0 ? null : c0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean score$lambda$0(long j10, long j11, TimedCue timedCue) {
        return timedCue.getEndMs() >= j10 - j11 && timedCue.getStartMs() <= j10 + j11;
    }

    public static /* synthetic */ double scoreByTiming$default(SubtitleSyncMatcher subtitleSyncMatcher, List list, List list2, long j10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = 0;
        }
        return subtitleSyncMatcher.scoreByTiming(list, list2, j10);
    }

    private final double scoreSortedShifted(List<TimedCue> sorted, List<x6.x> referenceIntervals, long offsetMs, long toleranceMs) {
        Iterator<x6.x> it;
        double d4;
        Iterator<x6.x> it2 = referenceIntervals.iterator();
        double dL = 0.0d;
        while (it2.hasNext()) {
            x6.x next = it2.next();
            long jLongValue = ((Number) next.f22608i).longValue();
            long jLongValue2 = ((Number) next.f22609l).longValue();
            long j10 = jLongValue2 - jLongValue;
            if (j10 < 1) {
                j10 = 1;
            }
            if (j10 <= SINGLE_CUE_MAX_MS) {
                d4 = 0.0d;
                for (TimedCue timedCue : sorted) {
                    if ((timedCue.getStartMs() + offsetMs) - toleranceMs >= jLongValue2) {
                        break;
                    }
                    if (timedCue.getEndMs() + offsetMs + toleranceMs > jLongValue) {
                        double dMin = (Math.min(jLongValue2, r14) - Math.max(jLongValue, r1)) / j10;
                        if (dMin > d4) {
                            d4 = dMin;
                        }
                    }
                }
                it = it2;
            } else {
                Iterator<TimedCue> it3 = sorted.iterator();
                long j11 = 0;
                while (it3.hasNext()) {
                    TimedCue next2 = it3.next();
                    long startMs = (next2.getStartMs() + offsetMs) - toleranceMs;
                    if (startMs >= jLongValue2) {
                        break;
                    }
                    long endMs = next2.getEndMs() + offsetMs;
                    Iterator<x6.x> it4 = it2;
                    Iterator<TimedCue> it5 = it3;
                    long j12 = endMs + toleranceMs;
                    if (j12 > jLongValue) {
                        long jMax = Math.max(startMs, jLongValue);
                        long jMin = Math.min(j12, jLongValue2);
                        if (jMin > jMax) {
                            j11 += jMin - jMax;
                            jLongValue = jMin;
                        }
                    }
                    it2 = it4;
                    it3 = it5;
                }
                it = it2;
                d4 = j11 / j10;
            }
            dL += qb.d.l(d4, 0.0d, 1.0d);
            it2 = it;
        }
        return dL / ((double) referenceIntervals.size());
    }

    public static /* synthetic */ double scoreSortedShifted$default(SubtitleSyncMatcher subtitleSyncMatcher, List list, List list2, long j10, long j11, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            j11 = 0;
        }
        return subtitleSyncMatcher.scoreSortedShifted(list, list2, j10, j11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence shiftTimestamps$lambda$0(long j10, kotlin.text.i iVar) {
        SubtitleSyncMatcher subtitleSyncMatcher = INSTANCE;
        Long timestamp = subtitleSyncMatcher.parseTimestamp((String) iVar.b().get(1));
        if (timestamp == null) {
            return iVar.getValue();
        }
        long jLongValue = timestamp.longValue();
        Long timestamp2 = subtitleSyncMatcher.parseTimestamp((String) iVar.b().get(2));
        if (timestamp2 == null) {
            return iVar.getValue();
        }
        long jLongValue2 = timestamp2.longValue();
        boolean zU = kotlin.text.o.U((CharSequence) iVar.b().get(1), ',');
        return androidx.compose.foundation.c.t(subtitleSyncMatcher.formatTimestamp(jLongValue + j10, zU), " --> ", subtitleSyncMatcher.formatTimestamp(jLongValue2 + j10, zU));
    }

    private final Set<String> tokens(String text) {
        List listX0 = kotlin.text.o.x0(normalize(text), new char[]{' '}, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listX0) {
            if (((String) obj).length() >= 2) {
                arrayList.add(obj);
            }
        }
        return kotlin.collections.x.g1(arrayList);
    }

    public final String cueTextAt(List<TimedCue> cues, long timeMs) {
        Object next;
        Iterator<T> it = cues.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            TimedCue timedCue = (TimedCue) next;
            long startMs = timedCue.getStartMs();
            if (timeMs <= timedCue.getEndMs() && startMs <= timeMs) {
                break;
            }
        }
        TimedCue timedCue2 = (TimedCue) next;
        if (timedCue2 != null) {
            return timedCue2.getText();
        }
        return null;
    }

    public final OffsetMatch estimateOffsetMatch(List<TimedCue> cues, List<x6.x> referenceIntervals, long minOffsetMs, long maxOffsetMs) {
        if (cues.size() < 3 || referenceIntervals.size() < 4) {
            return null;
        }
        List listW0 = kotlin.collections.x.W0(cues, new Comparator() { // from class: com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$estimateOffsetMatch$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Long.valueOf(((SubtitleSyncMatcher.TimedCue) t2).getStartMs()), Long.valueOf(((SubtitleSyncMatcher.TimedCue) t10).getStartMs()));
            }
        });
        double dScoreSortedShifted$default = scoreSortedShifted$default(this, listW0, referenceIntervals, 0L, 0L, 8, null);
        kotlin.jvm.internal.j0 j0Var = new kotlin.jvm.internal.j0();
        kotlin.jvm.internal.g0 g0Var = new kotlin.jvm.internal.g0();
        g0Var.f19741i = dScoreSortedShifted$default;
        for (long j10 = -maxOffsetMs; j10 <= maxOffsetMs; j10 += 250) {
            estimateOffsetMatch$consider(listW0, referenceIntervals, g0Var, j0Var, j10);
        }
        long j11 = j0Var.f19745i;
        long j12 = j11 + 250;
        for (long j13 = j11 - 250; j13 <= j12; j13 += 25) {
            estimateOffsetMatch$consider(listW0, referenceIntervals, g0Var, j0Var, j13);
        }
        if (Math.abs(j0Var.f19745i) < minOffsetMs) {
            return null;
        }
        return new OffsetMatch(j0Var.f19745i, g0Var.f19741i, dScoreSortedShifted$default);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCues(java.lang.String r5, d7.d<? super java.util.List<com.arflix.tv.ui.screens.player.SubtitleSyncMatcher.TimedCue>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.ui.screens.player.SubtitleSyncMatcher.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$loadCues$1 r0 = (com.arflix.tv.ui.screens.player.SubtitleSyncMatcher.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$loadCues$1 r0 = new com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$loadCues$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L42
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            r6 = 0
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r6 = r4.loadRaw(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L42
            return r5
        L42:
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L50
            com.arflix.tv.ui.screens.player.SubtitleSyncMatcher r5 = com.arflix.tv.ui.screens.player.SubtitleSyncMatcher.INSTANCE
            java.util.List r5 = r5.parseCues(r6)
            if (r5 != 0) goto L4f
            goto L50
        L4f:
            return r5
        L50:
            kotlin.collections.z r5 = kotlin.collections.z.f19728i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleSyncMatcher.loadCues(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object loadRaw(String str, d7.d<? super String> dVar) {
        return ka.m0.y(ka.x0.f19655d, new AnonymousClass2(str, null), dVar);
    }

    public final List<TimedCue> parseCues(String content) {
        List listSingletonList;
        kotlin.text.k kVarB;
        Long timestamp;
        String strReplace = kotlin.text.u.O(content, "\r\n", "\n", false).replace('\r', '\n');
        Pattern patternCompile = Pattern.compile("\n\\s*\n");
        kotlin.text.o.u0(0);
        Matcher matcher = patternCompile.matcher(strReplace);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList(10);
            int iEnd = 0;
            do {
                arrayList.add(strReplace.subSequence(iEnd, matcher.start()).toString());
                iEnd = matcher.end();
            } while (matcher.find());
            arrayList.add(strReplace.subSequence(iEnd, strReplace.length()).toString());
            listSingletonList = arrayList;
        } else {
            listSingletonList = Collections.singletonList(strReplace.toString());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = listSingletonList.iterator();
        while (it.hasNext()) {
            List listX0 = kotlin.text.o.x0((String) it.next(), new char[]{'\n'}, 6);
            ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(listX0, 10));
            Iterator it2 = listX0.iterator();
            while (it2.hasNext()) {
                a2.x((String) it2.next(), arrayList3);
            }
            ArrayList arrayList4 = new ArrayList();
            for (Object obj : arrayList3) {
                if (((String) obj).length() > 0) {
                    arrayList4.add(obj);
                }
            }
            if (!arrayList4.isEmpty()) {
                Iterator it3 = arrayList4.iterator();
                int i10 = 0;
                while (true) {
                    if (!it3.hasNext()) {
                        i10 = -1;
                        break;
                    }
                    if (TIME_LINE.a((String) it3.next())) {
                        break;
                    }
                    i10++;
                }
                if (i10 >= 0 && (kVarB = kotlin.text.m.b(TIME_LINE, (CharSequence) arrayList4.get(i10))) != null && (timestamp = parseTimestamp((String) ((kotlin.collections.n0) kVarB.b()).get(1))) != null) {
                    long jLongValue = timestamp.longValue();
                    Long timestamp2 = parseTimestamp((String) ((kotlin.collections.n0) kVarB.b()).get(2));
                    if (timestamp2 != null) {
                        long jLongValue2 = timestamp2.longValue();
                        String strO = com.arflix.tv.data.repository.g.o(TAG_STRIP, kotlin.collections.x.u0(kotlin.collections.x.h0(arrayList4, i10 + 1), " ", null, null, null, 62), "");
                        if (strO.length() > 0 && jLongValue2 > jLongValue) {
                            arrayList2.add(new TimedCue(jLongValue, jLongValue2, strO));
                        }
                    }
                }
            }
        }
        return arrayList2;
    }

    public final double score(List<TimedCue> cues, List<SpokenSample> samples, long latencyMs, long toleranceMs, double minSimilarity) {
        Double dValueOf;
        if (cues.isEmpty() || samples.isEmpty()) {
            return 0.0d;
        }
        int i10 = 0;
        for (SpokenSample spokenSample : samples) {
            ga.h hVar = new ga.h(ga.r.I(new ga.p(cues, 3), new com.arflix.tv.data.repository.u(spokenSample.getPositionMs() - latencyMs, toleranceMs, 1)));
            if (hVar.hasNext()) {
                double dSimilarity = INSTANCE.similarity(spokenSample.getText(), ((TimedCue) hVar.next()).getText());
                while (hVar.hasNext()) {
                    dSimilarity = Math.max(dSimilarity, INSTANCE.similarity(spokenSample.getText(), ((TimedCue) hVar.next()).getText()));
                }
                dValueOf = Double.valueOf(dSimilarity);
            } else {
                dValueOf = null;
            }
            if ((dValueOf != null ? dValueOf.doubleValue() : 0.0d) >= minSimilarity) {
                i10++;
            }
        }
        return ((double) i10) / ((double) samples.size());
    }

    public final double scoreByTiming(List<TimedCue> cues, List<x6.x> referenceIntervals, long toleranceMs) {
        if (cues.isEmpty() || referenceIntervals.isEmpty()) {
            return 0.0d;
        }
        return scoreSortedShifted(kotlin.collections.x.W0(cues, new Comparator() { // from class: com.arflix.tv.ui.screens.player.SubtitleSyncMatcher$scoreByTiming$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Long.valueOf(((SubtitleSyncMatcher.TimedCue) t2).getStartMs()), Long.valueOf(((SubtitleSyncMatcher.TimedCue) t10).getStartMs()));
            }
        }), referenceIntervals, 0L, toleranceMs);
    }

    public final List<TimedCue> shiftCues(List<TimedCue> cues, long offsetMs) {
        if (offsetMs == 0) {
            return cues;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(cues, 10));
        for (TimedCue timedCue : cues) {
            arrayList.add(new TimedCue(timedCue.getStartMs() + offsetMs, timedCue.getEndMs() + offsetMs, timedCue.getText()));
        }
        return arrayList;
    }

    public final String shiftTimestamps(String raw, long offsetMs) {
        return offsetMs == 0 ? raw : TIME_LINE.g(raw, new com.arflix.tv.data.repository.i(offsetMs, 3));
    }

    public final double similarity(String a10, String b10) {
        Set<String> set = tokens(a10);
        Set<String> set2 = tokens(b10);
        if (set.isEmpty() || set2.isEmpty()) {
            return 0.0d;
        }
        Set<String> set3 = set;
        int i10 = 0;
        if (!(set3 instanceof Collection) || !set3.isEmpty()) {
            Iterator<T> it = set3.iterator();
            while (it.hasNext()) {
                if (set2.contains((String) it.next()) && (i10 = i10 + 1) < 0) {
                    t7.a.P();
                    throw null;
                }
            }
        }
        return ((double) i10) / ((double) Math.min(set.size(), set2.size()));
    }
}
