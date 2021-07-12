/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * Question:
 * Write a parse to recognize the language specified by the following grammar:
 * X := + | - | * | / ; Y := 0 | 2 | 4 | 6 | 8 ; S := SXY | Y ;
 *
 * X := + | - | * | / ; Y := 0 | 2 | 4 | 6 | 8 ; S := YXS | Y ;
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
		if (!_tokeniser.isNextValid()) return false;
		if (!_tokeniser.hasNext()) {
			return true;
		}

		// TODO: Complete this method
		// START YOUR CODE
		Token nxt = _tokeniser.takeNext();

		if (nxt.type == Token.Type.EVEN_NUMBER) {

			Token nxt2 = _tokeniser.next();

			if (!_tokeniser.isNextValid()) {
				// Case where next token is not valid
				return false;
			} else if (nxt2 == null) {
				// Case where S := Y
				return true;
			} else if (nxt2.type == Token.Type.MATH_OPERATION) {
				// Case where S := SXY
				_tokeniser.takeNext();
				return parseExp();
			} else {
				// Case where S := YY, should return false
				return false;
			}

		} else {
			return false;
		}
		// END YOUR CODE
	}

}