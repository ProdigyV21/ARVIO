package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public interface Predicate<T> {

    /* JADX INFO: renamed from: com.discord.org.webrtc.Predicate$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Predicate a(final Predicate predicate, final Predicate predicate2) {
            return new Predicate() { // from class: com.discord.org.webrtc.Predicate.2
                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate and(Predicate predicate3) {
                    return CC.a(this, predicate3);
                }

                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate negate() {
                    return CC.b(this);
                }

                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate or(Predicate predicate3) {
                    return CC.c(this, predicate3);
                }

                @Override // com.discord.org.webrtc.Predicate
                public boolean test(Object obj) {
                    return Predicate.this.test(obj) && predicate2.test(obj);
                }
            };
        }

        public static Predicate b(final Predicate predicate) {
            return new Predicate() { // from class: com.discord.org.webrtc.Predicate.3
                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate and(Predicate predicate2) {
                    return CC.a(this, predicate2);
                }

                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate negate() {
                    return CC.b(this);
                }

                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate or(Predicate predicate2) {
                    return CC.c(this, predicate2);
                }

                @Override // com.discord.org.webrtc.Predicate
                public boolean test(Object obj) {
                    return !Predicate.this.test(obj);
                }
            };
        }

        public static Predicate c(final Predicate predicate, final Predicate predicate2) {
            return new Predicate() { // from class: com.discord.org.webrtc.Predicate.1
                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate and(Predicate predicate3) {
                    return CC.a(this, predicate3);
                }

                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate negate() {
                    return CC.b(this);
                }

                @Override // com.discord.org.webrtc.Predicate
                public final /* synthetic */ Predicate or(Predicate predicate3) {
                    return CC.c(this, predicate3);
                }

                @Override // com.discord.org.webrtc.Predicate
                public boolean test(Object obj) {
                    return Predicate.this.test(obj) || predicate2.test(obj);
                }
            };
        }
    }

    Predicate<T> and(Predicate<? super T> predicate);

    Predicate<T> negate();

    Predicate<T> or(Predicate<? super T> predicate);

    boolean test(T t2);
}
