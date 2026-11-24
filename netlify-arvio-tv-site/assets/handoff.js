// The hero handoff: shortly after the page loads, the series on the TV pauses and resumes on the iPad.
// It plays once. Without JavaScript, with reduced motion, with motion paused or on phones the stage
// simply shows its final state (--p defaults to 1 in the stylesheet).
(function () {
  const section = document.querySelector('.handoff');
  if (!section) return;
  const wide = window.matchMedia('(min-width: 721px)');
  const reduce = window.matchMedia('(prefers-reduced-motion: reduce)');
  const ease = t => (t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2);
  const DELAY = 1100, DURATION = 1800;
  let frame = 0;

  function settle() {
    cancelAnimationFrame(frame);
    section.style.removeProperty('--p');
  }

  function play() {
    if (reduce.matches || !wide.matches || document.body.classList.contains('paused')) return;
    let start = null;
    section.style.setProperty('--p', '0');
    const step = now => {
      if (start === null) start = now;
      const t = Math.min(1, Math.max(0, (now - start - DELAY) / DURATION));
      section.style.setProperty('--p', ease(t).toFixed(4));
      if (t < 1) frame = requestAnimationFrame(step);
      else settle();
    };
    frame = requestAnimationFrame(step);
  }

  document.addEventListener('arvio:motionchange', event => { if (event.detail.paused) settle(); });
  wide.addEventListener('change', settle);
  play();
})();
