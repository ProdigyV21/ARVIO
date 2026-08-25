package androidx.core.app;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class u0 extends y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f2056a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f2057b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public w1 f2058c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f2059d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Boolean f2060e;

    @Override // androidx.core.app.y0
    public final void addCompatExtras(Bundle bundle) {
        super.addCompatExtras(bundle);
        bundle.putCharSequence("android.selfDisplayName", this.f2058c.f2061a);
        bundle.putBundle("android.messagingStyleUser", this.f2058c.b());
        bundle.putCharSequence("android.hiddenConversationTitle", this.f2059d);
        if (this.f2059d != null && this.f2060e.booleanValue()) {
            bundle.putCharSequence("android.conversationTitle", this.f2059d);
        }
        ArrayList arrayList = this.f2056a;
        if (!arrayList.isEmpty()) {
            bundle.putParcelableArray("android.messages", t0.a(arrayList));
        }
        ArrayList arrayList2 = this.f2057b;
        if (!arrayList2.isEmpty()) {
            bundle.putParcelableArray("android.messages.historic", t0.a(arrayList2));
        }
        Boolean bool = this.f2060e;
        if (bool != null) {
            bundle.putBoolean("android.isGroupConversation", bool.booleanValue());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
    @Override // androidx.core.app.y0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void apply(androidx.core.app.t r10) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.u0.apply(androidx.core.app.t):void");
    }

    public final SpannableStringBuilder c(t0 t0Var) {
        String str = androidx.core.text.b.f2176b;
        androidx.core.text.b bVar = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? androidx.core.text.b.f2179e : androidx.core.text.b.f2178d;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        w1 w1Var = t0Var.f2045c;
        CharSequence charSequence = w1Var == null ? "" : w1Var.f2061a;
        int i10 = -16777216;
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = this.f2058c.f2061a;
            int i11 = this.mBuilder.f1982v;
            if (i11 != 0) {
                i10 = i11;
            }
        }
        SpannableStringBuilder spannableStringBuilderC = bVar.c(charSequence);
        spannableStringBuilder.append((CharSequence) spannableStringBuilderC);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i10), null), spannableStringBuilder.length() - spannableStringBuilderC.length(), spannableStringBuilder.length(), 33);
        CharSequence charSequence2 = t0Var.f2043a;
        spannableStringBuilder.append((CharSequence) "  ").append((CharSequence) bVar.c(charSequence2 != null ? charSequence2 : ""));
        return spannableStringBuilder;
    }

    @Override // androidx.core.app.y0
    public final void clearCompatExtraKeys(Bundle bundle) {
        super.clearCompatExtraKeys(bundle);
        bundle.remove("android.messagingStyleUser");
        bundle.remove("android.selfDisplayName");
        bundle.remove("android.conversationTitle");
        bundle.remove("android.hiddenConversationTitle");
        bundle.remove("android.messages");
        bundle.remove("android.messages.historic");
        bundle.remove("android.isGroupConversation");
    }

    @Override // androidx.core.app.y0
    public final String getClassName() {
        return "androidx.core.app.NotificationCompat$MessagingStyle";
    }

    @Override // androidx.core.app.y0
    public final void restoreFromCompatExtras(Bundle bundle) {
        super.restoreFromCompatExtras(bundle);
        ArrayList arrayList = this.f2056a;
        arrayList.clear();
        if (bundle.containsKey("android.messagingStyleUser")) {
            this.f2058c = w1.a(bundle.getBundle("android.messagingStyleUser"));
        } else {
            String string = bundle.getString("android.selfDisplayName");
            w1 w1Var = new w1();
            w1Var.f2061a = string;
            w1Var.f2062b = null;
            w1Var.f2063c = null;
            w1Var.f2064d = null;
            w1Var.f2065e = false;
            w1Var.f2066f = false;
            this.f2058c = w1Var;
        }
        CharSequence charSequence = bundle.getCharSequence("android.conversationTitle");
        this.f2059d = charSequence;
        if (charSequence == null) {
            this.f2059d = bundle.getCharSequence("android.hiddenConversationTitle");
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
        if (parcelableArray != null) {
            arrayList.addAll(t0.b(parcelableArray));
        }
        Parcelable[] parcelableArray2 = bundle.getParcelableArray("android.messages.historic");
        if (parcelableArray2 != null) {
            this.f2057b.addAll(t0.b(parcelableArray2));
        }
        if (bundle.containsKey("android.isGroupConversation")) {
            this.f2060e = Boolean.valueOf(bundle.getBoolean("android.isGroupConversation"));
        }
    }
}
