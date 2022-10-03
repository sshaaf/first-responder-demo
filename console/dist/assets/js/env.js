// blank values are replaced at runtime by the set-config.js node script
(function(window) {
  window._env = window._env || {};

  window._env.url = 'https://sso-sso.apps.erdemo-df1a.open.redhat.com/auth';
  window._env.realm = 'emergency-realm';
  window._env.clientId = 'js';
  window._env.enabled = 'false';
  window._env.accessToken = 'pk.eyJ1Ijoic3NoYWFmIiwiYSI6ImNsOGQ1ZW00MDAyZHQzcHBpMno1czNmbnYifQ.fwLESE8f14UL2l2dxWUQyg';
  window._env.pollingInterval = '10000';
})(this);
