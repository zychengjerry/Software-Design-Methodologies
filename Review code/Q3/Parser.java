/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * Question:
 * Write a parse to recognize the language specified by the following grammar:
 * X := 1 | 3 | 5 | 7 | 9 ; Y := 0 | 2 | 4 | 6 | 8 ; X := YX ; Y := XY ; S := X | Y ;
 *
 *
 */
public class Parser {
	Tokeniser _tokeniser;

	public Parser(Tokeniser tokeniser) {
		_tokeniser = tokeniser;
	}

	/**
	 * @return True if the given tokens conform with the grammar. False, otherwise.
	 */
	public boolean parseExp() {
		if (!_tokeniser.isNextOrLastValid()) return false;
		if (!_tokeniser.hasNext()) {
			return true;
		}

		// TODO: Complete this method
		// START YOUR CODE

		// Pop the last token out and try to load the second last one
		Token lst1 = _tokeniser.takeLast();
		Token lst2 = _tokeniser.last();

		// There must be at least one valid token
		if (lst1 == null) {
			return false;
		}

		// Count the number of valid tokens except the last token
		int remainingTokenCounts = 0;

		// This while loop could be stopped by two factors 1). Invalid token, 2). Empty buffer
		while (_tokeniser.hasNext()) {
			_tokeniser.takeNext();
			remainingTokenCounts += 1;
		}

		// If there is an invalid token, return false
		if (!_tokeniser.isNextOrLastValid()) {
			return false;
		}

		// There is only one digit in the buffer, should expect another last2 token to be null
		if (remainingTokenCounts == 0) {
			return lst2 == null;
		}

		// The case where there are at least two tokens
		if (lst2 == null) {
			return false;
		}

		// The grammar implies the String with different token type of the last two tokens in the buffer
		return lst1.type != lst2.type;
		// END YOUR CODE
	}
}