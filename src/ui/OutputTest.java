package ui;

import data.CSVReader;
import data.Input;
import logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import processor.Pull;
import util.Bookshelf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class OutputTest {
    private final InputStream  systemIn = System.in;
    private ByteArrayInputStream testIn;
    Bookshelf shelf = Bookshelf.getShelf();
    Logger logger = Logger.getInstance();
    CSVReader csv = new CSVReader(logger,shelf);
    Pull pull = new Pull(shelf);
    Scanner scanner = new Scanner(System.in);
    Output output = new Output(csv,null,pull,null,null,shelf,scanner);

    @Before
    public void setUp() throws Exception {
        logger.setDestination();
        this.csv.readBook("test1.csv");
    }

    private void setInput(String input){
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreInput(){
        System.setIn(systemIn);
    }

    @Test
    public void pull() {
        String testAuthor = "Imago";
        setInput(testAuthor);

        output.pull(4);
    }

    @Test
    public void upStatusChoice() {
    }

    @Test
    public void input() {
    }

    @Test
    public void csv() {
    }

    @Test
    public void execute() {
    }
}