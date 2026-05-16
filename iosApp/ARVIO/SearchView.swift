import SwiftUI

struct SearchView: View {
    @State private var query = ""

    var body: some View {
        VStack(alignment: .leading, spacing: 22) {
            Text("Search")
                .font(.system(size: 38, weight: .bold))
                .foregroundStyle(ArvioTheme.textPrimary)

            TextField("Search movies, shows and episodes", text: $query)
                .textInputAutocapitalization(.never)
                .padding(16)
                .background(RoundedRectangle(cornerRadius: 8).fill(ArvioTheme.panel))
                .overlay(RoundedRectangle(cornerRadius: 8).stroke(ArvioTheme.border, lineWidth: 1))
                .foregroundStyle(ArvioTheme.textPrimary)

            MediaRail(title: query.isEmpty ? "Suggested" : "Results", items: featuredItems)

            Spacer()
        }
        .padding(28)
    }
}
