package ar.com.shobukai.dao.exception;

import java.sql.SQLException;

public class GenericException extends Exception {

	public GenericException(String string, Throwable e) {
		super(string, e);
	}

}
