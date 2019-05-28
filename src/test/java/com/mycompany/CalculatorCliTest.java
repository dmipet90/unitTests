/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.StringReader;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 *
 * @author user
 */
public class CalculatorCliTest {
    
    private Calculator calculatorMock;
    private CalculatorCli calculatorCli;
    
    @Before
    public void setUp() {
        calculatorMock = Mockito.mock(Calculator.class);
        calculatorCli = new CalculatorCli(calculatorMock);
    }
    @Rule
    public TestRule timeout = new Timeout(100, TimeUnit.MILLISECONDS);
    /**
     * Test of runInteractiveSession method, of class CalculatorCli.
     */
    @Test
    public void emptyExpressionMustBeSkipped() throws InterruptedException {
        Thread.sleep(200);
        calculatorCli.runInteractiveSession(new StringReader(";\n;   ;;;\t\n;"));
        
        Mockito.verifyZeroInteractions(calculatorMock);
    }

    /**
     * Test of runInteractiveSession method, of class CalculatorCli.
     */
    @Test
    public void eachExpressionSeparatedBySemicolonMustBeEvaluated() {
        calculatorCli.runInteractiveSession(new StringReader("1;2;3;"));
        
        verify(calculatorMock).calculate("1");
        verify(calculatorMock).calculate("2");
        verify(calculatorMock).calculate("3");
        verifyNoMoreInteractions(calculatorMock);
    }

    /**
     * Test of main method, of class CalculatorCli.
     */
    @Test
    public void eachExpressionSeparatedBySemicolonMustBeEvaluated_2()  {
        when(calculatorMock.calculate("1")).thenReturn(1d);
        when(calculatorMock.calculate("2")).thenReturn(2d);
        when(calculatorMock.calculate("3")).thenReturn(2d);
        
        calculatorCli.runInteractiveSession(new StringReader("1;2;3;"));
        verify(calculatorMock).calculate("1");
        verify(calculatorMock).calculate("2");
        verify(calculatorMock).calculate("3");
        verifyNoMoreInteractions(calculatorMock);      
    }
    
}
