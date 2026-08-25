package androidx.media3.extractor.text.ssa;

import ac.b;
import android.text.TextUtils;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int layerIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int i10, int i11, int i12, int i13, int i14, int i15) {
        this.layerIndex = i10;
        this.startTimeIndex = i11;
        this.endTimeIndex = i12;
        this.styleIndex = i13;
        this.textIndex = i14;
        this.length = i15;
    }

    public static SsaDialogueFormat fromFormatLine(String str) {
        b.j(str.startsWith("Format:"));
        String[] strArrSplit = TextUtils.split(str.substring(7), ",");
        int i10 = -1;
        int i11 = -1;
        int i12 = -1;
        int i13 = -1;
        int i14 = -1;
        for (int i15 = 0; i15 < strArrSplit.length; i15++) {
            String strS = a.S(strArrSplit[i15].trim());
            strS.getClass();
            switch (strS) {
                case "end":
                    i12 = i15;
                    break;
                case "text":
                    i14 = i15;
                    break;
                case "layer":
                    i10 = i15;
                    break;
                case "start":
                    i11 = i15;
                    break;
                case "style":
                    i13 = i15;
                    break;
            }
        }
        if (i11 == -1 || i12 == -1 || i14 == -1) {
            return null;
        }
        return new SsaDialogueFormat(i10, i11, i12, i13, i14, strArrSplit.length);
    }
}
