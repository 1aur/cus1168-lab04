package academy.javapro;

class ExpressionParser {
    private final String input;
    private int position;

    public ExpressionParser(String input) {
        this.input = input;
        this.position = 0;
    }



    // expr → expr + term
    public double parseExpression() {

        try{
        double result = parseTerm();

        while(position < input.length() && input.charAt(position) == '+'){
            position++;
            result += parseTerm();
        }
        return result;
    } 
    catch(RuntimeException e){
        throw new UnsupportedOperationException("Implement parseExpression");
    }
    } 



    // term → term * factor
    private double parseTerm() {

        try{
        double result = parseFactor();

        while (position < input.length() && input.charAt(position) == '*'){
            position++;
            result *= parseFactor();
        }
        return result;
    }
    catch(RuntimeException e){
        throw new UnsupportedOperationException("Implement parseTerm");
    }
    }



    // factor → ( expr )
    private double parseFactor() {

        try{
        if (position < input.length() && input.charAt(position) == '('){
            position++;
            double result = parseExpression();

            if(position >= input.length() || input.charAt(position) != ')'){
                throw new RuntimeException("Missing closing parenthesis");
            }
            position++;
            return result;
        }
        return parseNumber();
    }
    catch(RuntimeException e){
        throw new UnsupportedOperationException("Implement parseFactor");
    }
}

    // Parse a numeric value
    private double parseNumber() {

        try {
        StringBuilder number = new StringBuilder();

        while(position < input.length() && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')){
        number.append(input.charAt(position));
        position++;
        }

        if(number.length() == 0){
            throw new RuntimeException("Expected a number at position " + position);
        }
        return Double.parseDouble(number.toString());
        }
       catch(RuntimeException e){
        throw new UnsupportedOperationException("Implement parseNumber");
    }
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "2 + 3 * (4 + 5)",        // Complex expression with parentheses
                "2 + 3 * 4",              // Basic arithmetic with precedence
                "(2 + 3) * 4",            // Parentheses changing precedence
                "2 * (3 + 4) * (5 + 6)",  // Multiple parentheses
                "1.5 + 2.5 * 3"           // Decimal numbers
        };

        // Process each test case
        for (String expression : testCases) {
            System.out.println("\nTest Case: " + expression);
            try {
                ExpressionParser parser = new ExpressionParser(expression.replaceAll("\\s+", "")); // Remove spaces
                double result = parser.parseExpression();
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}