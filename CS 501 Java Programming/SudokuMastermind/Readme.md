
December 8, 2023
- 1 -
Sudoku
Mastermind
Version 1
by
Vijay Khot
User's Guide
to JAVA game
December 8, 2023
- 2 -
Table of Contents
1 Introduction 4
1.1 Welcome to Sudoku Mastermind
2 System Requirements 5
2.1 For IntelliJ IDEA
2.1.1 Minimum Requirements
2.1.2 Recommended Requirements
2.2 For Eclipse Java IDE
2.2.1 Minimum and Recommended Requirements
3 Installation 6
3.1 For IntelliJ IDEA
3.1.1 Download the Source Code
3.1.2 Open IntelliJ IDEA
3.1.3 Import Project
3.1.4 Configure JavaFX Dependencies
3.1.5 Build and Run
3.1.6 Enjoy the Game
3.2 For Eclipse Java IDE
3.2.1 Download the Source Code
3.2.2 Open Eclipse
3.2.3 Import Project
3.2.4 Configure JavaFX Dependencies
3.2.5 Build and Run
3.2.6 Enjoy the Game
3.3 JavaFX Dependencies
4 Salient Features 8
4.1 Dynamic Flicker Animation
4.2 Hint Functionality
4.3 Mistakes Counter
4.4 Timer
4.5 Undo Functionality
4.6 Multiple Difficulty Levels
4.7 Intuitive User Interface
4.8 Dynamic Cell Highlights
4.8.1 Mouse Hover Effect
4.8.2 Mistake Highlighting
4.8.3 Correct Number Feedback
4.9 Victory and New Game Options
5 Demonstration - Getting to know Sudoku 10
December 8, 2023
- 3 -
6 Getting Started 11
6.1 Launch the Game
6.2 Welcome Screen
6.3 New Game Screen
6.4 Select Difficulty
6.5 Game Interface
6.6 Controls
6.7 Timer and Mistakes Counter
6.8 Hint Functionality
6.9 Check Solution
6.10 Victory Screen
7 Contact Information 16
December 8, 2023
- 4 -
Introduc)on
Welcome to the world of Sudoku Mastermind! The classic 9x9 Sudoku grid puzzle has
captured the hearts and minds of puzzle enthusiasts around the globe, and now, with our
meticulously crafted Sudoku game developed using JavaFX, you can experience the thrill of
solving Sudoku puzzles right at your fingertips.
Sudoku is a logic-based number-placement puzzle that consists of a 9x9 grid divided into
nine 3x3 sub-grids. The objective is to fill the grid with digits from 1 to 9, ensuring that each
row, each column, and each of the nine 3x3 sub-grids contains all of the digits without
repetition. It's a game of skill, strategy, and patience that exercises your logical thinking and
problem-solving abilities.
Get ready to embark on a journey of logical challenges and entertainment with our Java and
JavaFX Sudoku game. Happy solving!
December 8, 2023
- 5 -
System Requirements
Before diving into the captivating world of Sudoku with our JavaFX Sudoku game, ensure
that your system meets the following requirements, especially because you will need to run
the game using IntelliJ IDEA or Eclipse Java IDE.
For IntelliJ IDEA:
Minimum Requirements:
RAM: 2 GB of free RAM
CPU: Any modern CPU
Disk Space: 3.5 GB
Monitor Resolution: 1024×768
Operating System:
Microsoft Windows 10 (version 1809 or later)
Windows Server 2019 or later
macOS 11.0 or later
Any Linux distribution that supports Gnome, KDE, or Unity DE (excluding distributions
without GLIBC 2.27 or later)
Recommended Requirements:
RAM: 8 GB of total system RAM
CPU: Multi-core CPU. IntelliJ IDEA leverages multithreading for enhanced performance,
making it faster with more CPU cores.
Disk Space: SSD drive with at least 5 GB of free space
Monitor Resolution: 1920×1080
Operating System: Latest 64-bit version of Windows, macOS, or Linux (e.g., Debian,
Ubuntu, or RHEL)
Note: Pre-release versions of operating systems are not supported.
For Eclipse Java IDE:
Minimum and Recommended Requirements:
RAM: Eclipse typically runs well with 2 GB of RAM, but more is recommended for larger
projects.
CPU: Eclipse is not particularly CPU-intensive; therefore, any modern CPU should suffice.
Disk Space: Eclipse itself requires around 300 MB, but additional space is necessary for
workspace and projects.
Monitor Resolution: Eclipse is flexible and can adapt to various resolutions.
Operating System: Eclipse is compatible with various operating systems, including Windows,
macOS, and Linux.
Ensure that your development environment is optimized for the best Sudoku-solving
experience. Get ready to challenge your mind with our Sudoku game, built with precision
using Java and JavaFX.
December 8, 2023
- 6 -
Installation
Installing the JavaFX-based Sudoku game involves a few steps. Follow these instructions to
set up the game on your system, including the necessary JavaFX dependencies.
For IntelliJ IDEA:
Download the Source Code:
Obtain the Sudoku game source code from the provided repository or source.
Open IntelliJ IDEA:
Launch IntelliJ IDEA on your system.
Import Project:
In IntelliJ IDEA, go to File -> New -> Project from Existing Sources.
Navigate to the downloaded Sudoku game source code and select the project.
Configure JavaFX Dependencies:
IntelliJ IDEA may not automatically configure JavaFX dependencies. To set up JavaFX:
Go to File -> Project Structure.
Under "Project," ensure that the Project SDK is set to a compatible Java version.
Under "Libraries," add the JavaFX SDK to the project.
Configure the JavaFX Run Configuration by specifying the main class.
Build and Run:
Click on the "Build" option to compile the project.
Run the game using the configured JavaFX Run Configuration.
Enjoy the Game:
The JavaFX-based Sudoku game should now be up and running. Enjoy playing!
For Eclipse Java IDE:
Download the Source Code:
Acquire the Sudoku game source code from the provided repository or source.
Open Eclipse:
Open Eclipse on your system.
Import Project:
Navigate to File -> Import -> General -> Existing Projects into Workspace.
Select the root directory containing the Sudoku game source code.
Configure JavaFX Dependencies:
Eclipse may not automatically configure JavaFX dependencies. To set up JavaFX:
Right-click on the project -> Build Path -> Configure Build Path.
December 8, 2023
- 7 -
Add the JavaFX SDK to the project under the "Libraries" tab.
Set the JavaFX runtime in the Run Configuration.
Build and Run:
Click on the "Build" or "Run" option to compile and execute the game.
Enjoy the Game:
Your JavaFX-based Sudoku game is ready to be played in Eclipse. Have fun solving puzzles!
JavaFX Dependencies:
Ensure that your system has the JavaFX SDK installed and configured in your development
environment. The game's documentation or the provided source code should specify the
required JavaFX version.
Follow these installation steps carefully, and you'll be ready to immerse yourself in the
Sudoku gaming experience. If you encounter any issues, refer to the troubleshooting section
or consult the documentation for your specific IDE. Happy gaming!
December 8, 2023
- 8 -
Salient Features
Explore the unique and engaging features that make our Sudoku game a standout experience:
1. Dynamic Flicker Animation:
Upon successfully completing a puzzle, revel in a celebratory flicker animation that
dynamically highlights all cells. This visual treat adds a touch of excitement to your victories.
2. Hint Functionality:
Feeling stuck? Utilize the "Hint" feature to receive guidance on the next cell to focus on. The
availability of hints varies with the chosen difficulty level, providing a strategic edge to your
gameplay.
3. Mistakes Counter:
Keep track of your progress with the mistakes counter. Stay mindful of the maximum
allowable mistakes based on the selected difficulty level. If you exceed this limit, you lose!
This limit adds an extra layer of challenge, enhancing the overall Sudoku experience.
4. Timer:
Challenge yourself against the clock! A built-in timer records the duration of your gameplay.
Test your speed and aim for faster solving times as you progress through different difficulty
levels.
5. Undo Functionality:
Fear not the occasional mistake! Our game includes an undo feature, allowing you to correct
errors and refine your strategy without the need to start over.
6. Multiple Difficulty Levels:
Whether you're a Sudoku novice or a seasoned expert, our game offers a range of difficulty
levels. Begin with easy puzzles and progressively challenge yourself with harder ones,
providing endless hours of engaging gameplay.
7. Intuitive User Interface:
Navigate through the game seamlessly with an intuitive user interface. Designed for players
of all ages, the clean layout and easy-to-use controls make Sudoku solving a breeze.
December 8, 2023
- 9 -
8. Dynamic Cell Highlights:
• Mouse Hover Effect: Experience a smooth and visually pleasing effect as you run
your mouse pointer over unsolved cells. The cells dynamically highlight, enhancing
the overall interactive experience.
• Mistake Highlighting: When a mistake is made, the respective cell is temporarily
highlighted in red, signaling an error and prompting the player to correct the entry.
• Correct Number Feedback: Upon inputting a correct number, the cell is highlighted
in green, and the correct number text appears in blue. This provides a sense of
accomplishment, motivating players to continue solving the puzzle with confidence.
9. Victory and New Game Options:
Experience a moment of triumph upon completing a puzzle. The game congratulates you on
your victory and presents options to start a new game or exit, ensuring a smooth transition to
your next Sudoku challenge.
Discover these salient features, including the dynamic cell highlights, that elevate your
Sudoku game to new heights. Immerse yourself in a world of logic and fun as you explore
each facet of the gameplay experience.
Enjoy solving Sudoku like never before!
December 8, 2023
- 10 -
Demonstration - Getting to know Sudoku
1. Demonstration:
To solve a regular Sudoku puzzle, place a
number into each cell of the diagram so
that each row across, each column down,
and each block within the larger diagram
(there are 9 of these) will contain every
number from 1 through 9. In other words,
no number may appear more than once in
any row, column, or block. Complete each
puzzle with the missing numbers that will
lead to the correct solution.
For example, look at the ninth column of
the example puzzle to the right.
There are clues in the puzzle that will tell
you where, in this column, the number 3
belongs. The first clue lies in the eighth
column of the diagram. There is a 3 in the
fifth cell. Since numbers can't be repeated
in any 3 x 3 block, we can't put a 3 in the
fourth, fifth, or sixth cells of the ninth
column. We can also eliminate the bottom
three cells of the ninth column because
there's a 3 in that 3 x 3 block as well.
Therefore, the 3 must go in the second or
third cell of the ninth column.
The above puzzle aAer soluBon looks like
this.
December 8, 2023
- 11 -
Getting Started
1. Launch the Game:
Open your preferred Java IDE (IntelliJ IDEA or Eclipse). Load the Sudoku game project.
Build and run the project to launch the game.
2. Welcome Screen:
Upon launching, you will be greeted by the welcome screen, featuring an inviting design and
the "Start Game" button. This screen sets the stage for your Sudoku adventure.
Welcome Screen
3. New Game Screen:
After starting a new game, you'll be taken to the puzzle interface. The Sudoku grid will be
displayed, ready for you to solve.
December 8, 2023
- 12 -
New Game Screen
4. Select Difficulty:
If you wish to change the difficulty level, explore the options provided on the main menu
before starting a new game.
Difficulty levels include Easy, Medium, and Hard, catering to players of varying skill levels.
Easy level difficulty provides you with a maximum of 3 hints and you are allowed to make 5
mistakes. Medium level provides you with 2 hints and a limit of 3 mistakes whereas the Hard
level provides you with just a single hint and a limit of 1 mistake. To make it more exciting,
you do not have the undo button for your help in the hard level.
Difficulty Selection
December 8, 2023
- 13 -
5. Game Interface:
The Sudoku grid appears, featuring a 9x9 layout with empty cells for your answers. Navigate
the grid using your mouse or keyboard, and start filling in numbers.
Game Interface
6. Controls:
• Navigate: Use your mouse move around the grid.
• Enter Numbers: Click on a particular grid you want to enter a number. Type the
desired number (1-9) in the selected cell. Only numbers (1-9) are accepted as input.
The game ignores any other key press.
• Clear Cell: To erase a number, press the backspace key.
7. Timer and Mistakes Counter:
Keep an eye on the timer tracking how long you've been playing. The mistakes counter
displays the number of errors allowed based on the chosen difficulty.
December 8, 2023
- 14 -
Timer and Mistakes Counter
8. Hint Functionality:
Stuck? Click on the "Hint" button to reveal the next cell you should focus on. The number of
hints available depends on the difficulty level. The number of available hints is indicated on
the hint button itself.
Hint Functionality
9. Check Solution:
Finally, as you progress through the puzzle, feel free to use the "Check Solution" button to
assess your progress.
If the puzzle is incomplete, empty cells will be highlighted in red, and an alert box will notify
you that the puzzle is yet to be solved.
Incomplete Puzzle
If you successfully complete the puzzle, an alert box will appear, congratulating you on your
victory.
December 8, 2023
- 15 -
A flicker animation will engage, highlighting all cells to celebrate your accomplishment.
Victory Screen:
After the animation, two buttons will be presented, allowing you to either start a new game or
exit the game.
10. Difficulty indicator:
There is a difficulty indicator, that indicates the difficulty level of the current game.
11. New Game button:
Want to start over again? The "New Game" button will launch a new puzzle giving you the
option to select the difficulty level to start the new game.
December 8, 2023
- 16 -
Contact Information
We value your experience with Sudoku Mastermind and are here to assist you. If you have
any questions, feedback, or encounter any issues, feel free to reach out to our support team.
We're dedicated to ensuring you have a seamless and enjoyable gaming experience.
Support Email:
For general inquiries and support, please contact us at:
vkhot@stevens.edu
We appreciate your support and look forward to hearing from you!
