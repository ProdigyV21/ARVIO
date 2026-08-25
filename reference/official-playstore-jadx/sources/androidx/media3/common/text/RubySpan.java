package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public final class RubySpan implements LanguageFeatureSpan {
    public final int position;
    public final String rubyText;
    private static final String FIELD_TEXT = Util.intToStringMaxRadix(0);
    private static final String FIELD_POSITION = Util.intToStringMaxRadix(1);

    public RubySpan(String str, int i10) {
        this.rubyText = str;
        this.position = i10;
    }

    public static RubySpan fromBundle(Bundle bundle) {
        String string = bundle.getString(FIELD_TEXT);
        string.getClass();
        return new RubySpan(string, bundle.getInt(FIELD_POSITION));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(FIELD_TEXT, this.rubyText);
        bundle.putInt(FIELD_POSITION, this.position);
        return bundle;
    }
}
