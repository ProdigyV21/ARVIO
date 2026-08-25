package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class Label {
    private static final String FIELD_LANGUAGE_INDEX = Util.intToStringMaxRadix(0);
    private static final String FIELD_VALUE_INDEX = Util.intToStringMaxRadix(1);
    public final String language;
    public final String value;

    public Label(String str, String str2) {
        this.language = Util.normalizeLanguageCode(str);
        this.value = str2;
    }

    public static Label fromBundle(Bundle bundle) {
        String string = bundle.getString(FIELD_LANGUAGE_INDEX);
        String string2 = bundle.getString(FIELD_VALUE_INDEX);
        string2.getClass();
        return new Label(string, string2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Label label = (Label) obj;
            if (Objects.equals(this.language, label.language) && Objects.equals(this.value, label.value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.value.hashCode() * 31;
        String str = this.language;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = this.language;
        if (str != null) {
            bundle.putString(FIELD_LANGUAGE_INDEX, str);
        }
        bundle.putString(FIELD_VALUE_INDEX, this.value);
        return bundle;
    }
}
