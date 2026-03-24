package sudoku.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sudoku.model.exceptions.FillingBoardSudokuException;
import sudoku.model.models.SudokuBoard;
import sudoku.model.solver.BacktrackingSudokuSolver;

import java.util.Random;

public class DifficultyEnumTest {

    @Test
    void testClearSudokuFieldsFromSudokuBoardBasedOnDifficulty_RemovesCorrectNumFields() {
        int totalFields = 81;
        SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard3 = new SudokuBoard(new BacktrackingSudokuSolver());

        sudokuBoard1.solveGame();
        sudokuBoard2.solveGame();
        sudokuBoard3.solveGame();

        assertEquals(totalFields, countFilledFields(sudokuBoard1));
        assertEquals(totalFields, countFilledFields(sudokuBoard2));
        assertEquals(totalFields, countFilledFields(sudokuBoard3));

        DifficultyEnum.EASY.clearSudokuFieldsFromSudokuBoardBasedOnDifficulty(sudokuBoard1, new Random(1));
        DifficultyEnum.MEDIUM.clearSudokuFieldsFromSudokuBoardBasedOnDifficulty(sudokuBoard2, new Random(1));
        DifficultyEnum.HARD.clearSudokuFieldsFromSudokuBoardBasedOnDifficulty(sudokuBoard3, new Random(1));

        assertEquals(totalFields - 30, countFilledFields(sudokuBoard1));
        assertEquals(totalFields - 50, countFilledFields(sudokuBoard2));
        assertEquals(totalFields - 70, countFilledFields(sudokuBoard3));
    }

    private int countFilledFields(SudokuBoard board) {
        int count = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board.getField(x, y).getValue() != 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
