<%--
  Created by IntelliJ IDEA.
  User: Minh Duc
  Date: 10/7/2022
  Time: 8:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
    <style>
        body {
            background-color: #ededed;
        }
        h1 {
            font-family: 'Open Sans', sans-serif;
            font-size: 13pt;
            font-weight: 600;
            text-transform: uppercase;
            color: white;
            cursor: default;
        }
        h4 {
            font-family: 'Open Sans', sans-serif;
            font-size: 8pt;
            font-weight: 400;
            cursor: default;
        }
        h2 {
            font-family: 'Open Sans', sans-serif;
            font-size: 13pt;
            font-weight: 300;
            color: white;
            cursor: default;
        }
        .player {
            height: 190px;
            width: 430px;
            background-color: #1e2125;
            position: absolute;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
        }
        .player ul {
            list-style: none;
        }
        .player ul li {
            display: inline-block;
        }
        .cover {
            position: absolute;
            top: 0;
            left: 0;
        }
        .cover img {
            height: 190px;
            width: 190px;
        }
        .info h1 {
            margin-top: 15px;
            margin-left: 180px;
            line-height: 0;
        }
        .info h4 {
            margin-left: 180px;
            line-height: 20px;
            color: #636367;
        }
        .info h2 {
            margin-left: 180px;
        }
        .button-items {
            margin-left: 180px;
        }
        #slider {
            width: 182px;
            height: 4px;
            background: #151518;
            border-radius: 2px;
        }
        #slider div {
            width: 4px;
            height: 4px;
            margin-top: 1px;
            background: #ef6dbc;
            border-radius: 2px;
        }
        #timer {
            color: #494b4e;
            line-height: 0;
            font-size: 9pt;
            float: right;
            font-family: Arial, Sans-Serif;
        }
        .controls {
            margin-top: 20px;
        }
        .controls svg:nth-child(2) {
            margin-left: 5px;
            margin-right: 5px;
        }
        #play {
            padding: 0 3px;
            width: 30px;
            height: 30px;
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
        }
        #play g {
            stroke: #fefefe;
            stroke-width: 1;
            stroke-miterlimit: 10;
        }
        #play g path {
            fill: #fefefe;
        }
        #play:hover {
            cursor: pointer;
        }
        #play:hover g {
            stroke: #8f4da9;
            cursor: pointer;
        }
        #play:hover g path {
            fill: #9b59b6;
            cursor: pointer;
        }
        .step-backward {
            width: 18px;
            height: 18px;
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
            margin-bottom: 5px;
        }
        .step-backward g polygon {
            fill: #fefefe;
        }
        .step-foreward {
            width: 18px;
            height: 18px;
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
            margin-bottom: 5px;
        }
        .step-foreward g polygon {
            fill: #fefefe;
        }
        #pause {
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
            width: 30px;
            height: 30px;
            position: absolute;
            margin-left: -38px;
            cursor: pointer;
        }
        #pause rect {
            fill: white;
        }
        #pause:hover rect {
            fill: #8f4da9;
        }
        .step-backward g polygon:hover, .step-foreward g polygon:hover {
            fill: #ef6dbc;
            cursor: pointer;
        }
        .social {
            text-align: center;
        }
        .twitter {
            color: #bdbdbd;
            font-family: sans-serif;
            text-decoration: none;
        }
        .twitter:hover {
            color: #ecf0f1;
        }
        .github {
            color: #bdbdbd;
            font-family: sans-serif;
            text-decoration: none;
        }
        .github:hover {
            color: #ecf0f1;
        }
        p {
            color: #bdbdbd;
        }
        #skip {
            float: right;
            margin-top: 10px;
        }
        #skip p {
            color: #2980b9;
        }
        #skip p:hover {
            color: #e74c3c;
            cursor: pointer;
        }
        .expend {
            padding: 0.5px;
            cursor: pointer;
        }
        .expend svg:hover g polygon {
            fill: #ef6dbc;
        }
    </style>
  </head>
  <body>
  <div class="player">
      <ul>
          <li class="cover"><img src="http://i1285.photobucket.com/albums/a583/TheGreatOzz1/Hosted-Images/Noisy-Freeks-Image_zps4kilrxml.png"/></li>
          <li class="info">
              <h1>The Noisy Freaks</h1>
              <h4>Premiere</h4>
              <h2>I Need You Back</h2>
              <div class="button-items">
                  <audio id="music" preload="auto" loop="false">
                      <source src="https://dl.dropbox.com/s/oswkgcw749xc8xs/The-Noisy-Freaks.mp3?dl=1" type="audio/mp3">
                      <source src="https://dl.dropbox.com/s/75jpngrgnavyu7f/The-Noisy-Freaks.ogg?dl=1" type="audio/ogg">
                  </audio>
                  <div id="slider"><div id="elapsed"></div></div>
                  <p id="timer">0:00</p>
                  <div class="controls">
               <span class="expend"><svg class="step-backward" viewBox="0 0 25 25" xml:space="preserve">
                  <g><polygon points="4.9,4.3 9,4.3 9,11.6 21.4,4.3 21.4,20.7 9,13.4 9,20.7 4.9,20.7"/></g>
               </svg></span>

                      <svg id="play" viewBox="0 0 25 25" xml:space="preserve">
                   <defs><rect x="-49.5" y="-132.9" width="446.4" height="366.4"/></defs>
                          <g><circle fill="none" cx="12.5" cy="12.5" r="10.8"/>
                              <path fill-rule="evenodd" clip-rule="evenodd" d="M8.7,6.9V18c0,0,0.2,1.4,1.8,0l8.1-4.8c0,0,1.2-1.1-1-2L9.8,6.5 C9.8,6.5,9.1,6,8.7,6.9z"/>
                          </g>
               </svg>


                      <svg id="pause" viewBox="0 0 25 25" xml:space="preserve">
                  <g>
                      <rect x="6" y="4.6" width="3.8" height="15.7"/>
                      <rect x="14" y="4.6" width="3.9" height="15.7"/>
                  </g>
               </svg>

                      <span class="expend"><svg class="step-foreward" viewBox="0 0 25 25" xml:space="preserve">
                  <g><polygon points="20.7,4.3 16.6,4.3 16.6,11.6 4.3,4.3 4.3,20.7 16.7,13.4 16.6,20.7 20.7,20.7"/></g>
                </svg></span>
                  </div>
              </div>
          </li>
      </ul>
  </div>
  <script>
      var music = document.getElementById("music");
      var playButton = document.getElementById("play");
      var pauseButton = document.getElementById("pause");
      var playhead = document.getElementById("elapsed");
      var timeline = document.getElementById("slider");
      var timer = document.getElementById("timer");
      var duration;
      pauseButton.style.visibility = "hidden";

      var timelineWidth = timeline.offsetWidth - playhead.offsetWidth;
      music.addEventListener("timeupdate", timeUpdate, false);

      function timeUpdate() {
          var playPercent = timelineWidth * (music.currentTime / duration);
          playhead.style.width = playPercent + "px";

          var secondsIn = Math.floor(((music.currentTime / duration) / 3.5) * 100);
          if (secondsIn <= 9) {
              timer.innerHTML = "0:0" + secondsIn;
          } else {
              timer.innerHTML = "0:" + secondsIn;
          }
      }

      playButton.onclick = function() {
          music.play();
          playButton.style.visibility = "hidden";
          pause.style.visibility = "visible";
      }

      pauseButton.onclick = function() {
          music.pause();
          playButton.style.visibility = "visible";
          pause.style.visibility = "hidden";
      }

      music.addEventListener("canplaythrough", function () {
          duration = music.duration;
      }, false);
  </script>
  </body>
</html>
