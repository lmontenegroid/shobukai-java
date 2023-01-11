package ar.com.shobukai.domain.dao.exception;

import java.sql.SQLException;

public class DuplicatedException extends Exception {

	public DuplicatedException(String string, SQLException e) {
		super(string, e);
	}

}
