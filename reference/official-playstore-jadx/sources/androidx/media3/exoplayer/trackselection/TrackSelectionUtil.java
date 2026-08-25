package androidx.media3.exoplayer.trackselection;

import android.os.SystemClock;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackSelectionUtil {

    public interface AdaptiveTrackSelectionFactory {
        ExoTrackSelection createAdaptiveTrackSelection(ExoTrackSelection.Definition definition);
    }

    private TrackSelectionUtil() {
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelection[] trackSelectionArr) {
        o3 o3VarS;
        List[] listArr = new List[trackSelectionArr.length];
        for (int i10 = 0; i10 < trackSelectionArr.length; i10++) {
            TrackSelection trackSelection = trackSelectionArr[i10];
            if (trackSelection != null) {
                o3VarS = h1.s(trackSelection);
            } else {
                f1 f1Var = h1.f14020l;
                o3VarS = o3.f14078o;
            }
            listArr[i10] = o3VarS;
        }
        return buildTracks(mappedTrackInfo, (List<? extends TrackSelection>[]) listArr);
    }

    public static LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection exoTrackSelection) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int length = exoTrackSelection.length();
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11++) {
            if (exoTrackSelection.isTrackExcluded(i11, jElapsedRealtime)) {
                i10++;
            }
        }
        return new LoadErrorHandlingPolicy.FallbackOptions(1, 0, length, i10);
    }

    public static ExoTrackSelection[] createTrackSelectionsForDefinitions(ExoTrackSelection.Definition[] definitionArr, AdaptiveTrackSelectionFactory adaptiveTrackSelectionFactory) {
        ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
        boolean z = false;
        for (int i10 = 0; i10 < definitionArr.length; i10++) {
            ExoTrackSelection.Definition definition = definitionArr[i10];
            if (definition != null) {
                int[] iArr = definition.tracks;
                if (iArr.length <= 1 || z) {
                    exoTrackSelectionArr[i10] = new FixedTrackSelection(definition.group, iArr[0], definition.type);
                } else {
                    exoTrackSelectionArr[i10] = adaptiveTrackSelectionFactory.createAdaptiveTrackSelection(definition);
                    z = true;
                }
            }
        }
        return exoTrackSelectionArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto Lf
            r3 = 0
            r0 = 1
            if (r6 <= r7) goto L8
            r1 = r0
            goto L9
        L8:
            r1 = r3
        L9:
            if (r4 <= r5) goto Lc
            r3 = r0
        Lc:
            if (r1 == r3) goto Lf
            goto L12
        Lf:
            r2 = r5
            r5 = r4
            r4 = r2
        L12:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L22
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = androidx.media3.common.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L22:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = androidx.media3.common.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.trackselection.TrackSelectionUtil.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    @Deprecated
    public static DefaultTrackSelector.Parameters updateParametersWithOverride(DefaultTrackSelector.Parameters parameters, int i10, TrackGroupArray trackGroupArray, boolean z, DefaultTrackSelector.SelectionOverride selectionOverride) {
        DefaultTrackSelector.Parameters.Builder rendererDisabled = parameters.buildUpon().clearSelectionOverrides(i10).setRendererDisabled(i10, z);
        if (selectionOverride != null) {
            rendererDisabled.setSelectionOverride(i10, trackGroupArray, selectionOverride);
        }
        return rendererDisabled.build();
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, List<? extends TrackSelection>[] listArr) {
        boolean z;
        e1 e1Var = new e1(4);
        for (int i10 = 0; i10 < mappedTrackInfo.getRendererCount(); i10++) {
            TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i10);
            List<? extends TrackSelection> list = listArr[i10];
            for (int i11 = 0; i11 < trackGroups.length; i11++) {
                TrackGroup trackGroup = trackGroups.get(i11);
                boolean z5 = mappedTrackInfo.getAdaptiveSupport(i10, i11, false) != 0;
                int i12 = trackGroup.length;
                int[] iArr = new int[i12];
                boolean[] zArr = new boolean[i12];
                for (int i13 = 0; i13 < trackGroup.length; i13++) {
                    iArr[i13] = mappedTrackInfo.getTrackSupport(i10, i11, i13);
                    int i14 = 0;
                    while (true) {
                        if (i14 >= list.size()) {
                            z = false;
                            break;
                        }
                        TrackSelection trackSelection = list.get(i14);
                        if (trackSelection.getTrackGroup().equals(trackGroup) && trackSelection.indexOf(i13) != -1) {
                            z = true;
                            break;
                        }
                        i14++;
                    }
                    zArr[i13] = z;
                }
                e1Var.c(new Tracks.Group(trackGroup, z5, iArr, zArr));
            }
        }
        TrackGroupArray unmappedTrackGroups = mappedTrackInfo.getUnmappedTrackGroups();
        for (int i15 = 0; i15 < unmappedTrackGroups.length; i15++) {
            TrackGroup trackGroup2 = unmappedTrackGroups.get(i15);
            int[] iArr2 = new int[trackGroup2.length];
            Arrays.fill(iArr2, 0);
            e1Var.c(new Tracks.Group(trackGroup2, false, iArr2, new boolean[trackGroup2.length]));
        }
        return new Tracks(e1Var.f());
    }
}
