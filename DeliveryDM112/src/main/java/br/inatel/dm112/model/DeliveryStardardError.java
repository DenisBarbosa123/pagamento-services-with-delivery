package br.inatel.dm112.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryStardardError 
{
	private int status;
	private String message;
	private long timeStamp;
}
