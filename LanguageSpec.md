### StartProgram
Denotes the start of a program. There can be only one program in each source file. Otherwise synonymous with StartBlock.

### EndProgram
Denotes the end of a program. There can be only one program in each source file. Otherwise synonymous with EndBlock.

# Branching

### If
Evaluates a boolean expression, and if the value is true, executes the supplied statement.

### Else
When the boolean expression of an If clause evaluates as false, the supplied statement will be executed.

### While
Executes a statement for as long as a boolean condition remains true.

### StartBlock
Denotes the start of a block, which may contain scoped variable declarations and statements.

### EndBlock
Denotes the end of a block.

# Literals

### True
Literal value that always evaluates as true.

### False
Literal value that always evaluates as false.

### ""
Literal strings are allowed.

# Operators

### AdditionOperator
Evaluates the expression a + b.

### SubtractionOperator
Evaluates the expression a - b.

### MultiplicationOperator
Evaluates the expression a * b.

### DivisionOperator
Evaluates the expression a / b.

### ModuloOperator
Evaluates the expression a % b.

### EqualityOperator (value)
Evaluates by value the boolean expression a == b.

### GreaterThanOperator
Evaluates by value the boolean expression a > b.

### LessThanOperator
Evaluates by value the boolean expression a < b.

### Or
Compounds two boolean expressions, evaluating as true if either is correct. Short-circuits.

### And
Compounds two boolean expressions, evaluating as true if both are correct.

### Not
Negates a boolean expression.

### AssignmentOperator
Assigns a value to a variable from a literal, input, or function.

# Methods

### DeclareMethod
Declares a method with a unique identifier, and list of argument identifiers. StartBlock and EndBlock define the method body.

### InvokeMethod
Invokes the method with the supplied arguments.

### Return
Completes the method and returns the supplied value, if any.

# Variables

### DeclareVariable
Declares a new variable which remains in memory for the scope of the current block. Its value can be assigned using the AssignmentOperator. Variables must be a 32-bit integer by default.

# Misc

### Print
Prints a value to StdOut.

### ReadInput
Reads values from StdIn.
