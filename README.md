[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6696603&assignment_repo_type=AssignmentRepo)
# Maze Run
Maze Run is a 2D top-down game where you traverse a maze full of obstacles and enemies within a limited amount of time to win. 

You are given one life as you face a variety of enemies in 3 different levels so make sure not to get hit or it's game over! Luckily, you are very agile and have access to the sprint ability. 
Use this ability by pressing shift to move quicker which may help you avoid enemies and obstacles as you look for the highlighted goal.

There are two difficulties which are easy and hard.

        Easy - less enemies, more time
        Hard - more enemies, less time

Scoring is determined by # of coins collected + time remaining + difficulty multiplier.

        More coins = more score
        More time = more score
        Easy multiplier = none
        Hard multiplier = 1.2x 

Limitations:

        - Switching directions simultaneously can cause the player to stop for a very short but noticible time
        - Pressing keys other than arrows while changing direction causes player to stop moving
        - Game must be played in 800x800 resolution with 18 rows & columns as levels do not scale to screen  
        - Rows and columns are 2 less than value inputed 
