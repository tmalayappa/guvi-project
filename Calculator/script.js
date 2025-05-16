            let display = document.getElementById('result');
            let currentInput = '0';
            let previousInputs = [];
            let memoryStorage = localStorage.getItem('calculatorMemory') || '0';

            function updateDisplay() {
                display.value = currentInput;
            }

            function appendNumber(number) {
                console.log("Start of appendNumber, currentInput:", currentInput, "number:", number);
                if (currentInput === '0' || currentInput === 'Infinity' || currentInput === '-Infinity') {
                    currentInput = number;
                    console.log("Inside first if, currentInput:", currentInput);
                } else if (currentInput === '-') {
                    currentInput += number;
                    console.log("Inside else if, currentInput:", currentInput);
                } else if (isNaN(parseFloat(currentInput))) { // Add this else if to handle NaN cases specifically
                    currentInput = number;
                    console.log("Inside NaN check, currentInput:", currentInput);
                } else {
                    currentInput += number;
                    console.log("Inside else, currentInput:", currentInput);
                }
                updateDisplay();
                console.log("Value of currentInput in updateDisplay:", currentInput);
            }
            
            function appendDecimal() {
                if (!currentInput.includes('.')) {
                    currentInput += '.';
                    updateDisplay();
                }
            }

            function appendOperator(operator) {
                const lastInput = currentInput.slice(-1);

                // Allow starting with a negative sign
                if (currentInput === '0' && operator === '-') {
                    currentInput = '-';
                    console.log("currentInput set to:", currentInput); // Add this line
                    updateDisplay();
                    return;
                }

                // Allow entering a negative number as the second operand (or subsequent)
                if (currentInput === '' && operator === '-' && previousInputs.length > 0 && !['+', '-', '*', '/'].includes(previousInputs[previousInputs.length - 1])) {
                    currentInput = '-';
                    updateDisplay();
                    return;
                }

                // Prevent multiple operators in a row
                if (currentInput !== '' && ['+', '-', '*', '/'].includes(lastInput) && ['+', '-', '*', '/'].includes(operator)) {
                    previousInputs[previousInputs.length - 1] = operator; // Replace the last operator
                    updateDisplay();
                    return;
                }

                // If there's a current input and the last char is not an operator
                if (currentInput !== '' && !['+', '-', '*', '/'].includes(lastInput)) {
                    previousInputs.push(currentInput);
                    previousInputs.push(operator);
                    currentInput = '';
                    updateDisplay();
                }
            }

            function clearDisplay() {
                currentInput = '0';
                previousInputs = [];
                updateDisplay();
            }

            function calculate() {
                if (previousInputs.length > 0 && currentInput === '0' && previousInputs[previousInputs.length - 1] === '/') {
                    currentInput = 'Infinity';
                    previousInputs = [];
                    updateDisplay();
                    return;
                }

                if (currentInput !== '' && !isNaN(parseFloat(currentInput)) && previousInputs.length > 0) {
                    previousInputs.push(currentInput);
                    let expression = previousInputs.join('');
                    console.log("expression:", expression);
                    try {
                        const result = new Function('return ' + expression.replace(/÷/g, '/').replace(/×/g, '*'))();
                        currentInput = String(result);
                        previousInputs = [];
                        updateDisplay();
                    } catch (error) {
                        currentInput = 'Error';
                        previousInputs = [];
                        updateDisplay();
                    }
                } else if (previousInputs.length > 0 && isNaN(parseFloat(currentInput))) {
                    currentInput = 'Error';
                    previousInputs = [];
                    updateDisplay()
                }
            }

            document.addEventListener('keydown', function(event) {
                const key = event.key;
                if (key >= '0' && key <= '9') {
                    appendNumber(key);
                } else if (key === '.') {
                    appendDecimal();
                } else if (['+', '-', '*', '/'].includes(key)) {
                    appendOperator(key);
                } else if (key === 'Enter') {
                    calculate();
                } else if (key === 'Escape') {
                    clearDisplay();
                }
            });

            // Memory Functions
            function toggleMemoryStore() {
                if (currentInput !== '0' && !isNaN(parseFloat(currentInput))) {
                    memoryStorage = String(parseFloat(memoryStorage) + parseFloat(currentInput));
                    localStorage.setItem('calculatorMemory', memoryStorage);
                    currentInput = memoryStorage;
                    updateDisplay();
                } else {
                    memoryStorage = '0';
                    localStorage.setItem('calculatorMemory', memoryStorage);
                    currentInput = memoryStorage;
                    updateDisplay();
                }
            }

            // Test Suite (No changes needed here for input)
            function runTestSuite() {
                const testResultsContainer = document.getElementById('test-results');
                let testCount = 0;
                let passedCount = 0;

                const assert = (condition, description) => {
                    testCount++;
                    const result = condition ? 'pass' : 'fail';
                    const message = `<p class="${result}">Test ${testCount}: ${description} - <b>${result}</b></p>`;
                    testResultsContainer.insertAdjacentHTML('beforeend', message);
                    if (condition) {
                        passedCount++;
                    }
                };

                // Reset Calculator State Before Each Test
                function resetCalculator() {
                    currentInput = '0';
                    previousInputs = [];
                    updateDisplay();
                }

                // Test Case 1: Addition
                resetCalculator();
                appendNumber('5');
                appendOperator('+');
                appendNumber('3');
                calculate();
                assert(display.value === '8', 'Test Case 1: Addition');

                // Test Case 2: Subtraction
                resetCalculator();
                appendNumber('10');
                appendOperator('-');
                appendNumber('4');
                calculate();
                assert(display.value === '6', 'Test Case 2: Subtraction');

                // Test Case 3: Multiplication
                resetCalculator();
                appendNumber('6');
                appendOperator('*');
                appendNumber('7');
                calculate();
                assert(display.value === '42', 'Test Case 3: Multiplication');

                // Test Case 4: Division
                resetCalculator();
                appendNumber('8');
                appendOperator('/');
                appendNumber('2');
                calculate();
                assert(display.value === '4', 'Test Case 4: Division');

                // Test Case 5: Decimal Addition
                resetCalculator();
                appendNumber('2.5');
                appendOperator('+');
                appendNumber('3.5');
                calculate();
                assert(display.value === '6', 'Test Case 5: Decimal Addition');

                // Test Case 6: Clear Function
                resetCalculator();
                appendNumber('5');
                clearDisplay();
                assert(display.value === '0', 'Test Case 6: Clear Function');

                // Test Case 7: Multiple operations
                resetCalculator();
                appendNumber('2');
                appendOperator('+');
                appendNumber('3');
                appendOperator('*');
                appendNumber('4');
                calculate();
                assert(display.value === '14', 'Test Case 7: Multiple operations');

                // Test Case 8: Division by zero
                resetCalculator();
                appendNumber('5');
                appendOperator('/');
                appendNumber('0');
                calculate();
                assert(display.value === 'Infinity', 'Test Case 8: Division by zero');

                // Test Case 9: Negative numbers
                resetCalculator();
                appendOperator('-');
                console.log("Current input after appendOperator('-'):", currentInput); // Add this line
                appendNumber('5');
                appendOperator('+');
                appendNumber('10');
                calculate();
                assert(display.value === '5', 'Test Case 9: Negative numbers');


                // Test Case 10: Floating point numbers
                resetCalculator();
                appendNumber('2.5');
                appendOperator('*');
                appendNumber('2');
                calculate();
                assert(display.value === '5', 'Test Case 10: Floating point numbers');

                console.log(`${passedCount} / ${testCount} tests passed`);
                const summary = `<p><b>${passedCount} / ${testCount} tests passed</b></p>`;
                testResultsContainer.insertAdjacentHTML('beforeend', summary);
            }

            runTestSuite();