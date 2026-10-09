(function () {
  "use strict";
  var links = window.ArvioOpenLinks.destinations(window.location.search);
  if (!links) {
    document.getElementById("heading").textContent = "This link needs a title.";
    document.getElementById("message").textContent = "The title link is missing or invalid. Ask the sender for a new link, or open ARVIO directly.";
    return;
  }
  var nativeLink = document.getElementById("native");
  nativeLink.href = /Android/i.test(navigator.userAgent) ? links.androidIntent : links.native;
  document.getElementById("web").href = links.web;
  document.getElementById("install").href = links.install;
  var target = links.target;
  var reference = target.imdb ? "IMDb " + target.imdb : (target.type === "movie" ? "Movie" : "Series") + " · TMDB " + target.id;
  if (target.season !== undefined) reference += " · Season " + target.season;
  if (target.episode !== undefined) reference += " · Episode " + target.episode;
  document.getElementById("reference").textContent = reference;
  for (var id of ["reference", "actions", "app-note"]) document.getElementById(id).hidden = false;
})();
