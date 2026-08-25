package com.caverock.androidsvg;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.HashMap;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class l2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final l2 f12367i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final l2 f12368l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final l2 f12369m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final l2 f12370n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final HashMap f12371o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ l2[] f12372p;

    /* JADX INFO: Fake field, exist only in values array */
    l2 EF0;

    static {
        l2 l2Var = new l2("svg", 0);
        l2 l2Var2 = new l2(CmcdData.OBJECT_TYPE_AUDIO_ONLY, 1);
        l2 l2Var3 = new l2(TtmlNode.TEXT_EMPHASIS_MARK_CIRCLE, 2);
        l2 l2Var4 = new l2("clipPath", 3);
        l2 l2Var5 = new l2("defs", 4);
        l2 l2Var6 = new l2("desc", 5);
        f12367i = l2Var6;
        l2 l2Var7 = new l2("ellipse", 6);
        l2 l2Var8 = new l2("g", 7);
        l2 l2Var9 = new l2("image", 8);
        l2 l2Var10 = new l2("line", 9);
        l2 l2Var11 = new l2("linearGradient", 10);
        l2 l2Var12 = new l2("marker", 11);
        l2 l2Var13 = new l2("mask", 12);
        l2 l2Var14 = new l2("path", 13);
        l2 l2Var15 = new l2("pattern", 14);
        l2 l2Var16 = new l2("polygon", 15);
        l2 l2Var17 = new l2("polyline", 16);
        l2 l2Var18 = new l2("radialGradient", 17);
        l2 l2Var19 = new l2("rect", 18);
        l2 l2Var20 = new l2("solidColor", 19);
        l2 l2Var21 = new l2("stop", 20);
        l2 l2Var22 = new l2("style", 21);
        l2 l2Var23 = new l2("SWITCH", 22);
        f12368l = l2Var23;
        l2 l2Var24 = new l2("symbol", 23);
        l2 l2Var25 = new l2("text", 24);
        l2 l2Var26 = new l2("textPath", 25);
        l2 l2Var27 = new l2(LinkHeader.Parameters.Title, 26);
        f12369m = l2Var27;
        l2 l2Var28 = new l2("tref", 27);
        l2 l2Var29 = new l2("tspan", 28);
        l2 l2Var30 = new l2("use", 29);
        l2 l2Var31 = new l2("view", 30);
        l2 l2Var32 = new l2("UNSUPPORTED", 31);
        f12370n = l2Var32;
        f12372p = new l2[]{l2Var, l2Var2, l2Var3, l2Var4, l2Var5, l2Var6, l2Var7, l2Var8, l2Var9, l2Var10, l2Var11, l2Var12, l2Var13, l2Var14, l2Var15, l2Var16, l2Var17, l2Var18, l2Var19, l2Var20, l2Var21, l2Var22, l2Var23, l2Var24, l2Var25, l2Var26, l2Var27, l2Var28, l2Var29, l2Var30, l2Var31, l2Var32};
        f12371o = new HashMap();
        for (l2 l2Var33 : values()) {
            if (l2Var33 == f12368l) {
                f12371o.put("switch", l2Var33);
            } else if (l2Var33 != f12370n) {
                f12371o.put(l2Var33.name(), l2Var33);
            }
        }
    }

    public static l2 valueOf(String str) {
        return (l2) Enum.valueOf(l2.class, str);
    }

    public static l2[] values() {
        return (l2[]) f12372p.clone();
    }
}
