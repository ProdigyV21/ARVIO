(function (root) {
  "use strict";
  var fields = ["imdb", "type", "id", "season", "episode"];
  function parse(search) {
    if (typeof search !== "string" || search.length > 4096) return null;
    var params = new URLSearchParams(search);
    var values = {};
    for (var key of fields) {
      if (params.getAll(key).length > 1) return null;
      values[key] = params.get(key);
    }
    var target = {};
    if (values.imdb !== null) {
      if (!/^tt\d{5,12}$/.test(values.imdb) || values.type !== null || values.id !== null) return null;
      target.imdb = values.imdb;
    } else {
      if (values.type !== "movie" && values.type !== "tv") return null;
      if (!/^[1-9]\d{0,9}$/.test(values.id || "") || Number(values.id) > 2147483647) return null;
      target.type = values.type;
      target.id = Number(values.id);
    }
    for (var coordinate of ["season", "episode"]) {
      var raw = values[coordinate];
      if (raw === null || raw === "") continue;
      if (!/^(0|[1-9]\d{0,4})$/.test(raw)) return null;
      var number = Number(raw);
      if (number > 10000 || (coordinate === "episode" && number === 0)) return null;
      target[coordinate] = number;
    }
    if (target.episode !== undefined && target.season === undefined) return null;
    if (target.type === "movie" && (target.season !== undefined || target.episode !== undefined)) return null;
    return target;
  }
  function query(target) {
    var params = new URLSearchParams();
    for (var key of fields) if (target[key] !== undefined) params.set(key, String(target[key]));
    return params.toString();
  }
  function destinations(search) {
    var target = parse(search);
    if (!target) return null;
    var encoded = query(target);
    var source = new URLSearchParams(search).get("utm_source") || "partner";
    if (!/^[a-z0-9_-]{1,40}$/.test(source)) source = "partner";
    var install = new URL("https://play.google.com/store/apps/details");
    var campaign = new URLSearchParams({ utm_source: source, utm_medium: "integration", utm_campaign: "open_in_arvio" });
    install.searchParams.set("id", "com.arvio.tv");
    install.searchParams.set("referrer", campaign.toString());
    return {
      target: target,
      native: "arvio://open?" + encoded,
      web: "https://web.arvio.tv/?open=1&" + encoded + "&" + campaign.toString(),
      install: install.href,
      androidIntent: "intent://open?" + encoded + "#Intent;scheme=arvio;package=com.arvio.tv;S.browser_fallback_url=" + encodeURIComponent(install.href) + ";end"
    };
  }
  var api = { parse: parse, query: query, destinations: destinations };
  if (typeof module !== "undefined" && module.exports) module.exports = api;
  else root.ArvioOpenLinks = api;
})(typeof globalThis !== "undefined" ? globalThis : this);
