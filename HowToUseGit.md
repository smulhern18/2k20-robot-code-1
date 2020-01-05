# Team 190 and Git

Here's an overview of how we use Git. I hope this helps.

[Download Git from here first!](https://git-scm.com/)

## Cloning
- You need to get the project onto your own machine. To do this, you'll need to "clone" our code off of GitHub.
     - Basically downloads a copy onto your computer from Github
- If you are on Mac, open terminal. If you are on Windows, open CMD.
- In terminal/CMD, copy and paste: `git clone https://github.com/Team-190/2k20-robot-code.git`
- Hit enter
- The code is now downloaded. Navigate into the directory with `cd 2k20-robot-code` (cd = [c]hange [d]irectory)
     - On my computer (Sully), Github repositories save to ~/git/, where ~ is my user directory
## Branching
- If you want to make your own change, you need your own version of code, called a "branch"
- Create a new branch in terminal/CMD by typing `git checkout -b <<BRANCH_NAME_HERE>>`
- If you already have a branch, switch branches by typing `git checkout <<BRANCH_NAME_HERE>>`
## Staging
- After you have made some changes and are ready to move them to GitHub, you first need to choose which files you want to add.
- Run the command: `git status` in the terminal/commandline to see what has changed.
- Then to add individual files, the command: `git add [filename]`, repeated for each file
     - to add all files with changes, use: `git add .`
- After the files have been added, check with 'git status' again, the files you have added should be green
- Then run the command: `git commit -m "MESSAGE HERE"`, where you replace the message with the answer to this question: "if you use these changes it will..."
