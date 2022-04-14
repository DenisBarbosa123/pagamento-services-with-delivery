package br.inatel.dm112.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
	private int number;
	private String cpf;
	private float value;
	private int status;
	private Date orderDate;
	private Date issueDate;
	private Date paymentDate;
}

