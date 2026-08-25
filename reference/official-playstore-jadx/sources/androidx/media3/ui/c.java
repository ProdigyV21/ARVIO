package androidx.media3.ui;

import android.view.View;
import androidx.media3.ui.PlayerControlView;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4238i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4239l;

    public /* synthetic */ c(Object obj, int i10) {
        this.f4238i = i10;
        this.f4239l = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f4238i) {
            case 0:
                ((PlayerControlView.AudioTrackSelectionAdapter) this.f4239l).lambda$onBindViewHolderAtZeroPosition$0(view);
                break;
            case 1:
                ((PlayerControlView.SettingViewHolder) this.f4239l).lambda$new$0(view);
                break;
            case 2:
                ((PlayerControlView.TextTrackSelectionAdapter) this.f4239l).lambda$onBindViewHolderAtZeroPosition$0(view);
                break;
            case 3:
                ((PlayerControlViewLayoutManager) this.f4239l).onOverflowButtonClick(view);
                break;
            default:
                ((PlayerControlView) this.f4239l).onFullscreenButtonClicked(view);
                break;
        }
    }
}
