package kotlin.text;

import java.util.List;
import java.util.regex.Matcher;
import kotlin.collections.n0;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matcher f19946a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f19947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f19948c = new j(this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public n0 f19949d;

    public k(Matcher matcher, CharSequence charSequence) {
        this.f19946a = matcher;
        this.f19947b = charSequence;
    }

    @Override // kotlin.text.i
    public final kb.d a() {
        return new kb.d(this, 2);
    }

    @Override // kotlin.text.i
    public final List b() {
        if (this.f19949d == null) {
            this.f19949d = new n0(this, 1);
        }
        return this.f19949d;
    }

    @Override // kotlin.text.i
    public final j c() {
        return this.f19948c;
    }

    public final x7.i d() {
        Matcher matcher = this.f19946a;
        return qb.d.X(matcher.start(), matcher.end());
    }

    @Override // kotlin.text.i
    public final String getValue() {
        return this.f19946a.group();
    }

    @Override // kotlin.text.i
    public final k next() {
        Matcher matcher = this.f19946a;
        int iEnd = matcher.end() + (matcher.end() == matcher.start() ? 1 : 0);
        CharSequence charSequence = this.f19947b;
        if (iEnd > charSequence.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(charSequence);
        if (matcher2.find(iEnd)) {
            return new k(matcher2, charSequence);
        }
        return null;
    }
}
