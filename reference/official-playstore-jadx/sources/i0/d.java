package i0;

import android.content.res.ColorStateList;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shader f15985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f15986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f15987c;

    public d(Shader shader, ColorStateList colorStateList, int i10) {
        this.f15985a = shader;
        this.f15986b = colorStateList;
        this.f15987c = i10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x021c, code lost:
    
        r0 = new fi.iki.elonen.f();
        r0.f15416i = new int[]{r6, r5, r12};
        r0.f15417l = new float[]{0.0f, 0.5f, 1.0f};
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0230, code lost:
    
        r0 = new fi.iki.elonen.f();
        r0.f15416i = new int[]{r6, r12};
        r15 = 2;
        r0.f15417l = new float[]{0.0f, 1.0f};
        r14 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0244, code lost:
    
        if (r11 == r14) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0246, code lost:
    
        if (r11 == r15) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0248, code lost:
    
        r16 = (int[]) r0.f15416i;
        r17 = (float[]) r0.f15417l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0256, code lost:
    
        if (r10 == r14) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0258, code lost:
    
        if (r10 == r15) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x025a, code lost:
    
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0267, code lost:
    
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x026a, code lost:
    
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x026d, code lost:
    
        r11 = new android.graphics.LinearGradient(r21, r22, r26, r27, r16, r17, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0271, code lost:
    
        r11 = new android.graphics.SweepGradient(r8, r9, (int[]) r0.f15416i, (float[]) r0.f15417l);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0283, code lost:
    
        if (r25 <= 0.0f) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0285, code lost:
    
        r20 = (int[]) r0.f15416i;
        r21 = (float[]) r0.f15417l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0294, code lost:
    
        if (r10 == 1) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0297, code lost:
    
        if (r10 == 2) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0299, code lost:
    
        r0 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02a4, code lost:
    
        r0 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x02a7, code lost:
    
        r0 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x02aa, code lost:
    
        r11 = new android.graphics.RadialGradient(r8, r9, r25, r20, r21, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x02b6, code lost:
    
        return new i0.d(r11, null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x02be, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01db, code lost:
    
        if (r13.size() <= 0) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01dd, code lost:
    
        r0 = new fi.iki.elonen.f();
        r1 = r13.size();
        r0.f15416i = new int[r1];
        r0.f15417l = new float[r1];
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ef, code lost:
    
        if (r2 >= r1) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01f1, code lost:
    
        ((int[]) r0.f15416i)[r2] = ((java.lang.Integer) r13.get(r2)).intValue();
        ((float[]) r0.f15417l)[r2] = ((java.lang.Float) r7.get(r2)).floatValue();
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0214, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0215, code lost:
    
        if (r0 == null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0217, code lost:
    
        r14 = 1;
        r15 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x021a, code lost:
    
        if (r20 == false) goto L101;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static i0.d a(android.content.res.Resources r30, int r31, android.content.res.Resources.Theme r32) {
        /*
            Method dump skipped, instruction units count: 762
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.d.a(android.content.res.Resources, int, android.content.res.Resources$Theme):i0.d");
    }

    public final boolean b() {
        ColorStateList colorStateList;
        return this.f15985a == null && (colorStateList = this.f15986b) != null && colorStateList.isStateful();
    }
}
