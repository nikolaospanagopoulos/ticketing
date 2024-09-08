package com.events.tickets.servicesImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.events.tickets.services.PaymentService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
@Service
public class PaymentServiceImpl implements PaymentService{

	@Override
	public Charge chargeNewCard(String token, double amount) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		// TODO Auto-generated method stub
		Map<String,Object>chargeParams = new HashMap<>();
		chargeParams.put("ammount", (int)(amount * 100));
		chargeParams.put("currency", "USD");
		chargeParams.put("source", token);
		chargeParams.put("description","Charge for a ticket");
		return Charge.create(chargeParams);
	}

}
