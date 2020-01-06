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
## Committing
- After the files have been added, check with 'git status' again, the files you have added should be green
- Now, save your changes by "committing"
- Then run the command: `git commit -m "MESSAGE HERE"`, where you replace the message with the answer to this question: "if you use these changes it will..."
## Pushing
- To get your changes onto GitHub, you must upload your code by "pushing"
- Run `git push origin <<BRANCH_NAME>>`
## Pulling
- If someone made a change in the branch you are working on and you want to use them, you must download their changes by "pulling"
- Run `git pull origin <<BRANCH_NAME>>`
## Stashing
- If you made a change and committed it, but realized it's really bad and want to get rid of it, no worries!
- To get rid of all of your changes since the last time you pulled, run `git stash`
## Pull Requests
- If you want to have your changes merged with the "master" branch, you can do that by submitting a "Pull Request"
- Go to GitHub.com, view your branch, and click "Submit Pull Request"
- Describe what your changes do, and submit it.
- A mentor or Grant will review the PR, and merge it if it's good. 
     - If not, they will leave some comments (or changes) and you should go back and review with the reviewer

#Reference
- `git clone https://github.com/Team-190/2k20-robot-code.git`
- `git checkout -b <<BRANCH_NAME_HERE>>`
- `git checkout <<BRANCH_NAME_HERE>>`
- `git status`
- `git add [filename]`
- `git add .`
- `git commit -m "MESSAGE HERE"`
- `git push origin <<BRANCH_NAME>>`
- `git pull origin <<BRANCH_NAME>>`
- `git stash`
