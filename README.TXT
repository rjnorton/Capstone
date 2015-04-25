Overview: 
My capstone is a simulation of a solar system. Initially it starts out as a large amount of very small
particles, and aggregates to form a sun and planets. Sometimes it works better than other times though.

Concepts:
This project contains the Viewer-Frame-Component scheme that we learned in class, along with anonymous
classes, mouse/keyboard events, and required research of the law of universal gravitation and several
classes that I hadn't known about like Timer and TimerTask for use with FPS calculation.

Algorithm:
The main algorithm I use for gravity is -
Calculate the total force on a planet from all other planets to get acceleration
Add the acceleration to the velocity of the planet
Add the velocity of the planet to the position

This starts out very slow initially because it is O(n^2) because of the nested for loop but speeds up
eventually

Instructions:
To change the initial number of planets, just change the number assigned to the final int NUM_PLANETS
in SolarSystemComponent

To add a planet hold right click until prompted for the mass and velocity of the planet.

Arrow keys to move the camera around

Going fullscreen allows you to see more