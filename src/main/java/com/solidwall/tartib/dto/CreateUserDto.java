package com.solidwall.tartib.dto;

import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

	@NotBlank
	@Size(min = 3, max = 20)
	private String firstName;

	@NotBlank
	@Size(min = 3, max = 20)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private Set<String> role;

}
