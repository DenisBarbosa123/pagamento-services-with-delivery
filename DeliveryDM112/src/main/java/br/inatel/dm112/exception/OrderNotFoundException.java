package br.inatel.dm112.exception;

public class OrderNotFoundException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 541133563687880758L;

	public OrderNotFoundException(String msg) 
	{
		super(msg);
	}
}
