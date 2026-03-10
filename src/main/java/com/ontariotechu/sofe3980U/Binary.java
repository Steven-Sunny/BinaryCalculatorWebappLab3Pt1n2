package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// uncomment the following code
		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}
	}
	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue()
	{
		return this.number;
	}
	/**
	 * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;

	}

	/**
	 * Performs a bitwise logical OR operation on two binary variables.
	 * For more information, visit <a href="https://en.wikipedia.org/wiki/Bitwise_operation#OR"> Bitwise OR </a>.
	 *
	 * @param num1 The first operand object
	 * @param num2 The second operand object
	 * @return A binary variable with a value of <i>num1 | num2</i>.
	 */
	public static Binary or(Binary num1, Binary num2) {
		// StringBuilder is used to promote faster concatenation
		StringBuilder result = new StringBuilder();
		// Start tracking from the last character (the least significant bit)
		int i = num1.number.length() - 1;
		int j = num2.number.length() - 1;
		// Continue looping as long as at least one binary string has remaining bits
		while (i >= 0 || j >= 0) {
			char bit1;
			char bit2;
			// Check if the first binary still has bits remaining
			if (i >= 0) {
				// Assign the character bit at the current index
				bit1 = num1.number.charAt(i);
				// Decrement bit count after fetching the bit
				i = i - 1;
			} else {
				// No more bits for num1, treat the missing bit as '0'
				bit1 = '0';
			}

			// Check if the second binary still has bits remaining
			if (j >= 0) {
				// Assign the character bit at the current index
				bit2 = num2.number.charAt(j);
				// Decrement bit count after fetching the bit
				j = j - 1;
			} else {
				// No more bits for num2, treat the missing bit as '0'
				bit2 = '0';
			}
			// Logic for Bitwise OR: If either bit is '1', the result bit is '1'
			if (bit1 == '1' || bit2 == '1') {
				// Insert '1' at the beginning of the result string
				result.insert(0, "1");
			} else {
				// Insert '0' at the beginning of the result string
				result.insert(0, "0");
			}
		}
		// Return Binary value after operation (also converting from StringBuilder obj to String)
		return new Binary(result.toString());
	}

	/**
	 * Performs a bitwise logical AND operation on two binary variables.
	 * For more information, visit <a href="https://en.wikipedia.org/wiki/Bitwise_operation#AND"> Bitwise AND </a>.
	 *
	 * @param num1 The first operand object
	 * @param num2 The second operand object
	 * @return A binary variable with a value of <i>num1 &amp; num2</i>.
	 */
	public static Binary and(Binary num1, Binary num2) {
		// StringBuilder is used to promote faster concatenation
		StringBuilder result = new StringBuilder();
		// Start tracking from the last character (the least significant bit)
		int i = num1.number.length() - 1;
		int j = num2.number.length() - 1;
		// Continue looping as long as at least one binary string has remaining bits
		while (i >= 0 || j >= 0) {
			char bit1;
			char bit2;
			// Check if the first binary still has bits remaining
			if (i >= 0) {
				// Assign the character bit at the current index
				bit1 = num1.number.charAt(i);
				// Decrement bit count after fetching the bit
				i = i - 1;
			} else {
				// No more bits for num1, treat the missing bit as '0'
				bit1 = '0';
			}
			// Check if the second binary still has bits remaining
			if (j >= 0) {
				// Assign the character bit at the current index
				bit2 = num2.number.charAt(j);
				// Decrement bit count after fetching the bit
				j = j - 1;
			} else {
				// No more bits for num2, treat the missing bit as '0'
				bit2 = '0';
			}
			// Logic for Bitwise AND: If both bits are '1', the result bit is '1'
			if (bit1 == '1' && bit2 == '1') {
				// Insert '1' at the beginning of the result string
				result.insert(0, "1");
			} else {
				// Insert '0' at the beginning of the result string
				result.insert(0, "0");
			}
		}
		// Return Binary value after operation (also converting from StringBuilder obj to String)
		return new Binary(result.toString());
	}

	/**
	 * Multiplies two binary variables using the partial products method.
	 * For more information, visit <a href="https://www.geeksforgeeks.org/maths/binary-multiplication/"> Multiply-Binary-Numbers </a>.
	 *
	 * @param num1 The first factor (multiplicand) object
	 * @param num2 The second factor (multiplier) object
	 * @return A binary variable with a value of <i>num1 * num2</i>.
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		// Initialize the starting binary value to 0
		Binary result = new Binary("0");
		String s2 = num2.number;

		// Iterate through the second number from right to left
		for (int i = 0; i < s2.length(); i++) {
			// If the bit is '1', shift num1 left by 'i' positions and add to result
			if (s2.charAt(s2.length() - 1 - i) == '1') {
				// Create a temporary string to hold the shifted value
				// StringBuilder is used to promote faster concatenation
				StringBuilder shiftedValue = new StringBuilder(num1.number);
				for (int j = 0; j < i; j++) {
					// Shifting is just adding zeros to the end
					shiftedValue.append("0");
				}
				// Add the shifted value into the final result
				result = add(result, new Binary(shiftedValue.toString()));
			}
		}
		// Return Binary value after operation
		return result;
	}
}