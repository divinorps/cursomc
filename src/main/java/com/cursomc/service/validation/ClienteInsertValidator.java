package com.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.domain.Cliente;
import com.cursomc.domain.enuns.TipoCliente;
import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.reposotiries.ClienteRepository;
import com.cursomc.resources.exception.FieldMessage;
import com.cursomc.service.validation.utils.ValidaCPFCNPJ;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getId()) && !ValidaCPFCNPJ.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj","CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId()) && !ValidaCPFCNPJ.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj","CNPJ inválido"));
		}
		
		Cliente cliente = repository.findByEmail(objDto.getEmail());
		if (cliente != null) {
			list.add(new FieldMessage("email",  "Email já cadastrado."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
