package org.nasa;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

public class RoverMainTest {

    @Test
    public void should_display_rovers_final_positions() {
        // Given
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        // When
        RoverMain.main(new String[] { "src/test/resources/input.txt" });

        // Then
        var consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertEquals("1 3 N\n5 1 E\n", consoleOutput);
        out.close();
    }

    @Test
    public void should_throw_an_exception_when_file_path_is_invalid() {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                    () -> RoverMain.main(new String[] { "x" }));

        // Then
        assertEquals("You need to specify a valid file path", exception.getMessage());
    }

    @Test
    public void should_throw_an_exception_when_file_path_is_null() {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> RoverMain.main(null));

        // Then
        assertEquals("You need to specify a valid file path", exception.getMessage());
    }

    @Test
    public void should_throw_an_exception_when_file_path_is_empty() {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> RoverMain.main(new String[] {}));

        // Then
        assertEquals("You need to specify a valid file path", exception.getMessage());
    }

    @Test
    public void should_throw_an_exception_when_the_file_is_empty() {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> RoverMain.main(new String[] { "src/test/resources/empty_input.txt" }));

        // Then
        assertEquals("The file must not be empty", exception.getMessage());
    }
}
