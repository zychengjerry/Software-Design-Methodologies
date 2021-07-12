/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * Question:
 * Write a parse to recognize the language specified by the following grammar:
 * S := () ; S := )S( ; S := SS ;
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
		if (!_tokeniser.isNextValid()) return false;
		if (!_tokeniser.hasNext()) {
			return true;
		}

        // TODO: Complete this method
		// START YOUR CODE
		Token nxt = _tokeniser.next();

		if (nxt.type == Token.Type.LEFT_BRACKET) {

			if (_tokeniser.nextNext() != null && _tokeniser.nextNext().type == Token.Type.RIGHT_BRACKET) {
				_tokeniser.takeNext();
				_tokeniser.takeNext();
			} else {
				return false;
			}

		} else {

			_tokeniser.takeNext();
			parseExp();

			if (!_tokeniser.hasNext() || !_tokeniser.isNextValid()) {
				return false;
			}

			Token nxt2 = _tokeniser.next();
			if (nxt2.type == Token.Type.LEFT_BRACKET) {
				_tokeniser.takeNext();
			}
		}

		if (!_tokeniser.isNextValid()) {
			return false;
		}

		if (_tokeniser.hasNext()) {
			return parseExp();
		} else {
			return true;
		}
 		// END YOUR CODE
    }
}
