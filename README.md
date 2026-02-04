# Sudoku Generator & Solver (Java)

A simple **Sudoku puzzle generator + solver** written in Java. This project generates valid 9×9 Sudoku boards and solves Sudoku puzzles programmatically.

Features
- Generate valid 9×9 Sudoku puzzles
- Solve Sudoku puzzles (fills empty cells while respecting Sudoku rules)
- Validity checks for rows, columns, and 3×3 sub-grids
- Java project (Eclipse/IDE-friendly project structure)

How it works (high level)

Typical flow:
1. Generate a complete valid Sudoku solution grid
2. Remove numbers from the grid to create a playable puzzle (difficulty depends on how many cells are removed)
3. Solve puzzles using a standard approach such as backtracking (try a number, recurse, undo if invalid)


# Requirements
Java JDK 8+ (recommended: JDK 11+)
Any IDE (Eclipse / IntelliJ IDEA) or command line
