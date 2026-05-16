import SwiftUI

struct WatchlistView: View {
    var body: some View {
        CatalogView(
            title: "Watchlist",
            subtitle: "Movies and shows saved for later.",
            items: featuredItems + continueWatchingItems
        )
    }
}
