package androidx.media3.common.text;

import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import androidx.media3.common.util.Util;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
final class CustomSpanBundler {
    private static final int HORIZONTAL_TEXT_IN_VERTICAL_CONTEXT = 3;
    private static final int RUBY = 1;
    private static final int TEXT_EMPHASIS = 2;
    private static final int UNKNOWN = -1;
    private static final int VOICE = 4;
    private static final String FIELD_START_INDEX = Util.intToStringMaxRadix(0);
    private static final String FIELD_END_INDEX = Util.intToStringMaxRadix(1);
    private static final String FIELD_FLAGS = Util.intToStringMaxRadix(2);
    private static final String FIELD_TYPE = Util.intToStringMaxRadix(3);
    private static final String FIELD_PARAMS = Util.intToStringMaxRadix(4);

    private CustomSpanBundler() {
    }

    public static ArrayList<Bundle> bundleCustomSpans(Spanned spanned) {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        for (RubySpan rubySpan : (RubySpan[]) spanned.getSpans(0, spanned.length(), RubySpan.class)) {
            arrayList.add(spanToBundle(spanned, rubySpan, 1, rubySpan.toBundle()));
        }
        for (TextEmphasisSpan textEmphasisSpan : (TextEmphasisSpan[]) spanned.getSpans(0, spanned.length(), TextEmphasisSpan.class)) {
            arrayList.add(spanToBundle(spanned, textEmphasisSpan, 2, textEmphasisSpan.toBundle()));
        }
        for (HorizontalTextInVerticalContextSpan horizontalTextInVerticalContextSpan : (HorizontalTextInVerticalContextSpan[]) spanned.getSpans(0, spanned.length(), HorizontalTextInVerticalContextSpan.class)) {
            arrayList.add(spanToBundle(spanned, horizontalTextInVerticalContextSpan, 3, null));
        }
        for (VoiceSpan voiceSpan : (VoiceSpan[]) spanned.getSpans(0, spanned.length(), VoiceSpan.class)) {
            arrayList.add(spanToBundle(spanned, voiceSpan, 4, voiceSpan.toBundle()));
        }
        return arrayList;
    }

    private static Bundle spanToBundle(Spanned spanned, Object obj, int i10, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt(FIELD_START_INDEX, spanned.getSpanStart(obj));
        bundle2.putInt(FIELD_END_INDEX, spanned.getSpanEnd(obj));
        bundle2.putInt(FIELD_FLAGS, spanned.getSpanFlags(obj));
        bundle2.putInt(FIELD_TYPE, i10);
        if (bundle != null) {
            bundle2.putBundle(FIELD_PARAMS, bundle);
        }
        return bundle2;
    }

    public static void unbundleAndApplyCustomSpan(Bundle bundle, Spannable spannable) {
        int i10 = bundle.getInt(FIELD_START_INDEX);
        int i11 = bundle.getInt(FIELD_END_INDEX);
        int i12 = bundle.getInt(FIELD_FLAGS);
        int i13 = bundle.getInt(FIELD_TYPE, -1);
        Bundle bundle2 = bundle.getBundle(FIELD_PARAMS);
        if (i13 == 1) {
            bundle2.getClass();
            spannable.setSpan(RubySpan.fromBundle(bundle2), i10, i11, i12);
            return;
        }
        if (i13 == 2) {
            bundle2.getClass();
            spannable.setSpan(TextEmphasisSpan.fromBundle(bundle2), i10, i11, i12);
        } else if (i13 == 3) {
            spannable.setSpan(new HorizontalTextInVerticalContextSpan(), i10, i11, i12);
        } else {
            if (i13 != 4) {
                return;
            }
            bundle2.getClass();
            spannable.setSpan(VoiceSpan.fromBundle(bundle2), i10, i11, i12);
        }
    }
}
