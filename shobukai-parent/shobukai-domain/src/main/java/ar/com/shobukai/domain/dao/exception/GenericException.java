package ar.com.shobukai.domain.dao.exception;

import java.sql.SQLException;

public class GenericException extends Exception {

	public GenericException(String string, SQLException e) {
		super(string, e);
	}

}
