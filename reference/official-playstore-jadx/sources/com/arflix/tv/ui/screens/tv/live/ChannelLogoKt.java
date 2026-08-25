package com.arflix.tv.ui.screens.tv.live;

import android.util.Base64;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil.compose.AsyncImagePainter;
import io.ktor.http.ContentDisposition;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a)\u0010\t\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\u000f\u0010\r\u001a\u0019\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0011\u0010\r\u001a!\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u001a²\u0006\u000e\u0010\u0019\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channel", "Landroidx/compose/ui/unit/Dp;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "ChannelLogo-uFdPcIQ", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;FLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "ChannelLogo", "", ContentDisposition.Parameters.Name, "initialsFor", "(Ljava/lang/String;)Ljava/lang/String;", "raw", "safeChannelLogoUrl", "value", "decodeLegacyLogoUrl", "text", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "SmallTag-iJQMabo", "(Ljava/lang/String;JLandroidx/compose/runtime/Composer;II)V", "SmallTag", "", "showFallback", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ChannelLogoKt {
    /* JADX WARN: Removed duplicated region for block: B:119:0x04e6  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03b5  */
    /* JADX INFO: renamed from: ChannelLogo-uFdPcIQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6366ChannelLogouFdPcIQ(com.arflix.tv.ui.screens.tv.live.EnrichedChannel r96, float r97, androidx.compose.ui.Modifier r98, androidx.compose.runtime.Composer r99, int r100, int r101) {
        /*
            Method dump skipped, instruction units count: 1295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.ChannelLogoKt.m6366ChannelLogouFdPcIQ(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, float, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean ChannelLogo_uFdPcIQ$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ChannelLogo_uFdPcIQ$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelLogo_uFdPcIQ$lambda$3$1$0(MutableState mutableState, AsyncImagePainter.State.Success success) {
        ChannelLogo_uFdPcIQ$lambda$2(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelLogo_uFdPcIQ$lambda$3$2$0(MutableState mutableState, AsyncImagePainter.State.Error error) {
        ChannelLogo_uFdPcIQ$lambda$2(mutableState, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelLogo_uFdPcIQ$lambda$4(EnrichedChannel enrichedChannel, float f10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6366ChannelLogouFdPcIQ(enrichedChannel, f10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: SmallTag-iJQMabo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6367SmallTagiJQMabo(java.lang.String r40, long r41, androidx.compose.runtime.Composer r43, int r44, int r45) {
        /*
            Method dump skipped, instruction units count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.ChannelLogoKt.m6367SmallTagiJQMabo(java.lang.String, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SmallTag_iJQMabo$lambda$1(String str, long j10, int i10, int i11, Composer composer, int i12) {
        m6367SmallTagiJQMabo(str, j10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final String decodeLegacyLogoUrl(String str) {
        Object obj = null;
        if (str.length() < 12) {
            return null;
        }
        int i10 = 0;
        for (int i11 = 0; i11 < str.length(); i11++) {
            if (com.google.common.util.concurrent.r0.u(str.charAt(i11))) {
                return null;
            }
        }
        ga.h hVar = new ga.h(ga.r.Q(new ga.p(t7.a.E(0, 10), 3), new r(str, i10)));
        while (hVar.hasNext()) {
            Object next = hVar.next();
            String str2 = (String) next;
            if (kotlin.text.u.P(str2, "http://", true) || kotlin.text.u.P(str2, "https://", true)) {
                obj = next;
                break;
            }
        }
        return (String) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String decodeLegacyLogoUrl$lambda$1(String str, int i10) {
        Object c0Var;
        try {
            c0Var = kotlin.text.o.L0(new String(Base64.decode(str, i10), StandardCharsets.UTF_8)).toString();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        return (String) c0Var;
    }

    public static final String initialsFor(String str) {
        List listSingletonList;
        String string = kotlin.text.o.L0(str).toString();
        if (string.length() == 0) {
            return "??";
        }
        Pattern patternCompile = Pattern.compile("\\s+");
        kotlin.text.o.u0(0);
        Matcher matcher = patternCompile.matcher(string);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList(10);
            int iEnd = 0;
            do {
                arrayList.add(string.subSequence(iEnd, matcher.start()).toString());
                iEnd = matcher.end();
            } while (matcher.find());
            arrayList.add(string.subSequence(iEnd, string.length()).toString());
            listSingletonList = arrayList;
        } else {
            listSingletonList = Collections.singletonList(string.toString());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listSingletonList) {
            String str2 = (String) obj;
            int i10 = 0;
            while (true) {
                if (i10 >= str2.length()) {
                    break;
                }
                if (Character.isLetterOrDigit(str2.charAt(i10))) {
                    arrayList2.add(obj);
                    break;
                }
                i10++;
            }
        }
        int size = arrayList2.size();
        if (size == 0) {
            return "??";
        }
        if (size == 1) {
            return kotlin.text.o.I0(2, (String) arrayList2.get(0)).toUpperCase(Locale.ROOT);
        }
        char cZ = kotlin.text.o.Z((CharSequence) arrayList2.get(0));
        char cZ2 = kotlin.text.o.Z((CharSequence) arrayList2.get(1));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(cZ);
        sb2.append(cZ2);
        return sb2.toString().toUpperCase(Locale.ROOT);
    }

    private static final String safeChannelLogoUrl(String str) {
        String string = str != null ? kotlin.text.o.L0(str).toString() : null;
        if (string == null) {
            string = "";
        }
        if (!kotlin.text.o.h0(string)) {
            if (!kotlin.text.u.P(string, "http://", true) && !kotlin.text.u.P(string, "https://", true)) {
                string = kotlin.text.u.P(string, "//", false) ? "https:".concat(string) : decodeLegacyLogoUrl(string);
            }
            if (string != null && (kotlin.text.u.P(string, "http://", true) || kotlin.text.u.P(string, "https://", true))) {
                return string;
            }
        }
        return null;
    }
}
