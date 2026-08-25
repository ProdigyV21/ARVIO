package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionResult;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsCallback;

/* JADX INFO: loaded from: classes4.dex */
public final class w implements com.google.android.gms.common.api.internal.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DeviceSuggestionResult f13647b;

    public /* synthetic */ w(x xVar, DeviceSuggestionResult deviceSuggestionResult, int i10) {
        this.f13646a = i10;
        this.f13647b = deviceSuggestionResult;
    }

    @Override // com.google.android.gms.common.api.internal.m
    public final /* bridge */ /* synthetic */ void a(Object obj) {
        switch (this.f13646a) {
            case 0:
                ((DeviceSuggestionsCallback) obj).onDeviceSuggestionReceived(this.f13647b);
                break;
            default:
                ((DeviceSuggestionsCallback) obj).onDeviceSuggestionExpired(this.f13647b);
                break;
        }
    }

    @Override // com.google.android.gms.common.api.internal.m
    public final void b() {
        switch (this.f13646a) {
            case 0:
                a0.f13156b.w("Failed to notify listener for onDeviceSuggestionReceived", new Object[0]);
                break;
            default:
                a0.f13156b.w("Failed to notify listener for onDeviceSuggestionExpired", new Object[0]);
                break;
        }
    }
}
