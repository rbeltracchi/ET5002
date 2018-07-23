package com.plan111mil.exception;

import com.plan111mil.message.Message;

/**
 *
 * @author ET5002
 */
public class InvalidCredentialsException extends Exception {

	public InvalidCredentialsException() {
	}

	@Override
	public String getMessage() {
		// Este mensaje es el que se atrapa con la excepción y es el que se
		// debería mostrar directamente al usuario TODO: NO TRADUCIR A INGLES
		return Message.LOGIN_FAIL;
	}

}
